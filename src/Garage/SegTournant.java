package Garage;

public class SegTournant implements Runnable {
    private int position;
    private int pDest;
    private PoolHangars poolH;

    public int getPosition() {
        return position;
    }

    public void appeler(int pos) {
        pDest = position;
    }

    public void attendreAppel() {
        
    }

    public void entrer(int id) {
        appeler(id);
    }

    public void attendrePositionOK()  {
        while (position!=pDest) {
            
        }
    }
    
    public void seDeplacer() throws InterruptedException {
        Thread.sleep(1000);
        position = pDest;
    }

    public void attendreEntree() {

    }

    public void attendreVide() {

    }

    @Override
    public void run() {
        try {
            while (true) {
                attendreAppel();
                seDeplacer();
                attendreEntree();
                pDest = poolH.chercherDispo();
                seDeplacer();
                attendreVide();
            }
        } catch (InterruptedException e) {
            System.out.println("Terminaison sur interruption du segment tournant");
        }
    }
}
