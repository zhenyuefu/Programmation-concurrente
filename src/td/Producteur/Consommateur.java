package td.Producteur;

public class Consommateur implements Runnable {
    private Buffer b;
    private int nbRetrait;

    public Consommateur(Buffer b) {
        this.b = b;
        nbRetrait = b.getTaille();
    }

    public void run() {
        int val;
        for (int i = 0; i < nbRetrait; i++) {
            try {
                val = b.retrait();
                System.out.println(val);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
