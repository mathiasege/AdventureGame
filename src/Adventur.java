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

        // Tjekker om room er tomt. Returnere string udfra det.
        return room != null
                ? player.setLock() + " " + setRoomText(room)
                : "You cannot go that way.";
    }
    // sætter true på rum du er tilgået.
    private void setVisited(String input){
        //
        // Sætter alle til modsat af den værdi de har.
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

    // Metoden sender result tilbage, når alle rum er blevet besøgt.
    public String locationStatus() {
        // opretter lokale variabler.
        String result = "";

        // Hvis rummet er forskellig for null
        if(player.getRoom().getNorth() != null){
            result += "You can go north.\n";
        }
        if(player.getRoom().getSouth() != null){
            result += "You can go south.\n";
        }
        if(player.getRoom().getEast() != null){
            result += "You can go east.\n";
        }
        if(player.getRoom().getWest() != null){
            result += "You can go north.";
        }

        // result kommer kun tilbage hvis alle er true.
        return player.getRoom().isNorthVisited()
                && player.getRoom().isSouthVisited()
                && player.getRoom().isEastVisited()
                && player.getRoom().isWestVisited()
                ? result + "All rooms have been visited."
                : "";
    }

    public Player getPlayer() {
        return player;
    }

    // Del 2
    // Gennemgår items i et rum
    public String checkForItem(){
        String temp = "";

        // henter items.
        for(var item : player.getRoom().getItems()){
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
}
