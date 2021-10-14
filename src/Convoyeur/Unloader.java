package Convoyeur;

public class Unloader implements Runnable {
    private Cart cart;

    public Unloader(Cart cart) {
        this.cart = cart;
    }

    public void unload() {
        cart.unload(this);
    }

    @Override public void run() {
        while (true) {
            unload();
        }
    }
}
