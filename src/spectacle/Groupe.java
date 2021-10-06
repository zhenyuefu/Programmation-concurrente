package spectacle;

public class Groupe implements Runnable {
    private final int id;
    private final int nb;
    private static int cpt;
    private Salle salle;

    public Groupe(int n, Salle salle) {
        nb = n;
        id = cpt++;
        this.salle = salle;
    }

    public void run() {
        salle.reserver(nb);
    }
}