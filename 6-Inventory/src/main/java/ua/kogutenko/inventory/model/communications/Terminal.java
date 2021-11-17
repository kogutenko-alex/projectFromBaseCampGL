package ua.kogutenko.inventory.model.communications;

import ua.kogutenko.inventory.model.Coordinates;

public class Terminal implements Communication {

    private final Coordinates coordinates;

    private final boolean input;
    private final boolean output;

    public Terminal(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.input = true;
        this.output = true;
    }

    public Terminal(Coordinates coordinates, boolean output) {
        this.coordinates = coordinates;
        this.input = true;
        this.output = output;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


}
