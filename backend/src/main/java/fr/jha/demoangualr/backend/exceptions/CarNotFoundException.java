package fr.jha.demoangualr.backend.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("Impossible de trouver la voiture avec l'id : " + id);
    }
}
