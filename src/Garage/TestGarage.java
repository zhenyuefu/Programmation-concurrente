package Garage;

public class TestGarage {

    public static void main(String[] args) {
        PoolHangars poolH = new PoolHangars(10);
        SegTournant sTournant = new SegTournant(poolH);
        SegAccueil sAccueil = new SegAccueil();

        Thread sTournantThread = new Thread(sTournant);
        sTournantThread.start();
        for (int i = 0; i < 10; i++) {
            (new Thread(new Loco(sAccueil, sTournant, poolH))).start();
        }
    }
}
