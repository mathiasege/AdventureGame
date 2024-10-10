import java.util.Random;

public class Food extends Item {
    private int healthPoints;

    public Food(String name, String DESCRIPTION, int healthPoints){
        super(name, DESCRIPTION, new Random().nextDouble(1,10));
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

}
