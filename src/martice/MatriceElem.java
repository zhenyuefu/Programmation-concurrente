package martice;

public class MatriceElem {
    private int i, j, val;

    public MatriceElem(int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
