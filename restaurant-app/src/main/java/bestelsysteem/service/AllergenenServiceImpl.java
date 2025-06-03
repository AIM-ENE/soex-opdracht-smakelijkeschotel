package bestelsysteem.service;

import bestelsysteem.dto.VoedingRestrictie;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AllergenenServiceImpl implements AllergenenService {
    @Override
    public Set<VoedingRestrictie> getVoedingRestrictie(String ingredientNaam) {
        //TODO: vraag bij externe service op wat de diet informatie is van een ingredient op basis van naam
        Set<VoedingRestrictie> restrictiesIngredient = new HashSet<>();
        switch (ingredientNaam) {
            case "Parmesan Cheese", "Mozzarella Cheese" -> restrictiesIngredient.add(VoedingRestrictie.LACTOSE);
            case "Spaghetti", "Tomato Sauce", "Ground Beef" -> restrictiesIngredient.add(VoedingRestrictie.GLUTEN);
            case "Pizza Dough" -> {}
            default -> restrictiesIngredient.add(VoedingRestrictie.NIKS); // Lettuce, Croutons
        }
        return restrictiesIngredient;
    }
}
