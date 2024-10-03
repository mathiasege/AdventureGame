package Items;

public class Food extends Item {
    public int healthPoints;


    public Food(String name, String DESCRIPTION, int healthPoints){
        super(name, DESCRIPTION);
        this.healthPoints = healthPoints;
    }
}
