public class Item {
    private final String NAME, DESCRIPTION;

    public Item(String name, String DESCRIPTION){
        this.NAME = name;
        this.DESCRIPTION = DESCRIPTION;
    }
    public String getNAME() {
        return NAME;
    }

    @Override
    public String toString(){
        return "Item: " + NAME + ". Description: " + DESCRIPTION;
    }
}

