package Garage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegAccueil {
    private Lock lock = new ReentrantLock();
    private Condition reserve = lock.newCondition();
    private boolean r;

    public void reserver() throws InterruptedException {
        lock.lock();
        try {
            if (r) {
                reserve.await();
            }
            r = true;
        } finally {
            lock.unlock();
        }
    }

    public void liberer() throws InterruptedException {
        lock.lock();
        try {
            r = false;
            reserve.signal();
        } finally {
            lock.unlock();
        }
    }
}
