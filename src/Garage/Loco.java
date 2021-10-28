package Garage;

import java.util.concurrent.atomic.AtomicInteger;

public class Loco implements Runnable {
    private static AtomicInteger counter = new AtomicInteger(0);
    private static AtomicInteger locoRemainingCounter = new AtomicInteger(0);
    private final SegAccueil sAccueil;
    private final SegTournant sTournant;
    private final PoolHangars pHangars;
    private final int id;

    public Loco(SegAccueil sAccueil, SegTournant sTournant, PoolHangars pHangars) {
        this.sAccueil = sAccueil;
        this.sTournant = sTournant;
        this.pHangars = pHangars;
        id = counter.getAndIncrement();
        locoRemainingCounter.incrementAndGet();
    }

    @Override public void run() {
        try {
            sAccueil.reserver();
            System.out.println("Loco " + id + " réserve le segment d'acceuil");
            sTournant.appeler(0);
            System.out.println("Loco " + id + "appel le segment tournant");
            sTournant.attendrePositionOK();

            sTournant.entrer(id);
            sAccueil.liberer();
            sTournant.attendrePositionOK();
            pHangars.entrer(sTournant.getPosition(), id);
            sTournant.sortir(id);

        } catch (InterruptedException e) {
            System.out.println("Loco " + id + " interrompue (ne devrait pas arriver)");
        }
    }
}
