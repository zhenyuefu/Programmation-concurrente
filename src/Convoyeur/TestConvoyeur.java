package Convoyeur;

public class TestConvoyeur {
    
    public static void main(String[] args) {
        int stockSize = 20;
        AleaStock stock = new AleaStock(stockSize);

        for (int i = 0; i <stockSize;i++) {
            stock.add(new AleaObject());
        }
        Cart cart = new Cart(300, 5);

        Loader l1 = new Loader(stock, cart);
        Loader l2 = new Loader(stock, cart);

        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);

        t1.start();
        t2.start();

        Unloader u1 = new Unloader(cart);
        Thread tu = new Thread(u1);

        tu.start();
        
    }
}
