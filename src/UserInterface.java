import java.util.Scanner;

public class UserInterface {

    public void game(){
        Adventur adventur = new Adventur();

        Scanner scan = new Scanner(System.in);
        String input = "";

        System.out.println("\tYou're in room: " + adventur.getPlayer().getRoom().getName());
        System.out.println("\t" + adventur.getPlayer().getRoom().getDescription());

        // kør så længe der ikke er skrevet exist.
        while(!input.equals("exist")){
            System.out.println("\t-----------------------------------------------------");
            System.out.println("\t(1) Move command Type in: North, East, West, South.");
            System.out.println("\t(2) Look in current room type in: look.");
            System.out.println("\t(3) Look in inventory type in: inventory.");
            System.out.println("\t(4) Take a item type in: take.");
            System.out.println("\t(5) Drop item type in: drop.");
            System.out.println("\t(6) Need help Type in: help.");
            System.out.println("\t(7) Teleport Type in: xyzzy.");
            System.out.println("\t(8) If you want to quit game. Type in: exist.");

            input = scan.nextLine().toLowerCase();

            switch (input){
                case "north", "south", "east", "west" -> {
                    // Tjekker om det er muligt at gå igennem.
                    if(adventur.checkLock(input) && adventur.getPlayer().getRoom() != null)
                        System.out.println("The door is locked. Find another way");
                    else{
                        System.out.println(adventur.move(input));
                    }
                }
                case "look" -> {
                    System.out.println("You're in room: " + adventur.getPlayer().getRoom().getName());
                    System.out.println(adventur.checkForItem());
                    System.out.println(adventur.locationStatus());
                }
                case "inventory" -> System.out.println(adventur.checkInventory());
                case "take" -> {
                    System.out.println("Which item do you want to take?");
                    String item = new Scanner(System.in).nextLine();
                    System.out.println(adventur.takeItem(item));
                }
                case "drop" -> {
                    System.out.println("Which item do you want to drop?");
                    String item = new Scanner(System.in).nextLine();
                    System.out.println(adventur.dropItem(item));
                }
                case "xyzzy" -> System.out.println(adventur.teleport());
                case "exist" -> System.out.println("You will now exist game.");
                case "help" -> {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Move around and collect stuff.");
                    System.out.println("If you want to move type: ");
                    System.out.print("Type in: North, East, West, South. to move");
                    System.out.println("Press enter if you understand");
                    scan2.nextLine();
                }
                default -> System.out.println("unknown command.");
            }
        }
    }
}
