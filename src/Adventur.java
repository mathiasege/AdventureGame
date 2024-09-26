public class Adventur {
    private Map map;
    private Player player;

    public Adventur(){
        // Istansiere mine variabler.
        map = new Map();
        player = new Player(map.getRoom());
    }

    // Metoden for at gå.
    public String move(String input){
        // Henter current for at rette description til "Been there done that."
        Room room = player.getCurrent();

        // Hvis visited er false og room er null.
        if(room != null && !player.getHasVisited())
            player.setVisit(player.getCurrent());

        room = player.move(input);

        // Tjekker om room er tomt.
        String result = room != null
                ? player.setLock() + " " + setRoomText(room)
                : "You cannot go that way.";


        return result;
    }
    // Giver tekst tilbage.
    private String setRoomText(Room room){
        // Den sættes efter om man har været der.
        return !room.getHasVisited()
                ? "You moved on to: " + room.getRoomName() + "\n" + room.getDescription()
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
