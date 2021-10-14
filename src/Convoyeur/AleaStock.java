package Convoyeur;

public class AleaStock {

    private AleaObject[] objects;
    private int count=0;

    public AleaStock(int size) {
        objects = new AleaObject[size];
    }

    public int getSize() {
        return objects.length;
    }

    public boolean add(AleaObject object) {
        if (count < getSize()) {
            objects[count++] = object;
            return true;
        }
        System.out.println("Stock Full!");
        return false;
    }

    public AleaObject getObject() {
        AleaObject o = null;
        count--;
        if (count > 0) {
            o = objects[count];
            objects[count] = null;
        }
        return o;
    }



}
