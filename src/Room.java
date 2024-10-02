import java.util.ArrayList;

public class Room {
    private final String ROOM_NAME, DESCRIPTION;
    private Room north, south, east, west;

    // del 1 Ekstra. hasVisited er for selve rummet
    private boolean isLocked, hasVisited;

    // Det er for rummene man kan besøge
    private boolean northVisited, southVisited,eastVisited,westVisited;

    // Del 2
    private ArrayList<Item> items;

    public Room(String ROOM_NAME, String DESCRIPTION){
        this.ROOM_NAME = ROOM_NAME;
        this.DESCRIPTION = DESCRIPTION;

        // del 2. hasVisited er for selve rummet
        this.isLocked = false;
        hasVisited = false;

        // Det er for rummene man kan besøge
        northVisited = false;
        southVisited = false;
        eastVisited = false;
        westVisited = false;

        items = new ArrayList<>();
    }

    public void setNorthVisited(boolean northVisited) {
        this.northVisited = northVisited;
    }
    public boolean isNorthVisited() {
        return northVisited;
    }
    public void setSouthVisited(boolean southVisited) {
        this.southVisited = southVisited;
    }
    public boolean isSouthVisited() {
        return southVisited;
    }
    public void setEastVisited(boolean eastVisited) {
        this.eastVisited = eastVisited;
    }
    public boolean isEastVisited() {
        return eastVisited;
    }
    public void setWestVisited(boolean westVisited) {
        this.westVisited = westVisited;
    }
    public boolean isWestVisited() {
        return westVisited;
    }

    // Laver getter og setter for alle.
    public void setNorth(Room north) {
        this.north = north;
    }
    public Room getNorth() {
        return north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }
    public Room getSouth() {
        return south;
    }

    public void setEast(Room east) {
        this.east = east;
    }
    public Room getEast() {
        return east;
    }

    public void setWest(Room west){
        this.west = west;
    }
    public Room getWest() {
        return west;
    }

    public String getROOM_NAME() {
        return ROOM_NAME;
    }

    public String getDESCRIPTION(){
        return DESCRIPTION;
    }

    public boolean getIsLocked() {
        return isLocked;
    }
    public void setIsLocked(boolean isLocked){
        this.isLocked = isLocked;
    }

    // Det er for selve rummet.
    public boolean getHasVisited() {
        return hasVisited;
    }
    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    // Del 2
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public boolean addOneItem(Item item){
        return items.add(item);
    }

    public void removeSpecificItem(Item item){
        items.remove(item);
    }
}
