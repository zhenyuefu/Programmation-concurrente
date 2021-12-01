package td.Producteur;

public class TestPC {
    public static void main(String[] args) {
        Buffer b = new Buffer(5);

        Producteur p = new Producteur(b);
        Consommateur c = new Consommateur(b);

        Thread tp = new Thread(p);
        Thread tc = new Thread(c);
        try {
            tp.start();
            tc.start();
            tp.join();
            tc.join();
            System.out.println(b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
