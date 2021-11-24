package ServerMultiThread;

import java.util.Random;

public class ReponseRequete {
    
    private Client client;
    private int result;
    
    public ReponseRequete(Client c) {
        Random rnd = new Random();
        client = c;
        result = rnd.nextInt(1000);
    }
    
    public Client getClient() {
        return client;
    }
    
    @Override
    public String toString() {
        return client + String.valueOf(result);
    }
    
}
