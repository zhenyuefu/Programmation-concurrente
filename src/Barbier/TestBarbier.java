package Barbier;

public class TestBarbier {

    public static void main(String[] args) {
        Barbier barbier = new Barbier();
        Salle.getInstance().setBarbier(barbier);
        Thread barbierThread = new Thread(barbier);
        barbierThread.start();
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            new Thread(client).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
