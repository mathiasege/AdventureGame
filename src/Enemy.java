import java.util.Random;

public class Enemy {
    private final String NAME;
    private int health;
    private final Weapon WEAPON;
    private final int METERS;

    public Enemy(String NAME, int health, Weapon WEAPON){
        this.health = health;
        this.NAME = NAME;
        this.WEAPON = WEAPON;
        METERS = new Random().nextInt(0,10);
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

    public Weapon getWEAPON() {
        return WEAPON;
    }

    public void useAmmo(){
        getWEAPON().useAmmunition();
    }

    public int getMETERS(){
        return METERS;
    }

    public String getNAME() {
        return NAME;
    }

    @Override
    public String toString(){
        return NAME + ":" + "\n- Health: " + health + "\n- " + WEAPON + "\n- Meters: " + METERS + ".";
    }
}
