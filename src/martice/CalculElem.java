package martice;

import java.util.concurrent.Callable;

public class CalculElem implements Callable<MatriceElem> {
    private MatriceEntiere m1, m2;
    private int indL, indC;

    public CalculElem(int i, int j, MatriceEntiere m1, MatriceEntiere m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.indL = i;
        this.indC = j;
    }

    public MatriceElem call() throws TaillesNonConcordantesException {
        return new MatriceElem(indL, indC, Integer.valueOf(MatriceEntiere.produitLigneColonne(m1, indL, m2, indC)));
    }

}
