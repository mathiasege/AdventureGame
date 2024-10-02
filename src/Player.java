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

    // funktionen for at teleport.
    public String teleport() {
        // opretter en temp variabel og map.
        Room temp;
        Map map = new Map();

        // hvis ikke man har teleporteret sig før.
        if (!firstTeleport) {
            lastRoom = getRoom();
            currentRoom = map.getBoard();
        } else {
            temp = getRoom();
            currentRoom = lastRoom;
            lastRoom = temp;
        }

        // set firstTeleport til true
        firstTeleport = true;

        return getROOM_NAME();
    }

    // Tjekker om døren er låst.
    public boolean checkLock() {
        if (getROOM_NAME().equals("Room 3")
                // Tjekker for true
                && getRoom().getWestIsLocked())
            return getRoom().getWestIsLocked();
        if (getROOM_NAME().equals("Room 7")
                // Tjekker for true.
                && getRoom().getEastIsLocked())
            return getRoom().getEastIsLocked();

        // return false
        return getRoom().getIsLocked();
    }

    // Metoden for at bevæge sig rundt.
    public Room move(String moveCommand) {
        Room temp = getRoom();

        // sætter hasVisted for rummet.
        if(!getRoom().getHasVisited())
            getRoom().setHasVisited(!getRoom().getHasVisited());

        // sætter current til nyt rom og indhenter tekst på rom.
        if (moveCommand.equals("north") && getRoom().getNorth() != null) {
            currentRoom = getRoom().getNorth();
        } else if (moveCommand.equals("south") && getRoom().getSouth() != null) {
            currentRoom = getRoom().getSouth();
        } else if (moveCommand.equals("east") && getRoom().getEast() != null) {
            currentRoom = getRoom().getEast();
        } else if (moveCommand.equals("west") && getRoom().getWest() != null) {
            currentRoom = getRoom().getWest();
        }

        return !getRoom().equals(temp)
                ? getRoom()
                : null;
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
        for(Item item : getRoom().getItems()){
            // Fjern fra rum og tilføje til inventory.
            if(item.getNAME().toLowerCase().equals(input)){
                inventory.add(item);
                getRoom().removeSpecificItem(item);
                return item;
            }
        }

        return null;
    }

    // Returnere sandt eller falsk, hvis item eksistere.
    public Item dropItem(String input){
        for(Item item : inventory){
            // Fjern fra player og tilføj til rum.
            if(item.getNAME().toLowerCase().equals(input)){
                inventory.remove(item);
                getRoom().addOneItem(item);
                return item;
            }
        }

        return null;
    }

    // ------------------------- GET / SET -------------------------
    public Room getRoom() {
        return currentRoom;
    }
    public String getROOM_NAME(){
        return getRoom().getROOM_NAME();
    }
    public void setRoomDescription(String Description){
        getRoom().setDESCRIPTION(Description);
    }
    public String getRoomDescription(){
        return getRoom().getDESCRIPTION();
    }

    public boolean getRoomIsLocked(){
        return getRoom().getIsLocked();
    }

    // Sætter locked variablen
    public void setLockIfTrue() {
        // hvis locked er true. Sæt til false.
        if (getRoom().getIsLocked())
            getRoom().setIsLocked(!getRoom().getIsLocked());
    }

    public Room getRoomNorth(){
        return getRoom().getNorth();
    }
    public Room getRoomSouth(){
        return getRoom().getSouth();
    }
    public Room getRoomEast(){
        return getRoom().getEast();
    }
    public Room getRoomWest(){
        return getRoom().getWest();
    }

    public boolean getRoomIsNorthVisited(){
        return getRoom().isNorthVisited();
    }
    public void setRoomIsNorthVisited(boolean isNorthVisited){
        getRoom().setNorthVisited(isNorthVisited);
    }
    public boolean getRoomIsSouthVisited(){
        return getRoom().isSouthVisited();
    }
    public void setRoomIsSouthVisited(boolean isSouthVisited){
        getRoom().setSouthVisited(isSouthVisited);
    }
    public boolean getRoomIsEastVisited(){
        return getRoom().isEastVisited();
    }
    public void setRoomIsEastVisited(boolean isEastVisited){
        getRoom().setEastVisited(isEastVisited);
    }
    public boolean getRoomIsWestVisited(){
        return getRoom().isWestVisited();
    }
    public void setRoomIsWestVisited(boolean isWestVisited){
        getRoom().setWestVisited(isWestVisited);
    }

    // Del 2
    public ArrayList<Item> getRoomItems(){
        return getRoom().getItems();
    }
    // ----------------------------------------------------------
}
