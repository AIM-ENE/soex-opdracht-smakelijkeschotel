package bestelsysteem.service;

import bestelsysteem.dto.BestelRegel;
import bestelsysteem.dto.Bestelling;
import bestelsysteem.model.Gerecht;
import bestelsysteem.model.Restaurant;
import bestelsysteem.model.Tafel;
import bestelsysteem.model.Winkelmand;
import bestelsysteem.repository.GerechtRepository;
import bestelsysteem.repository.RestaurantRepository;
import bestelsysteem.repository.TafelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TafelServiceImpl implements TafelService {
    private final RestaurantRepository restaurantRepository;
    private final TafelRepository tafelRepository;
    private final GerechtRepository gerechtRepository;

    public TafelServiceImpl(RestaurantRepository restaurantRepository,
                        TafelRepository tafelRepository,
                        GerechtRepository gerechtRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tafelRepository = tafelRepository;
        this.gerechtRepository = gerechtRepository;
    }

    public Bestelling getBestelling(Tafel tafel, int bestelnummer) {
        //TODO: je zou hier heel mooi een custom query voor kunnen schrijven
        bestelsysteem.model.Bestelling bestelling = tafel.getBestelling(bestelnummer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bestelling niet gevonden"));

        List<BestelRegel> collect = bestelling.getGerechten().stream()
                .map(bestellingGerecht -> {
                    Gerecht gerecht = gerechtRepository.findById(
                                    Objects.requireNonNull(bestellingGerecht.gerecht().getId()))
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("gerecht %d in bestelling niet bestaand",
                                            bestellingGerecht.gerecht().getId())));
                    return new BestelRegel(gerecht.naam(), bestellingGerecht.aantal());
                }).toList();
        return new Bestelling(bestelling.getBestelnummer(), bestelling.getStatus(), collect);
    }

    public Integer plaatsBestelling(int restaurantId, int tafelnummer, int winkelmandId) {
        //TODO: eigenlijk zou dit transactioneel moeten zijn; bestelling slaagt nu niet gegarandeerd alleen wanneer de winkelmand ook verwijderd is
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "restaurant niet gevonden"));
        Tafel tafel = tafelRepository.findTafelByRestaurant(restaurantId, tafelnummer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tafel in restaurant niet gevonden"));
        Winkelmand winkelmand = restaurant.getWinkelmand(winkelmandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "winkelmand niet gevonden"));

        int bestelNummer = tafel.plaatsBestelling(getGerechten(winkelmand));
        tafelRepository.save(tafel);

        restaurant.verwijderWinkelmand(winkelmand); //omgezet naar een bestelling
        restaurantRepository.save(restaurant);
        return bestelNummer;
    }

    private List<Gerecht> getGerechten(Winkelmand winkelmand) {
        //TODO: je zou hier heel mooi een custom query voor kunnen schrijven
        return winkelmand.gerechten().stream().map(
                        winkelmandGerecht -> gerechtRepository.findById(
                                Objects.requireNonNull(winkelmandGerecht.gerecht().getId()))
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "gerecht uit winkelmand niet gevonden")))
                .collect(Collectors.toList());
    }
}
