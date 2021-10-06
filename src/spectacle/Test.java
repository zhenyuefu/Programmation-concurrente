package spectacle;

public class Test {

    public static void main(String[] args) {
        Salle s = new Salle(5, 7);
        Groupe g1 = new Groupe(5, s);
        Groupe g2 = new Groupe(15, s);
        Groupe g3 = new Groupe(10, s);
        Groupe g4 = new Groupe(8, s);

        Thread t = new Thread(g1);
        t.start();
        t = new Thread(g3);
        t.start();
        t = new Thread(g4);
        t.start();
        t = new Thread(g2);
        t.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(s);
    }

}
