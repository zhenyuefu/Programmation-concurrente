package ServerMultiThread;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable {

    private int id;
    private Server server;
    private ReponseRequete requete;
    private boolean receive;

    private static AtomicInteger cpt = new AtomicInteger();
    private static AtomicInteger clientActif = new AtomicInteger();

    public Client(Server s) {
        id = cpt.incrementAndGet();
        clientActif.getAndIncrement();
        requete = new ReponseRequete(this);
        server = s;
    }

    private synchronized void waitReponse() throws InterruptedException {
        while (!receive)
            wait();
    }

    public synchronized void requeteTraitee() {
        receive = true;
        System.out.println("Client " + id + " recieved reponse.");
        clientActif.decrementAndGet();
        notify();
    }

    private synchronized void sendRequete() throws InterruptedException {
        server.receiveRequete(requete);
        System.out.println("Client " + id + " send the request to the server. Wait reponse...");
    }

    @Override
    public String toString() {
        return "Client " + id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client " + id + " init");
            sendRequete();
            waitReponse();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
