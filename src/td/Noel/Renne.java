package td.Noel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Renne implements Runnable {
    private int id;
    private PereNoel _santa;
    private CyclicBarrier _cb;
    private static Object lock = new Object();
    private static int cpt = 1;

    public Renne(PereNoel santa, CyclicBarrier cb) {
        _santa = santa;
        _cb = cb;
        synchronized (lock) {
            cpt++;
        }
    }

    public void run() {
        try {
            _santa.sayHello();
            _cb.await();
            System.out.println("Ho huisse!");
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("renne " + id + " interrupted");
        }
    }
}
