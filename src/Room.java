public class Room {
    private String roomName;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    // Del 1 EKSTRA
    private boolean locked;
    private boolean hasVisited;


    public Room(String roomName, String description){
        this.roomName = roomName;
        this.description = description;

        // del 1 EKSTRA
        locked = false;
        hasVisited = false;
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

    public String getRoomName() {
        return roomName;
    }

    public String getDescription(){
        return description;
    }

    // ---------- DEL 1 EKSTRA ---------------------
    public boolean getLocked() {
        return locked;
    }
    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public boolean getHasVisited() {
        return hasVisited;
    }
    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }
}
