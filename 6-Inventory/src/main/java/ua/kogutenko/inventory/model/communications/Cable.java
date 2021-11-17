package ua.kogutenko.inventory.model.communications;

public class Cable {

    private final Wirecenter wirecenter;
    private final Terminal terminal;

    public Cable(Wirecenter wirecenter, Terminal terminal) {
        this.wirecenter = wirecenter;
        this.terminal = terminal;
    }

    public Wirecenter getWirecenter() {
        return wirecenter;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void communicating(Communication com1, Communication com2) {

    }

}
