import java.util.Random;

public class Producteur implements Runnable {

    private Buffer buf;
    private Random gen = new Random();
    private int nbDepots;

    public Producteur(Buffer b) {
        buf = b;
        nbDepots = b.getTaille();
    }

    public void run() {
        int val = 0;
        for (int i = 0; i < nbDepots; i++) {
            try {
                val = gen.nextInt(30);
                buf.depot(val);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
