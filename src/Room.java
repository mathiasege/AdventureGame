public class Room {
    private String roomName;
    private String description;
    private boolean locked;
    private boolean hasVisited;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(String roomName, String description){
        this.roomName = roomName;
        this.description = description;
        this.locked = false;
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

    public boolean getLocked() {
        return locked;
    }
    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public boolean getHasVisited() {
        return hasVisited;
    }
    public boolean setHasVisited(boolean hasVisited) {
        return this.hasVisited = hasVisited;
    }
}
