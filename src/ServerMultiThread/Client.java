package ServerMultiThread;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable{

    
    private int id;
    private Server server;
    private ReponseRequete requete;
    private final Object waitReponseLock = new Object();
    
    private static AtomicInteger cpt = new AtomicInteger();
    private static AtomicInteger clientActif = new AtomicInteger();
    
    public Client(Server s) {
        id = cpt.incrementAndGet();
        clientActif.getAndIncrement();
        requete = new ReponseRequete(this);
        server = s;
    }

    private void waitReponse() throws InterruptedException {
        synchronized (waitReponseLock) {
            waitReponseLock.wait();
        }
    }

    public void requeteTraitee() {
        synchronized (waitReponseLock) {
            waitReponseLock.notify();
        }
    }
    
    private void sendRequete() {
        server.receiveRequete(requete);
    }
    
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Client " + id;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Client "+ id + " init");
            sendRequete();
            System.out.println("Client "+ id + " send the request to the server. Wait reponse...");
            waitReponse();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Client "+ id + " recieved reponse.");
    }
}
