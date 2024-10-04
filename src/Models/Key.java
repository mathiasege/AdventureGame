package Models;

import java.util.Random;

public class Key extends Item{

    public Key(String NAME, String DESCRIPTION){
        super(NAME,DESCRIPTION,  new Random().nextDouble(0.1,3));

    }
}
