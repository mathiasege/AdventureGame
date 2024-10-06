package Models;

import java.util.Random;

public class MeeleWeapon extends Weapon {

    public MeeleWeapon(String name, String DESCRIPTION){
        super(name, DESCRIPTION, new Random().nextInt(4,6));
    }

    @Override
    public boolean canUse(){
        return true;
    }

    @Override
    public String useAmmo() {
        return "You just attacked";
    }

    @Override
    public String getAmmo(){
        return "Infinity ammo";
    }

    @Override
    public String toString(){
        return getNAME() + ". \nDamage: " + getDamage() + ".";
    }

}
