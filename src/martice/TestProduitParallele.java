package martice;

import java.io.File;

public class TestProduitParallele {
    public static void main(String[] args) {
        File file = new File("data/donnees_produit1");
        MatriceEntiere matrice1 = new MatriceEntiere(file);
        file = new File("data/donnees_produit2");
        MatriceEntiere matrice2 = new MatriceEntiere(file);
        Thread t = null;
        MatriceEntiere m = new MatriceEntiere(matrice1.getLignes(), matrice2.getColonnes());
        for (int i = 0; i < matrice1.getLignes(); i++) {
            for (int j = 0; j < matrice2.getColonnes(); j++) {
                CalculElem c = new CalculElem(matrice1, i, matrice2, j, m);
                t = new Thread(c);
                t.start();
            }
        }
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        System.out.println(m);
    }
}
