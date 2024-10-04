package Models;

import java.util.Random;

public class Item {
    private final String NAME, DESCRIPTION;
    private int weight;

    public Item(String name, String DESCRIPTION){
        this.NAME = name;
        this.DESCRIPTION = DESCRIPTION;
        if(this instanceof Food)
            weight = new Random().nextInt(1,10);
        else
            weight = new Random().nextInt(10,20);

    }
    public String getNAME() {
        return NAME;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public String toString(){
        return "Item: " + NAME + ". " + DESCRIPTION;
    }
}

