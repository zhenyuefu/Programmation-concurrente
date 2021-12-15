package td.Noel;

import java.util.concurrent.Semaphore;

public class PereNoel implements Runnable {
    private final int NB_TOTAL_RENNES;
    private int nbRennesAtteles = 0;
    private Semaphore renneEnAttente = new Semaphore(0);
    private Semaphore pereNoelDispo = new Semaphore(0, true);

    public PereNoel(int nb) {
        NB_TOTAL_RENNES = nb;
    }

    public void sayHello() throws InterruptedException {
        renneEnAttente.release();
        pereNoelDispo.acquire();
    }

    private void attelerRenne() throws InterruptedException {
        renneEnAttente.acquire();
        System.out.println("Je suis le père Noel et je vais atteler un renne");
        Thread.sleep(300);
        nbRennesAtteles++;
        pereNoelDispo.release();
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
