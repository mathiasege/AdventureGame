import Models.*;

import java.util.ArrayList;
import java.util.Random;

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
        Item sword = new Weapon("Sword", "Long sword");
        Item blade = new Weapon("Blade", "Very sharp blade.");
        // Vil gerne være sikker på, at noget mad giver +. Derfor laver jeg random her.
        Item banana = new Food("Banana", "This vivid banana hints at mental clarity, but its strange color raises questions about its safety.", new Random().nextInt(-20, -5));
        Item melon = new Food("Melon", "A strange melon that may look ripe but could have unexpected flavors.", new Random().nextInt(10, 20));
        Item apple = new Food("Apple", "A crisp apple that promises strength, but its taste might be more tart than expected.", new Random().nextInt(20, 40));
        Item keyToRoom2 = new Key("KeyMine", "Key to unlock Mine");
        Item keyToRoom8 = new Key("KeySuite", "Key to unlock Suite");

        // tilføjer items til room.

        // items til room1
        ArrayList<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(sword);
        itemsRoom1.add(melon);

        // Item til room2
        ArrayList<Item> itemsRoom2 = new ArrayList<>();
        itemsRoom2.add(apple);

        // items til room5
        ArrayList<Item> itemsRoom5 = new ArrayList<>();
        itemsRoom5.add(keyToRoom8);
        itemsRoom5.add(blade);

        // Items room6
        ArrayList<Item> itemsRoom6 = new ArrayList<>();
        itemsRoom6.add(banana);

        // Items room7
        ArrayList<Item> itemsRoom7 = new ArrayList<>();
        itemsRoom7.add(keyToRoom2);

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
        Room room7 = new Room("Living Models.Room",
                "A sinister room with a cold fireplace, torn armchairs, and a faint smell of something rotten.");
        Room room8 = new Room("Suite",
                "A once-grand suite, now filled with broken furniture and an oppressive sense of dread.");
        Room room9 = new Room("Dragon Models.Room",
                "An eerie room lined with dragon statues, their eyes glowing red, watching your every move.");

        room1.setEast(room2);
        room1.setSouth(room4);
        room1.setItems(itemsRoom1);

        room2.setEast(room3);
        room2.setWest(room1);
        room2.setIsLocked(true);
        room2.setItems(itemsRoom2);

        room3.setSouth(room6);
        room3.setWest(room2);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);
        room5.setItems(itemsRoom5);

        room6.setNorth(room3);
        room6.setSouth(room9);
        room6.setItems(itemsRoom6);

        room7.setNorth(room4);
        room7.setEast(room8);
        room7.setItems(itemsRoom7);

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
