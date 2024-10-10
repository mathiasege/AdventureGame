import java.util.Random;

public abstract class Weapon extends Item {
    private final int DAMAGE;
    private int ammo;

    public Weapon(String name, String DESCRIPTION, int DAMAGE, int ammo){
        super(name, DESCRIPTION, new Random().nextDouble(10,20));
        this.ammo = ammo;
        this.DAMAGE = DAMAGE;
    }

    public int getDAMAGE(){
        return DAMAGE;
    }

    public int getAmmo() {
        return ammo;
    }
    public void useOneAmmo() {
        this.ammo--;
    }

    public abstract boolean canAttack();
    public abstract void useAmmunition();
    public abstract int getAmmunition();

}