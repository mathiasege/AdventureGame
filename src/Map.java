public class Map {
    private Room board;

    public Map(){
        createMap();
    }

    public Room getBoard(){
        return board;
    }

    private void createMap(){
        Room room1 = new Room("Room 1", "You're at the intrance.");
        Room room2 = new Room("Room 2", "You're in the mine.");
        Room room3 = new Room("Room 3", "You're in the cell.");
        Room room4 = new Room("Room 4", "You're in the hall.");
        Room room5 = new Room("Room 5", "You're in the book room.");
        Room room6 = new Room("Room 6", "You're in the bed room.");
        Room room7 = new Room("Room 7", "You're in the living room.");
        Room room8 = new Room("Room 8", "You're in the president suit.");
        Room room9 = new Room("Room 9", "You're in the dragon room.");

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setEast(room3);
        room2.setWest(room1);
        room2.setLocked(true);

        room3.setSouth(room6);
        room3.setWest(room2);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setNorth(room5);
        room8.setEast(room9);
        room8.setWest(room7);
        room8.setLocked(true);

        room9.setNorth(room6);
        room9.setWest(room8);

        // configurer det første rum.
        board = room1;
        board.setHasVisited(true);
    }
}
