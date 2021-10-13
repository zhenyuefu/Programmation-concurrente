package Convoyeur;

public class Unloader{
    private Cart cart;

    public Unloader(Cart cart) {
        this.cart = cart;
    }

    public void unload(){
        cart.unload();
    }


}
