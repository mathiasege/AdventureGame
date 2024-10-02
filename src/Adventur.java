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
        return player.teleport();
    }

    public String getLock(){
        // Før jeg sætter variablen, sætter jeg tekst udfra hvad den er nu.
        String result = player.getRoomIsLocked()
                ? "You unlocked the door."
                : "The door is not locked.";

        player.setLockIfTrue();

        return result;
    }

    // Metoden for at gå.
    public String move(String input){
        Room temp = player.getRoom();
        // Sætter visited for rum du kan tilgå
        setVisited(input);

        // Henter current for at rette description til "Been there done that."
        Room room = player.move(input);

        // Tjekker om room er skiftet. Returnere string udfra det.
        return !room.equals(temp)
                ? roomText(room)
                : "You cannot go that way.";
    }
    // sætter true på rum du er tilgået.
    private void setVisited(String input){
        // Sætter alle til modsat af den værdi de har.
        if(input.equals("north") && !player.getRoomIsNorthVisited()){
            player.setRoomIsNorthVisited(!player.getRoomIsNorthVisited());
        }
        if(input.equals("south") && !player.getRoomIsSouthVisited()){
            player.setRoomIsSouthVisited(!player.getRoomIsSouthVisited());
        }
        if(input.equals("east") && !player.getRoomIsEastVisited()){
            player.setRoomIsEastVisited(!player.getRoomIsEastVisited());
        }
        if(input.equals("west") && !player.getRoomIsWestVisited()){
            player.setRoomIsWestVisited(!player.getRoomIsWestVisited());
        }
    }
    // Giver tekst tilbage.
    private String roomText(Room room){
        // Hvis du ikke har været der.
        if(!room.getHasVisited())
                return "You moved on to: " + room.toString();

        // Sætter description hvis du har været der.
        String newDescription = "Been there done that.";
        player.setRoomDescription(newDescription);

        return player.getRoom().toString();
    }

    public boolean checkLock(){
        return player.checkLock();
    }

    // Metoden sender result tilbage, når alle rum er blevet besøgt.
    public String locationStatus() {
        // opretter lokale variabler.
        String result = "";

        // Hvis rummet er forskellig for null
        if(player.getRoomNorth() != null){
            result += "You can go north.\n";
        }
        if(player.getRoomSouth() != null){
            result += "You can go south.\n";
        }
        if(player.getRoomEast() != null){
            result += "You can go east.\n";
        }
        if(player.getRoomWest() != null){
            result += "You can go north.\n";
        }

        // result kommer kun tilbage hvis alle er true.
        return player.getRoomIsNorthVisited()
                && player.getRoomIsSouthVisited()
                && player.getRoomIsEastVisited()
                && player.getRoomIsWestVisited()
                ? result + "All rooms have been visited."
                : "";
    }

    // Del 2
    // Gennemgår items i et rum
    public String checkForItem(){
        String temp = "";

        // henter items.
        for(var item : player.getRoomItems()){
            temp += item.toString() + "\n";
        }

        // returner ingenting, hvis temp er tom. ellers temp.
        return temp.isEmpty()
                ? ""
                : temp;
    }

    // Returnere en besked udfra en String fra player.checkItem.
    public String checkInventory(){
        return player.checkInventory().isEmpty()
                ? "Inventory is empty."
                : "Your inventory:\n" + player.checkInventory();
    }

    // Returnere en besked udfra en bool fra player.takeItem.
    public String takeItem(String input){
        Item item = player.takeItem(input);
        return item != null
                ? "You took the item: " + item.toString()
                :  "There is nothing like " + input + " to take around here";
    }
    // Returnere en besked udfra en bool fra player.dropItem.
    public String dropItem(String input){
        Item item = player.dropItem(input);
        return item != null
                ? "You dropped the item: " + item.toString()
                : "You don't have anything like " + input + " in your inventory";
    }


    // ------------------------- GET / SET -------------------------
    public Room getRoom() {
        return player.getRoom();
    }

    public boolean getRoomHasVisited(){
        return getRoom().getHasVisited();
    }
    // ----------------------------------------------------------
}
