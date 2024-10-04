package Models;

import java.util.Random;

public class Item {
    private final String NAME, DESCRIPTION;
    private final int WEIGHT;

    public Item(String NAME, String DESCRIPTION, int WEIGHT){
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.WEIGHT = WEIGHT;
    }


    public String getNAME() {
        return NAME;
    }

    public int getWeight(){
        return WEIGHT;
    }

    @Override
    public String toString(){
        return "Item: " + NAME + ". " + DESCRIPTION;
    }
}

