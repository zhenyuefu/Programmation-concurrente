package babouins;

import java.util.concurrent.Semaphore;

public class Corde {

    private Semaphore mutex = new Semaphore(1);
    private Semaphore cond = new Semaphore(0);
    private int cptCond = 0;
    Position sens = null;
    int cpt = 0;

    public void acceder(Position p) throws InterruptedException {
        mutex.acquire();
        try {
            while (sens != null && sens != p) {
                cptCond--;
                mutex.release();
                cond.acquire();
                mutex.acquire();
            }
            sens = p;
            cpt++;
        } finally {
            mutex.release();
        }
    }

    public void lacher() throws InterruptedException {
        mutex.acquire();
        try {
            cpt--;
            if (cpt == 0) {
                sens = null;
                if (cptCond < 0) {
                    cptCond++;
                    cond.release();
                }
            }
        } finally {
            mutex.release();
        }
    }

}
