package ua.kogutenko.inventory.inventory;

import ua.kogutenko.inventory.model.Coordinates;
import ua.kogutenko.inventory.model.communications.Cable;
import ua.kogutenko.inventory.model.communications.Pair;
import ua.kogutenko.inventory.model.communications.Terminal;
import ua.kogutenko.inventory.model.communications.Wirecenter;

import java.util.ArrayList;

public class Inventory {
    private static ArrayList<Wirecenter> wirecenters ;
    private static ArrayList<Terminal> terminals;
    private static Cable[] cables;

    public Inventory() {}

    public static void setWirecenters(ArrayList<Wirecenter> wirecenters) {
        Inventory.wirecenters = wirecenters;
    }

    public static void setTerminals(ArrayList<Terminal> terminals) {
        Inventory.terminals = terminals;
    }

    public static void setCables(Cable[] cables) {
        Inventory.cables = cables;
    }

    public static ArrayList<Wirecenter> getWirecenters() {
        return wirecenters;
    }

    public static ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    private static void init() {
        cables = new Cable[200];

        wirecenters.add(new Wirecenter(new Coordinates(1.1, 1.1)));
        wirecenters.add(new Wirecenter(new Coordinates(1.2, 1.2)));
        wirecenters.add(new Wirecenter(new Coordinates(1.3, 1.3)));

        terminals.add(new Terminal(new Coordinates(2.1, 2.1), false));
        terminals.add(new Terminal(new Coordinates(2.2, 2.2), false));
        terminals.add(new Terminal(new Coordinates(2.3, 2.3), false));
        terminals.add(new Terminal(new Coordinates(2.4, 2.4), false));
        terminals.add(new Terminal(new Coordinates(2.5, 2.5), false));
        terminals.add(new Terminal(new Coordinates(2.6, 2.6), false));
        terminals.add(new Terminal(new Coordinates(2.7, 2.7), false));
        terminals.add(new Terminal(new Coordinates(2.8, 2.8), false));
        terminals.add(new Terminal(new Coordinates(2.9, 2.9), true));
        terminals.add(new Terminal(new Coordinates(2.10, 2.10), true));
        terminals.add(new Terminal(new Coordinates(2.11, 2.11), true));
        terminals.add(new Terminal(new Coordinates(2.12, 2.12), true));

        execute();
    }

    public static Cable[] getCables() {
        return cables;
    }

    public static void execute() {

    }

}
