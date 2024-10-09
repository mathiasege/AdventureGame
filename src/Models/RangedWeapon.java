package Models;

import java.util.Random;

public class RangedWeapon extends Weapon{

    public RangedWeapon(String name, String DESCRIPTION){
        super(name, DESCRIPTION, new Random().nextInt(10,15), new Random().nextInt(2,7));
    }

    @Override
    public boolean canAttack(){
        return getAmmo() > 0;
    }

    @Override
    public void useAmmunition() {
        useOneAmmo();
    }

    @Override
    public int getAmmunition(){
        return getAmmo();
    }

    @Override
    public String toString(){
        return NAME + ": \n- Ammunition: " + ammo + ". Damage: " + damage + ".";
    }

}
