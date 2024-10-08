import java.util.ArrayList;

public class Room {
    private final String ROOM_NAME;
    private String description;
    private Room north, south, east, west;
    private boolean isLocked, hasVisited;

    // Det er for rummene man kan besøge
    private boolean northVisited, southVisited, eastVisited, westVisited;

    private ArrayList<Item> items;

    private final ArrayList<Enemy> ENEMIES;

    public Room(String ROOM_NAME, String description) {
        this.ROOM_NAME = ROOM_NAME;
        this.description = description;
        this.isLocked = false;
        hasVisited = false;

        // Det er for rummene man kan besøge
        northVisited = false;
        southVisited = false;
        eastVisited = false;
        westVisited = false;

        items = new ArrayList<>();
        ENEMIES = new ArrayList<>();
    }

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

    public void setWest(Room west) {
        this.west = west;
    }

    public Room getWest() {
        return west;
    }

    public String getROOM_NAME() {
        return ROOM_NAME;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    // Det er for selve rummet.
    public boolean getHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
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

    public boolean getEastIsLocked() {
        return getEast().isLocked;
    }

    public boolean getWestIsLocked() {
        return getWest().isLocked;
    }


    // Del 2
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return ROOM_NAME + ".\n" + description;
    }

    // Del 5
    public void setENEMIES(Enemy ENEMIES) {
        this.ENEMIES.add(ENEMIES);
    }

    public ArrayList<Enemy> getENEMIES() {
        return ENEMIES;
    }

    public void addItem(Item item){
        items.add(item);
    }

    // Henter den som er tættest.
    public Enemy getClosestEnemy() {
        Enemy closestEnemy = null;

        // Looper på List.
        for (Enemy enemy : ENEMIES) {
            if (closestEnemy == null)
                closestEnemy = enemy;

            // indsætter den som er tættest.
            if (closestEnemy.getMETERS() > enemy.getMETERS()) {
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }

    // Henter specifik fjende.
    public Enemy getSpecificEnemy(String input) {
        for (Enemy enemy : ENEMIES) {
            if (enemy.getNAME().equalsIgnoreCase(input))
                return enemy;
        }
        return null;
    }
}
