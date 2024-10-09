package Models;

import java.util.Random;

public abstract class Weapon extends Item {
    protected final int damage;
    protected int ammo;

    public Weapon(String name, String DESCRIPTION, int damage, int ammo){
        super(name, DESCRIPTION, new Random().nextDouble(10,20));
        this.ammo = ammo;
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public int getAmmo() {
        return ammo;
    }
    protected void useOneAmmo() {
        this.ammo--;
    }

    public abstract boolean canAttack();
    public abstract void useAmmunition();
    public abstract int getAmmunition();

}