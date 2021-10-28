package Garage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegTournant implements Runnable {
    private int position;
    private int pDest;
    private PoolHangars poolH;
    private int locoID;
    private Lock lock = new ReentrantLock();
    private Condition attendPositionOK = lock.newCondition();
    private Condition attendAppel = lock.newCondition();
    private Condition attendEntree = lock.newCondition();
    private Condition attendVide = lock.newCondition();

    public SegTournant(PoolHangars poolh) {
        this.poolH = poolh;
    }

    public int getPosition() {
        return position;
    }

    public void appeler(int pos) {
        lock.lock();
        try {
            pDest = pos;
            attendAppel.signal();
        } finally {
            lock.unlock();
        }
    }

    public void attendreAppel() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("SegTournant attendre appel");
            attendAppel.await();
        } finally {
            lock.unlock();
        }
    }

    public void attendrePositionOK() throws InterruptedException {
        lock.lock();
        try {
            while (position != pDest) {
                System.out.println("attend position");
                attendPositionOK.await();
            }
            System.out.println("SegTournant Position Ok");
        } finally {
            lock.unlock();
        }
    }

    public void seDeplacer() throws InterruptedException {
        if (position != pDest) {
            Thread.sleep(Math.abs(position - pDest) * 1000L);
            position = pDest;
            System.out.println("SegTournant " + position + " -> " + pDest);
            attendPositionOK.signal();
        }
    }

    public void attendreEntree() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("SegTournant attendre loco entre");
            attendEntree.await();
        } finally {
            lock.unlock();
        }
    }

    public void entrer(int id) {
        lock.lock();
        try {
            locoID = id;
            System.out.println("SegTournant : loco " + id + "entrer");
            attendEntree.signal();
        } finally {
            lock.unlock();
        }
    }

    public void attendreVide() throws InterruptedException {
        lock.lock();
        try {

            System.out.println("SegTournant attendre vide");
            attendVide.await();
        } finally {
            lock.unlock();
        }
    }

    public void sortir(int id) {
        lock.lock();
        try {
            locoID = -1;
            System.out.println("SegTournant : loco " + id + " sortir");
            attendVide.signal();
        } finally {
            lock.unlock();
        }

    }

    @Override public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                attendreAppel();
                seDeplacer();
                attendreEntree();
                pDest = poolH.chercherDispo();
                seDeplacer();
                attendreVide();
            }
        } catch (InterruptedException e) {
            System.out.println("Terminaison sur interruption du segment tournant");
        }
    }

}
