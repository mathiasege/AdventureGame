public class Adventur {
    private Room current;

    public String move(String moveCommand){
        String result = "";
        if(moveCommand.equals("north") && current.getNorth() != null) {
            current = current.getNorth();
            result = "You moved on to: " + current.getRoomName();
        } else if(moveCommand.equals("south") && current.getSouth() != null) {
            current = current.getSouth();
            result = "You moved on to: " + current.getRoomName();
        } else if(moveCommand.equals("east") && current.getEast() != null){
            current = current.getEast();
            result = "You moved on to: " + current.getRoomName();
        } else if(moveCommand.equals("west") && current.getWest() != null){
            current = current.getWest();
            result = "You moved on to: " + current.getRoomName();
        }

        return result.isEmpty() ? "You cannot go that way" : result + ".";
    }

    public String getRoom(){
        return current.getRoomName();
    }

    public String getDescription(){
        return current.getDescription();
    }

    public Adventur(){
        current = new Room("Room 1",
                "You're in room one");
        Room room2 = new Room("Room 2", "You're in room 2");
        Room room3 = new Room("Room 3", "You're in room 3");
        Room room4 = new Room("Room 4", "You're in room 4");
        Room room5 = new Room("Room 5", "You're in room 5");
        Room room6 = new Room("Room 6", "You're in room 6");
        Room room7 = new Room("Room 7", "You're in room 7");
        Room room8 = new Room("Room 8", "You're in room 8");
        Room room9 = new Room("Room 9", "You're in room 9");

        current.setNorth(null);
        current.setEast(room2);
        current.setSouth(room4);
        current.setWest(null);

        room2.setNorth(null);
        room2.setSouth(null);
        room2.setEast(room3);
        room2.setWest(current);

        room3.setNorth(null);
        room3.setSouth(room6);
        room3.setEast(null);
        room3.setWest(room2);

        room4.setNorth(current);
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

        room9.setNorth(room6);
        room9.setSouth(null);
        room9.setEast(null);
        room9.setWest(room8);
    }
}
