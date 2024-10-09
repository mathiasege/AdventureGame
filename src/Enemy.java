import java.util.Random;

public class Enemy {
    private String name;
    private int health;
    private Weapon weapon;
    private int meters;

    public Enemy(String name, int health, Weapon weapon){
        this.health = health;
        this.name = name;
        this.weapon = weapon;
        meters = new Random().nextInt(0,10);
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void useAmmo(){
        getWeapon().useAmmunition();
    }

    public int getMeters(){
        return meters;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name + ":" + "\n- Health: " + health + "\n- " + weapon + "\n- Meters: " + meters + ".";
    }
}
