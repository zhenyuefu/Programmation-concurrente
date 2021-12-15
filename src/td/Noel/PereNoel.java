package td.Noel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PereNoel implements Runnable {
    private final int NB_TOTAL_RENNES;
    private int nbRennesAtteles = 0;
    private boolean busy = false;
    private Lock lock = new ReentrantLock();
    private Condition pereNoelDispo = lock.newCondition();
    private Condition renneEnAttente = lock.newCondition();

    public PereNoel(int nb) {
        NB_TOTAL_RENNES = nb;
    }

    public void sayHello() throws InterruptedException {
        lock.lock();
        try {
            while (busy) {
                pereNoelDispo.await();
            }
            busy = true;
            renneEnAttente.signal();
        } finally {
            lock.unlock();
        }
    }

    private void attelerRenne() throws InterruptedException {
        lock.lock();
        try {
            while (!busy) {
                renneEnAttente.await();
            }
            nbRennesAtteles++;
            busy = false;
            pereNoelDispo.signal();
        } finally {
            lock.unlock();
        }
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
