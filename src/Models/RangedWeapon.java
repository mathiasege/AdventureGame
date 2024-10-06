package Models;

import java.util.Random;

public class RangedWeapon extends Weapon{
    private int ammunition;

    public RangedWeapon(String name, String DESCRIPTION){
        super(name, DESCRIPTION, new Random().nextInt(2,4));

        this.ammunition = new Random().nextInt(0,10);
    }

    @Override
    public boolean canUse(){
        return ammunition > 0;
    }

    @Override
    public String useAmmo() {
        ammunition--;

        return "You just fired";
    }

    @Override
    public String getAmmo(){
        return "" + ammunition;
    }

    @Override
    public String toString(){
        return getNAME() + ". \nAmmunition: " + ammunition + ". Damage: " + getDamage() + ".";
    }

}
