package Convoyeur;

public class Loader implements Runnable {
    private AleaStock stock;
    private Cart cart;
    private static int counter = 1;
    private int id;

    public Loader(AleaStock stock, Cart cart) {
        this.stock = stock;
        this.cart = cart;
        id = counter++;
    }

    @Override public void run() {
        AleaObject o = stock.getObject();
        while (o != null) {
            try {
                cart.add(o);
                System.out.println("Loader " + id + " added object " + o);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            o = stock.getObject();
        }

    }

}
