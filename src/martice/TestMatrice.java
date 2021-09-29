package martice;

import java.io.File;

public class TestMatrice {
    public static void main(String[] args) {
        File file = new File("data/donnees_produit1");
        MatriceEntiere matrice1 = new MatriceEntiere(file);
        file = new File("data/donnees_produit2");
        MatriceEntiere matrice2 = new MatriceEntiere(file);
        try {
            MatriceEntiere result = matrice1.multipication(matrice2);
            System.out.println(result);
        } catch (TaillesNonConcordantesException e) {
            e.printStackTrace();
        }
        file = new File("data/donnees_somme1");
        matrice1 = new MatriceEntiere(file);
        file = new File("data/donnees_somme2");
        matrice2 = new MatriceEntiere(file);
        try {
            MatriceEntiere result = matrice1.add(matrice2);
            System.out.println(result);
        } catch (TaillesNonConcordantesException e) {
            e.printStackTrace();
        }
    }
}
