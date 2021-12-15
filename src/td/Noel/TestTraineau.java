package td.Noel;

import java.util.concurrent.CyclicBarrier;

public class TestTraineau {
    public static void main(String[] args) {
        final int NB_RENNES = 9;
        PereNoel santa = new PereNoel(NB_RENNES);
        CyclicBarrier cb = new CyclicBarrier(NB_RENNES);
        new Thread(santa).start();
        for (int i = 0; i < NB_RENNES; i++) {
            new Thread(new Renne(santa, cb)).start();
        }
    }
}
