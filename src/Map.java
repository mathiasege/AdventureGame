import java.util.ArrayList;

public class Map {
    private Room board;

    public Map(){
        createMap();
    }

    public Room getBoard(){
        return board;
    }

    private void createMap(){
        // Opretter items
        Item blade = new Item("Blade", "Very sharp blade.");
        Item sword = new Item("Sword", "Long sword");
        Item bow = new Item("Bow", "Fast shooting bow");
        Item dagger = new Item("Dagger", "Throwing dagger");
        Item keyToRoom2 = new Item("KeyMine", "Key to unlock Mine");
        Item keyToRoom8 = new Item("KeySuite", "Key to unlock Suite");

        // tilføjer items til room.

        // items til room5
        ArrayList<Item> itemRoom5 = new ArrayList<>();
        itemRoom5.add(keyToRoom8);
        itemRoom5.add(blade);
        // items til room1
        ArrayList<Item> itemRoom1 = new ArrayList<>();
        itemRoom1.add(sword);// items til room1
        ArrayList<Item> itemRoom6 = new ArrayList<>();
        itemRoom1.add(bow);

        // Opretter rum
        Room room1 = new Room("Entrance",
                "A small, shadowy entryway with a dusty mat and a flickering bulb casting eerie shapes.");
        Room room2 = new Room("Mine",
                "Narrow, dark tunnels with the sound of water dripping and unsettling echoes in the distance.");
        Room room3 = new Room("Cell",
                "A chilling, stone cell with rusty chains and scratch marks etched into the walls.");
        Room room4 = new Room("Hall",
                "A vast, dim hall with cracked walls and portraits of hollow-eyed figures staring back at you.");
        Room room5 = new Room("Library",
                "Shelves packed with old, decaying books, their pages whispering secrets as the wind stirs.");
        Room room6 = new Room("Bedroom",
                "A dusty bed with torn sheets, and shadows that seem to shift when you're not looking.");
        Room room7 = new Room("Living Room",
                "A sinister room with a cold fireplace, torn armchairs, and a faint smell of something rotten.");
        Room room8 = new Room("Suite",
                "A once-grand suite, now filled with broken furniture and an oppressive sense of dread.");
        Room room9 = new Room("Dragon Room",
                "An eerie room lined with dragon statues, their eyes glowing red, watching your every move.");

        room1.setEast(room2);
        room1.setSouth(room4);
        room1.setItems(itemRoom1);

        room2.setEast(room3);
        room2.setWest(room1);
        room2.setIsLocked(true);

        room3.setSouth(room6);
        room3.setWest(room2);

        room4.setNorth(room1);
        room4.setSouth(room7);
        room4.addOneItem(dagger);

        room5.setSouth(room8);
        room5.setItems(itemRoom5);

        room6.setNorth(room3);
        room6.setSouth(room9);
        room6.setItems(itemRoom6);

        room7.setNorth(room4);
        room7.setEast(room8);
        room7.addOneItem(keyToRoom2);

        room8.setNorth(room5);
        room8.setEast(room9);
        room8.setWest(room7);
        room8.setIsLocked(true);

        room9.setNorth(room6);
        room9.setWest(room8);

        // configurer det første rum.
        board = room1;
        board.setHasVisited(true);
    }
}
