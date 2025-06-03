package bestelsysteem.service;

import bestelsysteem.dto.Bestelling;
import bestelsysteem.model.Tafel;

public interface TafelService {
    Bestelling getBestelling(Tafel tafel, int bestelnummer);
    Integer plaatsBestelling(int restaurantId, int tafelnummer, int winkelmandId);
}
