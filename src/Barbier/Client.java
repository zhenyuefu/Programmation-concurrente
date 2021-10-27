package Barbier;

public class Client implements Runnable {

    private static int cpt = 0;
    private int id;

    public Client() {
        id = cpt++;
    }

    @Override
    public void run() {
        try {
            Salle.getInstance().getChair(this);
            
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
