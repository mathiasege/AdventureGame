package Models;

import java.text.DecimalFormat;
import java.util.Random;

public class Item {
    private final String NAME, DESCRIPTION;
    private final double WEIGHT;

    public Item(String NAME, String DESCRIPTION, double WEIGHT){
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        // Runder til 2 decimaler.
        this.WEIGHT = Math.round(WEIGHT * 100.0) / 100.0;
    }

    public String getNAME() {
        return NAME;
    }

    public double getWeight(){
        return  WEIGHT;
    }

    @Override
    public String toString(){
        return "Item: " + NAME + "." +
                "\nWeight: " + WEIGHT + "." +
                "\nDescription: " + DESCRIPTION + ".";
    }
}

