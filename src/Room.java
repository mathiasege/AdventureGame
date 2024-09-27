public class Room {
    private String roomName, description;
    private Room north, south, east, west;

    // del 2. hasVisited er for selve rummet
    private boolean locked, hasVisited;

    // Det er for rummene man kan besøge
    private boolean northVisited, southVisited,eastVisited,westVisited;

    public Room(String roomName, String description){
        this.roomName = roomName;
        this.description = description;

        // del 2. hasVisited er for selve rummet
        this.locked = false;
        hasVisited = false;

        // Det er for rummene man kan besøge
        northVisited = false;
        southVisited = false;
        eastVisited = false;
        westVisited = false;
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

    public String getName() {
        return roomName;
    }

    public String getDescription(){
        return description;
    }

    public boolean getLocked() {
        return locked;
    }
    public void setLocked(boolean locked){
        this.locked = locked;
    }

    // Det er for selve rummet.
    public boolean getHasVisited() {
        return hasVisited;
    }
    public void setHasVisited(boolean hasVisited) {
        // Sætter til modsat.
        this.hasVisited = hasVisited;
    }
}
