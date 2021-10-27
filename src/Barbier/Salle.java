package Barbier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Salle {
    private static int MAX_CHAIR = 10;
    private Client[] chairAt;
    private int nbClient;
    private Lock lock = new ReentrantLock();
    private Condition salleVide = lock.newCondition();
    private Condition sallePleain = lock.newCondition();
    private static Salle salle = new Salle();

    private Salle() {
        chairAt = new Client[MAX_CHAIR];
        nbClient = 0;
    }
    
    public static Salle getInstance() {
        return salle;
    }

    public void getChair(Client c) throws InterruptedException {
        lock.lock();
        try {
            while (nbClient == MAX_CHAIR) {
                sallePleain.await();
            }
            chairAt[nbClient] = c;
            System.out.println(c + "takes chair " + nbClient);
            nbClient++;
            salleVide.notify();
        } finally {
            lock.unlock();
        }
    }

    public void releaseChair() throws InterruptedException {
        lock.lock();
        try {
            while (nbClient == 0) {
                salleVide.await();
            }
            chairAt[nbClient] = null;
            nbClient --;
            sallePleain.notify();
        }finally {
            lock.unlock();
        }
    }
    
    

}
