package td.babouins;

import java.util.concurrent.Semaphore;

public class Baboulin implements Runnable {

    @Override
    public void run() {
        try {
            laCorde.acceder(position);
            System.out.println(this + " a pris la corde");
            traverser();
            System.out.println(this + " est arrive");
            laCorde.lacher();
        } catch (InterruptedException e) {
            System.out.println("Pb babouin !");
        }
    }

}
