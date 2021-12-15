package td.Noel;

public class PereNoel implements Runnable {
    private final int NB_TOTAL_RENNES;
    private int nbRennesAtteles = 0;
    private boolean busy = false;

    public PereNoel(int nb) {
        NB_TOTAL_RENNES = nb;
    }

    public synchronized void sayHello() throws InterruptedException {
        while (busy) {
            wait();
        }
        busy = true;
        notify();
    }

    private synchronized void attelerRenne() throws InterruptedException {
        while (!busy) {
            wait();
        }
        Thread.sleep(300);
        nbRennesAtteles++;
        System.out.printf("Encore un renne attelé.");
        System.out.printf("Il y en a maintenant %d\n", nbRennesAtteles);
        busy = false;
        notify();
    }

    public void run() {
        try {
            while (nbRennesAtteles != NB_TOTAL_RENNES) {
                attelerRenne();
            }
            Thread.sleep(100);
            System.out.println("Je monte dans le traineau !");
        } catch (InterruptedException e) {
            System.err.println("PereNoel interrompu en pleine travail !");
        }
    }
}
