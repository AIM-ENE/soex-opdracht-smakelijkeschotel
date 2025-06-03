package bestelsysteem.service;

import bestelsysteem.dto.Gerecht;
import bestelsysteem.dto.Menu;
import bestelsysteem.dto.VoedingRestrictie;
import bestelsysteem.model.Restaurant;
import bestelsysteem.model.Winkelmand;
import bestelsysteem.repository.GerechtRepository;
import bestelsysteem.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final AllergenenService allergenenService;
    private final RestaurantRepository restaurantRepository;
    private final GerechtRepository gerechtRepository;

    public RestaurantServiceImpl(AllergenenService allergenenService, RestaurantRepository restaurantRepository, GerechtRepository gerechtRepository) {
        this.allergenenService = allergenenService;
        this.restaurantRepository = restaurantRepository;
        this.gerechtRepository = gerechtRepository;
    }

    @Override
    public Menu filterMenu(int restaurantId, Set<VoedingRestrictie> condities) {
        Menu menu = gerechtRepository.findMenu(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "menu voor restaurant niet gevonden"));
        if(condities.contains(VoedingRestrictie.NIKS)) {
            if(condities.size() > 1) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "`niks` mag niet gecombineerd worden met andere voedingrestricties");
            } else {
                return menu; // geen filtering nodig
            }
        }

        List<Gerecht> gefilterdMenu = menu.gerechten().stream().filter(gerecht -> {
            for (Gerecht.GerechtIngredient gerechtIngredient : gerecht.ingredienten()) {
                Set<VoedingRestrictie> restrictiesIngredient = allergenenService.getVoedingRestrictie(gerechtIngredient.naam());
                if(restrictiesIngredient.contains(VoedingRestrictie.NIKS)) {
                    return false; // voor de zekerheid filteren, niks bekend over voeding restricties van dit ingredient
                }
                if (condities.stream().anyMatch(restrictiesIngredient::contains)) {
                    return false;
                }
            }
            return true;
        }).toList();
        return new Menu(gefilterdMenu);
    }

    public void plaatsGerechtInWinkelmand(int restaurantId, int winkelmandId, List<String> gerechtNamen) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "restaurant niet gevonden"));
        Winkelmand winkelmand = restaurant.getWinkelmand(winkelmandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "winkelmand niet gevonden"));

        for(String gerechtNaam : gerechtNamen) {
            bestelsysteem.model.Gerecht gerecht = gerechtRepository.findByRestaurantAndNaam(restaurantId, gerechtNaam)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("gerecht '%s' niet gevonden voor restaurant %d", gerechtNaam, restaurantId)));

            winkelmand.voegGerechtToe(gerecht);
        }
        restaurantRepository.save(restaurant);
    }
}
