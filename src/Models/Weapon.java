package Models;

import java.util.Random;

public abstract class Weapon extends Item {
    private final int damage;

    public Weapon(String name, String DESCRIPTION, int damage){
        super(name, DESCRIPTION, new Random().nextDouble(10,20));
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public abstract boolean canUse();
    public abstract String useAmmo();
    public abstract String getAmmo();

}