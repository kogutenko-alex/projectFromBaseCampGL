package ua.kogutenko.inventory.model.communications;

import ua.kogutenko.inventory.model.connections.DSL;
import ua.kogutenko.inventory.model.connections.DegradedConnection;
import ua.kogutenko.inventory.model.connections.LineConnection;
import ua.kogutenko.inventory.model.connections.TelephoneLineConnection;

public class Pair {
    private final Double rz;

    public Pair(Double rz) {
        if (rz <= 2.5 && rz >= 0.5)
            this.rz = rz;
        else this.rz = 0.5;
    }

    public Double getRz() {
        return rz;
    }

    public LineConnection getConnection() {
        if (rz >= 1 && rz <= 2.5) {
            return new TelephoneLineConnection();
        }
        if (rz >= 1.5 && rz <= 2.5) {
            return new DSL();
        }
        return new DegradedConnection();
    }

}
