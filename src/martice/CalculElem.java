package martice;

public class CalculElem implements Runnable {
    private MatriceEntiere m1, m2, res;
    private int i, j;

    public CalculElem(MatriceEntiere m1, int i, MatriceEntiere m2, int j, MatriceEntiere res) {
        this.m1 = m1;
        this.m2 = m2;
        this.res = res;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        try {
            res.setElem(i, j, MatriceEntiere.produitLigneColonne(m1, i, m2, j));
        } catch (TaillesNonConcordantesException e) {
            e.printStackTrace();
        }

    }

}
