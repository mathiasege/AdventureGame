public class Adventur {
    private Map map;
    private Player player;

    public Adventur(){
        // Istansiere mine variabler.
        map = new Map();
        player = new Player(map.getRoom());
    }

    public String move(String input){
        Room room = player.move(input);

        //Kontrollere om result er tomt.
        String result = room != null
                ? player.setLock(false) + " " + setRoomText(room)
                : "You cannot go that way.";

        if(room != null)
            player.setVisit(room);

        return result;
    }
    // sætter teksten udfra room.
    private String setRoomText(Room room){
        return !room.getHasVisited()
                ? "You moved on to: " + room.getRoomName() + "\n" +  room.getDescription()
                : room.getRoomName() + "\nBeen there done that.";
    }


    public boolean checkLock(String input){
        return player.checkLock(input);
    }

    public String checkLocation(){
        return player.checkLocations();
    }

    public Player getPlayer() {
        return player;
    }
}
