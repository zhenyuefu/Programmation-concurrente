package Convoyeur;

import java.util.Random;

public class AleaObject {
    private static int counter = 0;
    private int index;
    private int weight;
    private static final int weight_min = 0;
    private static final int weight_max = 100;

    public AleaObject() {
        Random rand = new Random();
        weight = rand.nextInt(weight_max - weight_min) + weight_min;
        index = counter++;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Object:"+index+" weight:"+weight;
    }

}