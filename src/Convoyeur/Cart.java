package Convoyeur;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cart {

    private int weightMax;
    private int nbMax;
    private Queue<AleaObject> objectList;
    private int weight;
    private final Lock lock = new ReentrantLock();
    private Condition waitEmpty = lock.newCondition();

    public Cart(int weightMax, int nbMax) {
        this.weightMax = weightMax;
        this.nbMax = nbMax;
        weight = 0;
        objectList = new LinkedList<>();
    }

    private boolean canAdd(AleaObject object) {
        return (objectList.size() < nbMax) && (weight + object.getWeight() < weightMax);
    }

    public void add(AleaObject object) throws InterruptedException {
        lock.lock();
        try {
            while (!canAdd(object)) {
                waitEmpty.await();
            }
            objectList.add(object);

        } finally {
            lock.unlock();
        }
    }

    public void unload() {
        while (objectList.size()!=0) {
            System.out.println("Unload "+objectList.poll());
        }
        waitEmpty.signalAll();
    }

}
