package Barbier;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable {

    private static AtomicInteger cpt = new AtomicInteger(0);
    private final int id;

    public Client() {
        id = cpt.getAndIncrement();
    }

    @Override public void run() {
        try {
            Salle salle = Salle.getInstance();
            salle.enter(this);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Client " + id;
    }
}
