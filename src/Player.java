public class Player {
    private Room currentRoom;

    public Player(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    public Room getCurrent() {
        return currentRoom;
    }

    // Tjekker om døren er låst.
    public boolean checkLock(String input){
        if(currentRoom.getRoomName().equals("Room 3")
                && input.equals("west")
                && currentRoom.getWest().getLocked())
            return true;
        if(currentRoom.getRoomName().equals("Room 7")
                && input.equals("east")
                && currentRoom.getEast().getLocked())
            return true;

        return false;
    }

    // Sætter locked variablen
    public String setLock(Boolean locked){
        // Før jeg sætter viablen, sætter jeg tekst udfra hvad den er nu.
        String result = currentRoom.getLocked()
                ? "You unlocked the door."
                : "The door is not locked.";

        currentRoom.setLocked(locked);

        return result;
    }

    // sætter variablen visit for room.
    public void setVisit(Room currentRoom){
        if(!currentRoom.getHasVisited())
            currentRoom.setHasVisited(true);
    }

    // Metoden for at bevæge sig rundt.
    public Room move(String moveCommand){
        // sætter current til nyt rom og indhenter tekst på rom.
        if(moveCommand.equals("north") && currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth();
        } else if(moveCommand.equals("south") && currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
        } else if(moveCommand.equals("east") && currentRoom.getEast() != null){
            currentRoom = currentRoom.getEast();
        } else if(moveCommand.equals("west") && currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
        }else {
            return null;
        }
        return currentRoom;
    }


    // Metoden for at se om du har været der før.
    public String checkLocations(){
        String result = "";

        // Tjekker at rummet ikke er null
        if(currentRoom.getNorth() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getNorth().getHasVisited()
                    ? "You haven't visited north. " + currentRoom.getNorth().getRoomName()
                    : "You have visited north: " + currentRoom.getNorth().getRoomName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(currentRoom.getSouth() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getSouth().getHasVisited()
                    ? "You haven't visited south: " + currentRoom.getSouth().getRoomName()
                    : "You have visited south: " + currentRoom.getSouth().getRoomName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(currentRoom.getEast() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getEast().getHasVisited()
                    ? "You haven't visited east: " + currentRoom.getEast().getRoomName()
                    : "You have visited east: " + currentRoom.getEast().getRoomName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(currentRoom.getWest() != null){
            // Tjekker om vi har været der før
            result += !currentRoom.getWest().getHasVisited()
                    ? "You haven't visited west: " + currentRoom.getWest().getRoomName()
                    : "You have visited west: " + currentRoom.getWest().getRoomName();
        }
        return result;
    }
}
