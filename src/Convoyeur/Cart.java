package Convoyeur;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cart {

    private final Lock lock = new ReentrantLock();
    private int weightMax;
    private int nbMax;
    private Queue<AleaObject> objectList;
    private int weight;
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

    public boolean isEmpty() {
        return objectList.isEmpty();
    }

    public void add(AleaObject object) throws InterruptedException {
        lock.lock();
        try {
            while (!canAdd(object)) {
                waitEmpty.await();
            }
            objectList.add(object);
            weight += object.getWeight();
            System.out.println("Weight:" + weight + "/" + weightMax + " nbobject:" + objectList.size() + "/" + nbMax);
        } finally {
            lock.unlock();
        }
    }

    public void unload(Unloader u) {
        lock.lock();
        try {
            while (objectList.size() != 0) {
                AleaObject o = objectList.poll();
                System.out.println("Unloader " + u + " unloading object" + o);
                weight -= o.getWeight();
                System.out.println(
                    "Weight:" + weight + "/" + weightMax + " nbobject:" + objectList.size() + "/" + nbMax);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            waitEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
