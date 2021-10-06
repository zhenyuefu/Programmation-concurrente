package spectacle;

public class Groupe implements Runnable {
    private static int cpt = 1;
    private final int id;
    private final int nb;
    private Salle salle;

    public Groupe(int n, Salle salle) {
        nb = n;
        id = cpt++;
        this.salle = salle;
    }

    @Override
    public void run() {
        System.out.println("Group " + id + " reserve " + (salle.reserver(nb) ? "success" : "fail"));
        System.out.println(salle);
    }
}