public class Adventur {
    private Map map;
    private Player player;


    public Adventur(){
        // Istansiere mine variabler.
        map = new Map();
        player = new Player(map.getBoard());
    }

    // returnere navnet på det nye rum.
    public String teleport(){
        return player.teleport().getName();
    }

    // Metoden for at gå.
    public String move(String input){
        // Sætter visited for rum du kan tilgå
        setVisited(input);

        // Henter current for at rette description til "Been there done that."
        Room room = player.move(input);

        // Tjekker om room er tomt. Sætter string udfra det.
        String result = room != null
                ? player.setLock() + " " + setRoomText(room)
                : "You cannot go that way.";

        return result;
    }
    // sætter true på rum du er tilgået.
    private void setVisited(String input){
        if(input.equals("north") && !player.getRoom().isNorthVisited()){
            player.getRoom().setNorthVisited(!player.getRoom().isNorthVisited());
        }
        if(input.equals("south") && !player.getRoom().isSouthVisited()){
            player.getRoom().setSouthVisited(!player.getRoom().isSouthVisited());
        }
        if(input.equals("east") && !player.getRoom().isEastVisited()){
            player.getRoom().setEastVisited(!player.getRoom().isEastVisited());
        }
        if(input.equals("west") && !player.getRoom().isWestVisited()){
            player.getRoom().setWestVisited(!player.getRoom().isWestVisited());
        }
    }
    // Giver tekst tilbage.
    private String setRoomText(Room room){
        // Den sættes efter om man har været der.
        return !room.getHasVisited()
                ? "You moved on to: " + room.getName() + "\n" + room.getDescription()
                : room.getName() + "\nBeen there done that.";
    }

    public boolean checkLock(String input){
        return player.checkLock(input);
    }

    public String checkLocation(){
        return checkLocations();
    }
    // Metoden sender result tilbage, når alle rum er blevet besøgt.
    private String checkLocations() {
        // opretter lokale variabler.
        String result = "";

        // Hvis rummet er forskellig for null
        if(player.getRoom().getNorth() != null){
            result += "You can go north.\n";
        }

        // Hvis rummet er forskellig for null
        if(player.getRoom().getSouth() != null){
            result += "You can go south.\n";
        }

        // Hvis rummet er forskellig for null
        if(player.getRoom().getEast() != null){
            result += "You can go east.\n";
        }

        // Hvis rummet er forskellig for null
        if(player.getRoom().getWest() != null){
            result += "You can go north.\n";
        }

        // result kommer kun tilbage hvis countTrue er = med count.
        return player.getRoom().isNorthVisited()
                && player.getRoom().isSouthVisited()
                && player.getRoom().isEastVisited()
                && player.getRoom().isWestVisited()
                ? result + "\nAll rooms have been visited"
                : "";
    }

    public Player getPlayer() {
        return player;
    }
}
