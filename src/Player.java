public class Player {
    private Room currentRoom, lastRoom;
    private boolean firstTeleport;

    public Player(Room currentRoom){
        this.currentRoom = currentRoom;
        firstTeleport = false;
    }

    public Room getCurrent() {
        return currentRoom;
    }

    public boolean getHasVisited(){
        return currentRoom.getHasVisited();
    }

    // funktionen for at teleport.
    public Room teleport(){
        // opretter en temp variabel og map.
        Room temp;
        Map map = new Map();

        // hvis ikke man har teleporteret sig før.
        if(!firstTeleport){
            lastRoom = currentRoom;
            currentRoom = map.getRoom();
        }else{
            temp = currentRoom;
            currentRoom = lastRoom;
            lastRoom = temp;
        }

        // set firstTeleport til true
        firstTeleport = true;

        return currentRoom;
    }

    // Tjekker om døren er låst.
    public boolean checkLock(String input){
        if(currentRoom.getName().equals("Room 3")
                && input.equals("west")
                // Tjekker for true
                && currentRoom.getWest().getLocked())
            return currentRoom.getWest().getLocked();
        if(currentRoom.getName().equals("Room 7")
                && input.equals("east")
                // Tjekker for true.
                && currentRoom.getEast().getLocked())
            return currentRoom.getEast().getLocked();

        // return false
        return currentRoom.getLocked();
    }

    // sætter variablen visit for room.
    public void setVisit(Room currentRoom){
        currentRoom.setHasVisited(currentRoom.getHasVisited());
    }

    // Metoden for at bevæge sig rundt.
    public Room move(String moveCommand){
        Room temp = currentRoom;
        // sætter current til nyt rom og indhenter tekst på rom.
        if(moveCommand.equals("north") && currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth();
        } else if(moveCommand.equals("south") && currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
        } else if(moveCommand.equals("east") && currentRoom.getEast() != null){
            currentRoom = currentRoom.getEast();
        } else if(moveCommand.equals("west") && currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
        }

        return !currentRoom.equals(temp)
                ? currentRoom
                : null;
    }

    // Sætter locked variablen
    public String setLock(){
        // Før jeg sætter variablen, sætter jeg tekst udfra hvad den er nu.
        String result = currentRoom.getLocked()
                ? "You unlocked the door."
                : "The door is not locked.";

        // hvis locked er true. Sæt til false.
        if(currentRoom.getLocked())
            currentRoom.setLocked(!currentRoom.getLocked());

        return result;
    }

    // Metoden for at se om du har været der før.
    public String checkLocations(){
        String result = "";

        // Tjekker at rummet ikke er null
        if(currentRoom.getNorth() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getNorth().getHasVisited()
                    ? "You haven't visited north. " + currentRoom.getNorth().getName()
                    : "You have visited north: " + currentRoom.getNorth().getName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(currentRoom.getSouth() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getSouth().getHasVisited()
                    ? "You haven't visited south: " + currentRoom.getSouth().getName()
                    : "You have visited south: " + currentRoom.getSouth().getName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(currentRoom.getEast() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getEast().getHasVisited()
                    ? "You haven't visited east: " + currentRoom.getEast().getName()
                    : "You have visited east: " + currentRoom.getEast().getName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(currentRoom.getWest() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getWest().getHasVisited()
                    ? "You haven't visited west: " + currentRoom.getWest().getName()
                    : "You have visited west: " + currentRoom.getWest().getName();
        }
        return result;
    }
}
