import java.util.Scanner;

public class UserInterface {

    public void game() {
        Adventur adventur = new Adventur();
        Scanner scan = new Scanner(System.in);
        String input = "";
        // Dårligt fiks. Har ikke tid til andet.
        String room = "";

        // kør så længe der ikke er skrevet exist.
        while (adventur.getPlayerHealth() > 0 && !input.equals("exit")) {
            displayPlayerRoomDetails(adventur, room);
            System.out.println("\t-----------------------------------------------------");
            System.out.println("\t- Move command Type in: go north, go east, go west, go south.");
            System.out.println("\t- Look in current room type in: look.");
            System.out.println("\t- Check health type in: health.");
            System.out.println("\t- Eat food type in: eat.");
            System.out.println("\t- Attack type in: attack.");
            System.out.println("\t- Take a item type in: take.");
            System.out.println("\t- Drop item type in: drop.");
            System.out.println("\t- Equip item type in: equip.");
            System.out.println("\t- Look in inventory type in: inventory.");
            System.out.println("\t- Need help Type in: help.");
            System.out.println("\t- Teleport Type in: xyzzy.");
            System.out.println("\t- If you want to quit game. Type in: exit.");

            input = scan.nextLine().toLowerCase().trim();

            // Opdeler input sådan, at man kan skrive 2 ord.
            String[] command = input.split(" ", 2);

            // Deler min command op og trim() sådan, at alt mellemrum bliver fjernet.
            String firstWord = command[0].trim();
            String secondWord = command.length == 2 ? command[1].trim() : "";

            switch (firstWord) {
                case "go" -> {
                    room = "";
                    // Tjekker den rigtige dør.
                    if (adventur.checkDoor(secondWord) && adventur.tryKey() == null) {
                        System.out.println("You're missing the right key.");
                    } else {
                        room = adventur.move(secondWord);
                        System.out.print(room);
                    }
                }
                case "look" -> {
                    System.out.println("You're in " + adventur.getRoom().toString());
                    if (!adventur.locationStatus().isEmpty())
                        System.out.println(adventur.locationStatus());
                    System.out.print(adventur.checkForItem());
                }
                case "health" -> System.out.println("Your health: " + adventur.getPlayerInfo());
                case "eat" -> {
                    System.out.println("Are you sure you want to eat? It might be dangerous.");
                    System.out.println("Max health = 100. Your health: " + adventur.getPlayerInfo());
                    System.out.println("Type y for yes and n for no.");
                    if (new Scanner(System.in).nextLine().equalsIgnoreCase("y"))
                        System.out.println(adventur.eat(secondWord));
                    else
                        System.out.println("You put the food back.");
                }
                case "attack" -> {
                    if (adventur.checkCanAttack()) {
                        adventur.attack(secondWord);
                    } else if (adventur.getWeapon() != null) {
                        System.out.println("You're missing ammo:");
                        System.out.println(adventur.checkPlayerWeaponInfo());
                    } else {
                        System.out.println("You need a weapon to attack");
                    }
                }
                case "take" -> System.out.println(adventur.takeItem(secondWord));
                case "drop" -> System.out.println(adventur.dropItem(secondWord));
                case "equip" -> System.out.println(adventur.equipWeapon(secondWord));
                case "inventory" -> System.out.println(adventur.checkInventory());
                case "xyzzy" -> System.out.println(adventur.teleport());
                case "help" -> {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Move around and collect stuff.");
                    System.out.println("You can get or lose health from food.");
                    System.out.println("If you want to eat food. Type in: eat food name");
                    System.out.println("If you want check health:");
                    System.out.println("\t- Type in: health");
                    System.out.println("If you want to move:");
                    System.out.println("\t- Type in: go north, go east, go west, go south.");
                    System.out.println("If you want to drop og take a item:");
                    System.out.println("\t- Type in: drop item or take item.");
                    System.out.println("If you want to teleport");
                    System.out.println("\t- Type in: xyzzy.");
                    System.out.println("Look in inventory:");
                    System.out.println("\t- Type in: inventory.");
                    System.out.println("Exist game:");
                    System.out.println("\t- Type in: Exit.");
                    System.out.println("Press enter if you understand");
                    scan2.nextLine();
                }
                case "exit" -> System.out.println("You will now exit game.");
                default -> System.out.println("unknown command.");
            }
            System.out.println();

            if (adventur.getPlayerHealth() <= 0) {
                System.out.println("You're dead. You lost the game. The game will end");
                System.out.println("Your health: " + adventur.getPlayerHealth());
            }
        }
    }

    private void displayPlayerRoomDetails(Adventur adventur, String description) {
        if (description.isEmpty())
            System.out.println(adventur.getRoom().toString());

        System.out.println("-------------------- PLAYER --------------------\n" +
                adventur.getPlayerInfo() +
                "\n" + adventur.checkPlayerWeaponInfo());
        System.out.print("-------------------- ITEMS --------------------\n" +
                adventur.checkForItem());
        System.out.print("-------------------- ENEMIES --------------------\n" +
                adventur.checkEnemy());
    }
}
