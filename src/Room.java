public class Room {
    private String roomName;
    private String description;
    private Room roomOne;
    private Room roomTwo;
    private Room roomThree;

    public Room(String roomName, String description){
        this.roomName = roomName;
        this.description = description;
    }

    public void setRoomOne(Room roomOne) {
        this.roomOne = roomOne;
    }

    public void setRoomTwo(Room roomTwo) {
        this.roomTwo = roomTwo;
    }

    public void setRoomThree(Room roomThree) {
        this.roomThree = roomThree;
    }
}
