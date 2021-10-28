package Garage;

public class Loco implements Runnable {
    private SegAccueil sAccueil;
    private SegTournant sTournant;


    @Override
    public void run() {
        try {
            sAccueil.reserver();
            sTournant.appeler(0);
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
