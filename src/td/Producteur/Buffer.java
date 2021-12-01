package td.Producteur;

import java.util.concurrent.locks.*;

public class Buffer {
    private int nbEl = 0;
    private int[] table;
    private int id = 0;
    private int ir = 0;
    Lock mutex = new ReentrantLock();
    Lock[] mutexCase;
    Condition bufferNonVide = mutex.newCondition();
    Condition bufferNonPlein = mutex.newCondition();

    public Buffer(int taille) {
        table = new int[taille];
        mutexCase = new ReentrantLock[taille];
        for (int i = 0; i < taille; i++) {
            mutexCase[i] = new ReentrantLock();
        }
    }

    public int getNbEl() {
        return nbEl;
    }

    public int getTaille() {
        return table.length;
    }

    public int retrait() throws InterruptedException {

        int myIr;
        synchronized (this) {
            while (nbEl == 0) {
                wait();
            }
            nbEl--;
            myIr = ir;
            ir = (ir + 1) % table.length;
        }
        return table[myIr];
    }

    public void depot(int val) throws InterruptedException {
        int myId;
        mutex.lock();
        try {
            while (nbEl == table.length) {
                bufferNonPlein.await();
            }
            nbEl++;
            myId = id;
            id = (id + 1) % table.length;
            mutexCase[myId].lock();
            bufferNonVide.signalAll();
        } finally {
            mutex.unlock();
        }
        table[myId] = val;

        mutexCase[myId].unlock();
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < nbEl; i++) {
            res = res + "table[" + i + "]=" + table[i] + "\n";
        }
        return res;
    }

}
