package ServerMultiThread;

public class Main {
    private static final int NB_CLIENTS = 5;
    
    
    public static void main(String[] args) {
        Server server = new Server();
        new Thread(server).start();
        
        for (int i = 0; i < NB_CLIENTS ; i++) {
            new Thread(new Client(server)).start();
        }
    }
}
