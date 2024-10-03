import java.util.Scanner;

public class UserInterface {

    public void game() {
        Adventur adventur = new Adventur();

        Scanner scan = new Scanner(System.in);
        String input = "";

        System.out.println("You're in " + adventur.getRoom().toString());
        System.out.println(adventur.getPlayerInfo());
        System.out.print(adventur.checkForItem());

        // kør så længe der ikke er skrevet exist.
        while (!input.equals("exist")) {
            System.out.println("\t-----------------------------------------------------");
            System.out.println("\t- Move command Type in: go north, go east, go west, go south.");
            System.out.println("\t- Look in current room type in: look.");
            System.out.println("\t- Check health type in: health.");
            System.out.println("\t- Take health type in: eat.");
            System.out.println("\t- Look in inventory type in: inventory.");
            System.out.println("\t- Take a item type in: take.");
            System.out.println("\t- Drop item type in: drop.");
            System.out.println("\t- Need help Type in: help.");
            System.out.println("\t- Teleport Type in: xyzzy.");
            System.out.println("\t- If you want to quit game. Type in: exist.");

            input = scan.nextLine().toLowerCase().trim();

            // Opdeler input sådan, at man kan skrive 2 ord.
            String[] command = input.split(" ", 2);

            // Deler min command op og trim() sådan, at alt mellemrum bliver fjernet.
            String firstWord = command[0].trim();
            String secondWord = command.length == 2 ? command[1].trim() : "";

            switch (firstWord) {
                case "go" -> {
                    // Tjekker den rigtige dør.
                    if (adventur.checkDoor(secondWord) && adventur.tryKey() == null){
                        System.out.println("You're missing the right key.");
                    } else {
                        System.out.println(adventur.move(secondWord));
                        System.out.print(adventur.checkForItem());
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
                    System.out.println("Are you sure you want to eat?");
                    System.out.println("Max health = 100. Your health: " + adventur.getPlayerInfo());
                    System.out.println("Type y for yes and n for no.");
                    if (new Scanner(System.in).nextLine().equalsIgnoreCase("y"))
                        System.out.println(adventur.eat(secondWord));
                    else
                        System.out.println("You put the food back.");
                }
                case "inventory" -> System.out.print(adventur.checkInventory());
                case "take" -> System.out.println(adventur.takeItem(secondWord));
                case "drop" -> System.out.println(adventur.dropItem(secondWord));
                case "xyzzy" -> System.out.println(adventur.teleport());
                case "exist" -> System.out.println("You will now exist game.");
                case "help" -> {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Move around and collect stuff.");
                    System.out.println("You can get or lose health from food.");
                    System.out.println("If you want to move type: ");
                    System.out.println("Type in: North, East, West, South. to move");
                    System.out.println("Press enter if you understand");
                    scan2.nextLine();
                }
                default -> System.out.println("unknown command.");
            }
        }
    }
}
