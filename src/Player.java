import java.util.ArrayList;

public class Player {
    private Room currentRoom, lastRoom;
    private boolean firstTeleport;
    // Spørg hvorfor den kan være final?
    // Den ændre sig jo hele tiden.
    private final ArrayList<Item> inventory;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        firstTeleport = false;
        inventory = new ArrayList<>();
    }

    public Room getRoom() {
        return currentRoom;
    }


    // funktionen for at teleport.
    public Room teleport() {
        // opretter en temp variabel og map.
        Room temp;
        Map map = new Map();

        // hvis ikke man har teleporteret sig før.
        if (!firstTeleport) {
            lastRoom = currentRoom;
            currentRoom = map.getBoard();
        } else {
            temp = currentRoom;
            currentRoom = lastRoom;
            lastRoom = temp;
        }

        // set firstTeleport til true
        firstTeleport = true;

        return currentRoom;
    }

    // Tjekker om døren er låst.
    public boolean checkLock(String input) {
        if (currentRoom.getName().equals("Room 3")
                && input.equals("west")
                // Tjekker for true
                && currentRoom.getWest().getLocked())
            return currentRoom.getWest().getLocked();
        if (currentRoom.getName().equals("Room 7")
                && input.equals("east")
                // Tjekker for true.
                && currentRoom.getEast().getLocked())
            return currentRoom.getEast().getLocked();

        // return false
        return currentRoom.getLocked();
    }

    // Metoden for at bevæge sig rundt.
    public Room move(String moveCommand) {
        Room temp = currentRoom;

        // sætter hasVisted for rummet.
        if(!currentRoom.getHasVisited())
            currentRoom.setHasVisited(!currentRoom.getHasVisited());

        // sætter current til nyt rom og indhenter tekst på rom.
        if (moveCommand.equals("north") && currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth();
        } else if (moveCommand.equals("south") && currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
        } else if (moveCommand.equals("east") && currentRoom.getEast() != null) {
            currentRoom = currentRoom.getEast();
        } else if (moveCommand.equals("west") && currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
        }

        return !currentRoom.equals(temp)
                ? currentRoom
                : null;
    }

    // Sætter locked variablen
    public String setLock() {
        // Før jeg sætter variablen, sætter jeg tekst udfra hvad den er nu.
        String result = currentRoom.getLocked()
                ? "You unlocked the door."
                : "The door is not locked.";

        // hvis locked er true. Sæt til false.
        if (currentRoom.getLocked())
            currentRoom.setLocked(!currentRoom.getLocked());

        return result;
    }

    // Del 2
    // Henter items i inventory.
    public String checkInventory(){
        String temp = "";
        for(Item item : inventory){
            temp += item.toString() + ".\n";
        }

        return temp;
    }

    // Returnere sandt eller falsk, hvis item eksistere.
    public Item takeItem(String input){
        for(Item item : currentRoom.getItems()){
            // Fjern fra rum og tilføje til inventory.
            if(item.getName().toLowerCase().equals(input)){
                inventory.add(item);
                currentRoom.removeSpecificItem(item);
                return item;
            }
        }

        return null;
    }

    // Returnere sandt eller falsk, hvis item eksistere.
    public Item dropItem(String input){
        for(Item item : inventory){
            // Fjern fra player og tilføj til rum.
            if(item.getName().toLowerCase().equals(input)){
                inventory.remove(item);
                currentRoom.addOneItem(item);
                return item;
            }
        }
        return null;
    }
}
