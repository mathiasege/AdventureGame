import java.util.Scanner;

public class UserInterface {

    public void game(){
        Adventur adventur = new Adventur();

        Scanner scan = new Scanner(System.in);
        String input = "";

        System.out.println("You're in room: " + adventur.getPlayer().getRoom().getName());
        System.out.println(adventur.getPlayer().getRoom().getDescription());
        System.out.print(adventur.checkForItem());

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
                        Room temp = adventur.getPlayer().getRoom();
                        System.out.println(adventur.move(input));

                        // Tjekker låsen, hvis man skifter rum og ikke har været der.
                        if(temp != adventur.getPlayer().getRoom()
                                && !adventur.getPlayer().getRoom().getHasVisited()){
                            System.out.println(adventur.getLock());
                        }
                        System.out.print(adventur.checkForItem());
                    }
                }
                case "look" -> {
                    System.out.println("You're in room: " + adventur.getPlayer().getRoom().getName());
                    System.out.println(adventur.locationStatus());
                    System.out.print(adventur.checkForItem());
                }
                case "inventory" -> System.out.print(adventur.checkInventory());
                case "take" -> {
                    System.out.println("Which item do you want to take?");
                    String item = new Scanner(System.in).nextLine();
                    System.out.println(adventur.takeItem(item));
                }
                case "drop" -> {
                    System.out.println("Which item do you want to drop?");
                    String item = new Scanner(System.in).nextLine();
                    System.out.print(adventur.dropItem(item));
                }
                case "xyzzy" -> System.out.print(adventur.teleport());
                case "exist" -> System.out.print("You will now exist game.");
                case "help" -> {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Move around and collect stuff.");
                    System.out.println("If you want to move type: ");
                    System.out.println("Type in: North, East, West, South. to move");
                    System.out.print("Press enter if you understand");
                    scan2.nextLine();
                }
                default -> System.out.print("unknown command.");
            }
        }
    }
}
