package Barbier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbier implements Runnable {

    private Lock lock = new ReentrantLock();
    private Condition waitClient = lock.newCondition();

    public Barbier() {

    }

    public void clientArrived() {
        lock.lock();
        try {
            waitClient.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override public void run() {
        Salle salle = Salle.getInstance();
        lock.lock();
        try {
            while (true) {
                while (salle.isEmpty()) {
                    waitClient.await();
                }
                Client client = salle.recupere();
                Thread.sleep(1000);
                System.out.println("Barbier : " + client.toString() + " est servi");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
