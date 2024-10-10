import java.util.Random;

public class MeeleWeapon extends Weapon {

    public MeeleWeapon(String name, String DESCRIPTION){
        super(name, DESCRIPTION, new Random().nextInt(7,12),Integer.MAX_VALUE);
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
        return super.getNAME() + ": \n- Damage: " + super.getDAMAGE() + ".";
    }

}
