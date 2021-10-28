package Garage;

public class Hangar {

    private static int cpt = 1;
    private final int numero;
    private int idLoco;
    private boolean free;

    public Hangar() {
        idLoco = -1;
        free = true;
        numero = cpt++;

    }

    public boolean isFree() {
        return free;
    }

    public void entrer(int idLoco) {
        this.idLoco = idLoco;
        free = false;
    }

    public int getNumero() {
        return numero;
    }

    public int getIdLoco() {
        return idLoco;
    }
}
