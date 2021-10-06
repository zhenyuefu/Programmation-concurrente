package spectacle;

public class Test {

    public static void main(String[] args) {
        Salle s = new Salle(50, 50);
        Groupe g1 = new Groupe(50, s);
        Groupe g2 = new Groupe(1500, s);
        Groupe g3 = new Groupe(800, s);
        Groupe g4 = new Groupe(400, s);

        Thread t = new Thread(g1);
        t.start();
        t = new Thread(g3);
        t.start();
        t = new Thread(g4);
        t.start();
        t = new Thread(g2);
        t.start();

    }

}
