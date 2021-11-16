package Barbier;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Salle {

    private static final int MAX_CHAIR = 10;
    private static final Salle salle = new Salle();
    private final BlockingQueue<Client> chair;
    private Barbier barbier;

    private Salle() {
        chair = new ArrayBlockingQueue<>(MAX_CHAIR);
    }

    public static Salle getInstance() {
        return salle;
    }

    public void setBarbier(Barbier barbier) {
        this.barbier = barbier;
    }

    public void enter(Client c) throws InterruptedException {
        if (!isFull()) {
            chair.put(c);
            System.out.println(c + " est entré");
            printChairs();
            barbier.clientArrived();
        } else {
            System.out.println("La salle est pleine " + c + " sorti");
        }
    }

    public Client recupere() throws InterruptedException {
        Client c = chair.take();
        System.out.println(c + " est recupere");
        printChairs();
        return c;
    }

    public boolean isFull() {
        return chair.remainingCapacity() == 0;
    }

    public boolean isEmpty() {
        return chair.isEmpty();
    }

    public void printChairs() {
        synchronized (chair) {
            System.out.println("----------------------------------------------------");
            System.out.println(chair);
            System.out.println("----------------------------------------------------");
        }
    }

}
