package ServerMultiThread;

public class Server implements Runnable {

    private ReponseRequete requete;
    private boolean wait = true;

    public Server() {
        // TODO Auto-generated constructor stub
    }

    private synchronized void waitRequest() throws InterruptedException {
            while(wait) {
                wait();
            }
    }

    private synchronized void sendReponse() throws InterruptedException {
        while (!wait) {
            Thread.sleep(100);
            wait = true;
            System.out.println("Server: send reponse to " + requete.getClient());
            requete.getClient().requeteTraitee();
        }
    }

    public synchronized void receiveRequete(ReponseRequete requete) throws InterruptedException {
        while (!wait) {
            wait();
        }
        this.requete = requete;
        System.out.println("Recive a request from " + requete.getClient());
        wait = false;
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
