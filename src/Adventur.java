public class Adventur {
    private Room current;

    // Tjekker om døren er låst.
    public boolean checkLock(String move){
        if(current.getRoomName().equals("Room 3")
                && move.equals("west")
                && current.getWest().getLocked() == true)
            return true;
        if(current.getRoomName().equals("Room 7")
                && move.equals("east")
                && current.getEast().getLocked() == true)
            return true;

        return false;
    }

    // Sætter locked variablen
    public String setLock(Boolean locked){
        // Før jeg sætter viablen, sætter jeg tekst udfra hvad den er nu.
        String result = current.getLocked()
                ? "Unlocked the door"
                : "The door is not locked";

        current.setLocked(locked);

        return result;
    }

    // Metoden for at se om du har været der før.
    public String checkLocations(){
        String result = "";

        // Tjekker at rummet ikke er null
        if(current.getNorth() != null){
            // Tjekker om vi har været der før
            result = current.getNorth().getHasVisited() == false
                 ? "You haven't visited north. " + current.getNorth().getRoomName()
                 : "You have visited north: " + current.getNorth().getRoomName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(current.getSouth() != null){
            // Tjekker om vi har været der før
            result += current.getSouth().getHasVisited() == false
                 ? "You haven't visited south: " + current.getSouth().getRoomName()
                 : "You have visited south: " + current.getSouth().getRoomName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(current.getEast() != null){
            // Tjekker om vi har været der før
            result += current.getEast().getHasVisited() == false
                 ? "You haven't visited east: " + current.getEast().getRoomName()
                 : "You have visited east: " + current.getEast().getRoomName();
        }
        result += "\n";

        // Tjekker at rummet ikke er null
        if(current.getWest() != null){
            // Tjekker om vi har været der før
            result += current.getWest().getHasVisited() == false
                ? "You haven't visited west: " + current.getWest().getRoomName()
                : "You have visited west: " + current.getWest().getRoomName();
        }
        return result;
    }

    // Metoden for at bevæge sig rundt.
    public String move(String moveCommand){
        String result = "";

        // sætter current til nyt rom og indhenter tekst på rom.
        if(moveCommand.equals("north") && current.getNorth() != null) {
            current = current.getNorth();
            result = setRoomText(current);
        } else if(moveCommand.equals("south") && current.getSouth() != null) {
            current = current.getSouth();
            result = setRoomText(current);
        } else if(moveCommand.equals("east") && current.getEast() != null){
            current = current.getEast();
            result = setRoomText(current);
        } else if(moveCommand.equals("west") && current.getWest() != null){
            current = current.getWest();
            result = setRoomText(current);
        }

        // Sætter variablen hasVisited.
        setVisit(current);

        //Kontrollere om result er tomt.
        return result.isEmpty()
                ? "You cannot go that way"
                : result;
    }
    // sætter teksten udfra room.
    private String setRoomText(Room room){
        return room.getHasVisited() == false
                ? "You moved on to: " + current.getRoomName() + "\n" + current.getDescription()
                : current.getRoomName() + "\nBeen there done that.";
    }
    // sætter variablen visit for room.
    private boolean setVisit(Room room){
        return room.getHasVisited() == false
                ? room.setHasVisited(true)
                : room.getHasVisited();
    }

    public String getRoomName(){
        return current.getRoomName();
    }

    public String getDescription(){
        return current.getDescription();
    }

    // Konfigurer spillet.
    public Adventur(){
        Room room1 = new Room("Room 1", "You're at the intrance.");
        Room room2 = new Room("Room 2", "You're in the mine.");
        Room room3 = new Room("Room 3", "You're in the cell.");
        Room room4 = new Room("Room 4", "You're in the hall.");
        Room room5 = new Room("Room 5", "You're in the book room.");
        Room room6 = new Room("Room 6", "You're in the bed room.");
        Room room7 = new Room("Room 7", "You're in the living room.");
        Room room8 = new Room("Room 8", "You're in the president suit.");
        Room room9 = new Room("Room 9", "You're in the dragon room.");

        room1.setNorth(null);
        room1.setEast(room2);
        room1.setSouth(room4);
        room1.setWest(null);

        room2.setNorth(null);
        room2.setSouth(null);
        room2.setEast(room3);
        room2.setWest(room1);
        room2.setLocked(true);

        room3.setNorth(null);
        room3.setSouth(room6);
        room3.setEast(null);
        room3.setWest(room2);

        room4.setNorth(room1);
        room4.setSouth(room7);
        room4.setEast(null);
        room4.setWest(null);

        room5.setNorth(null);
        room5.setSouth(room8);
        room5.setEast(null);
        room5.setWest(null);

        room6.setNorth(room3);
        room6.setSouth(room9);
        room6.setEast(null);
        room6.setWest(null);

        room7.setNorth(room4);
        room7.setSouth(null);
        room7.setEast(room8);
        room7.setWest(null);

        room8.setNorth(room5);
        room8.setSouth(null);
        room8.setEast(room9);
        room8.setWest(room7);
        room8.setLocked(true);

        room9.setNorth(room6);
        room9.setSouth(null);
        room9.setEast(null);
        room9.setWest(room8);

        // configurer det første rum.
        current = room1;
        current.setHasVisited(true);
    }
}
