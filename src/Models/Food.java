package Models;

import java.util.Random;

public class Food extends Item {
    public int healthPoints;

    public Food(String name, String DESCRIPTION, int healthPoints){
        super(name, DESCRIPTION, new Random().nextInt(1,10));
        this.healthPoints = healthPoints;
    }
}
