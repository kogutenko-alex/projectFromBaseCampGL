package ua.kogutenko.inventory.model.communications;

import ua.kogutenko.inventory.model.Coordinates;
import ua.kogutenko.inventory.model.communications.Communication;

public class Wirecenter implements Communication {

    private final Coordinates coordinates;


    public Wirecenter(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


}
