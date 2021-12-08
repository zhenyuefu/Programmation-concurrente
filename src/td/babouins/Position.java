package td.babouins;

public enum Position {
    NORD(0), SUD(1);

    private Position(int i) {
        this.index = i;
    }

    private final int index;

    public int index() {
        return index;
    }

}