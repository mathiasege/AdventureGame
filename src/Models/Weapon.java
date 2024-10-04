package Models;

import java.util.Random;

public class Weapon extends Item {

    public Weapon(String name, String DESCRIPTION){
        super(name, DESCRIPTION, new Random().nextInt(10,20));
    }

}