import java.util.Random;

public class Food extends Item {
    private final int HEALTH_POINTS;

    public Food(String name, String DESCRIPTION, int HEALTH_POINTS){
        super(name, DESCRIPTION, new Random().nextDouble(1,10));
        this.HEALTH_POINTS = HEALTH_POINTS;
    }

    public int getHEALTH_POINTS() {
        return HEALTH_POINTS;
    }

}
