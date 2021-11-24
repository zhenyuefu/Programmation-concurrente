package ServerMultiThread;

public class Server implements Runnable {

    private ReponseRequete requete;

    public Server() {
        // TODO Auto-generated constructor stub
    }

    private synchronized void waitRequest() throws InterruptedException {
        wait();
    }

    private synchronized void sendReponse() throws InterruptedException {
        Thread.sleep(100);
        requete.getClient().requeteTraitee();
    }

    public synchronized void receiveRequete(ReponseRequete requete) {
        this.requete = requete;
        System.out.println("Recive a request from" + requete.getClient());
        notify();

    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Server : wait request");
                waitRequest();
                sendReponse();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
