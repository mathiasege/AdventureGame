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
        Random rand = new Random();

        // Vil gerne være sikker på, at noget mad giver +, på et tidspunk. Derfor laver jeg random her, individuelt.
        Item melon = new Food("Melon", "A strange melon that may look ripe but could have unexpected flavors", rand.nextInt(10, 20));
        Item apple = new Food("Apple", "A crisp apple that promises strength, but its taste might be more tart than expected", rand.nextInt(20, 40));
        Item steak = new Food("Steak", "A juicy piece of meat that restores health but leaves you slightly sluggish afterwards.", rand.nextInt(30, 50));
        Item bread = new Food("Bread", "A loaf of bread. Stale but filling, providing a small boost of stamina.", rand.nextInt(5, 15));
        Item banana = new Food("Banana", "This vivid banana hints at mental clarity, but its strange color raises questions about its safety", rand.nextInt(-20, -5));
        Item mushroom = new Food("Mushroom", "A peculiar mushroom. It might increase your vitality, or it could be poisonous.", rand.nextInt(-20, -5));
        Item cheese = new Food("Cheese", "A wedge of cheese with an intense aroma, which might either heal or nauseate you.", rand.nextInt(-10, -1));
        Item keyToRoom2 = new Key("KeyMine", "Key to unlock Mine");
        Item keyToRoom8 = new Key("KeySuite", "Key to unlock Suite");

        // Opretter mine enemies.
        Enemy orc = new Enemy("Orc", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy human = new Enemy("Human", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy elf = new Enemy("Elf", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy goblin = new Enemy("Goblin", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy dwarf = new Enemy("Dwarf", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy zombie = new Enemy("Zombie", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy vampire = new Enemy("Vampire", enemyHealth(rand), (Weapon) newWeapon(rand));
        Enemy werewolf = new Enemy("Werewolf", enemyHealth(rand) , (Weapon) newWeapon(rand));


        // items til room1
        ArrayList<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(newWeapon(rand));
        itemsRoom1.add(melon);
        itemsRoom1.add(steak);

        ArrayList<Item> itemsRoom2 = new ArrayList<>();
        itemsRoom2.add(newWeapon(rand));

        ArrayList<Item> itemsRoom3 = new ArrayList<>();
        itemsRoom3.add(mushroom);
        itemsRoom3.add(bread);
        itemsRoom3.add(newWeapon(rand));

        ArrayList<Item> itemsRoom5 = new ArrayList<>();
        itemsRoom5.add(keyToRoom8);

        ArrayList<Item> itemsRoom6 = new ArrayList<>();
        itemsRoom6.add(banana);
        itemsRoom6.add(newWeapon(rand));

        ArrayList<Item> itemsRoom7 = new ArrayList<>();
        itemsRoom7.add(keyToRoom2);
        itemsRoom7.add(newWeapon(rand));

        ArrayList<Item> itemsRoom9 = new ArrayList<>();
        itemsRoom9.add(cheese);
        itemsRoom9.add(apple);
        itemsRoom9.add(newWeapon(rand));

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
        room1.setItems(itemsRoom1);
        room1.setENEMIES(orc);

        room2.setEast(room3);
        room2.setWest(room1);
        room2.setIsLocked(true);
        room2.setItems(itemsRoom2);

        room3.setSouth(room6);
        room3.setWest(room2);
        room3.setItems(itemsRoom3);
        room3.setENEMIES(zombie);

        room4.setNorth(room1);
        room4.setSouth(room7);
        room4.setENEMIES(werewolf);
        room4.setENEMIES(vampire);

        room5.setSouth(room8);
        room5.setItems(itemsRoom5);
        room5.setENEMIES(dwarf);
        room5.setENEMIES(goblin);

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
        room9.setItems(itemsRoom9);
        room9.setENEMIES(human);
        room9.setENEMIES(elf);

        // configurer det første rum.
        board = room1;
    }

    // Opretter nye reference typer hver gang. Undgår genbrug.
    private Item newWeapon(Random rand){
        ArrayList<Item> weapons = new ArrayList<>();
        weapons.add(new MeeleWeapon("Sword", "Long sword"));
        weapons.add(new MeeleWeapon("Blade", "Very sharp blade"));
        weapons.add(new RangedWeapon("Bow", "A long-range weapon that shoots arrows with precision."));
        weapons.add(new RangedWeapon("Throwing-Dagger", "A small, sharp blade perfect for quick and silent attacks."));
        weapons.add(new MeeleWeapon("Spear", "A long pole weapon, excellent for keeping enemies at a distance."));
        weapons.add(new MeeleWeapon("Warhammer", "A heavy weapon that can crush armor and bones alike."));
        weapons.add(new RangedWeapon("Crossbow", "A powerful ranged weapon with a mechanical trigger to release bolts."));
        weapons.add(new MeeleWeapon("Axe", "A brutal chopping weapon, ideal for breaking shields."));
        weapons.add(new RangedWeapon("Sling", "A simple but effective weapon for throwing stones at high velocity."));

        return weapons.get(rand.nextInt(0,9));
    }

    // Har en random, som sætter liv for enemy
    private int enemyHealth(Random rand){
        return rand.nextInt(13, 26);
    }
}
