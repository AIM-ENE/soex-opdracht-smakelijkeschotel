package bestelsysteem.service;

import bestelsysteem.dto.Menu;
import bestelsysteem.dto.VoedingRestrictie;

import java.util.List;
import java.util.Set;

public interface RestaurantService {
    Menu filterMenu(int restaurantId, Set<VoedingRestrictie> condities);
    void plaatsGerechtInWinkelmand(int restaurantId, int winkelmandId, List<String> gerechtNamen);
}
