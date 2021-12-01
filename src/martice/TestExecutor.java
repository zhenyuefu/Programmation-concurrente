package martice;

import java.io.File;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {
    public static void main(String[] args) {
        final int NB_THREADS = 5;
        try {
            File file = new File("data/donnees_produit1");
            MatriceEntiere matrice1 = new MatriceEntiere(file);
            file = new File("data/donnees_produit2");
            MatriceEntiere matrice2 = new MatriceEntiere(file);
            MatriceEntiere m = new MatriceEntiere(matrice1.getLignes(), matrice2.getColonnes());

            final ExecutorService exec = Executors.newFixedThreadPool(NB_THREADS);
            CompletionService<MatriceElem> ecs = new ExecutorCompletionService<>(exec);
            for (int i = 0; i < m.getLignes(); i++) {
                for (int j = 0; j < m.getColonnes(); j++) {
                    ecs.submit(new CalculElem(i, j, matrice1, matrice2));
                }
            }
            exec.shutdown();
            for (int i = 0; i < m.getLignes(); i++) {
                for (int j = 0; j < m.getColonnes(); j++) {
                    MatriceElem elem = ecs.take().get();
                    m.setElem(elem.getI(), elem.getJ(), elem.getVal());
                }
            }

            m.affiche();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
