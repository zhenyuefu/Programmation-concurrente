package Convoyeur;

public class Loader implements Runnable {
    private AleaStock stock;
    private Cart cart;

    public Loader(AleaStock stock, Cart cart) {
        this.stock = stock;
        this.cart = cart;
    }

    public void run() {
        AleaObject o = stock.getObject();
        while (o != null) {
            try {
                cart.add(o);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
