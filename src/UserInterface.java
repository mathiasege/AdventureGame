import java.util.Scanner;

public class UserInterface {

    public void game(){
        Scanner scan = new Scanner(System.in);
        Adventur adventur = new Adventur();
        String input = "";

        System.out.println("You're in room: " + adventur.getRoomName());
        System.out.println(adventur.getDescription());

        while(!input.equals("exist")){
            System.out.println("(1) Move command Type in: North, East, West, South.");
            System.out.println("(2) Looking in room type in: look.");
            System.out.println("(3) Need help Type in: help.");
            System.out.println("(4) If you want to quiet game. Type in: exist.");

            input = scan.nextLine().toLowerCase();
            switch (input){
                case "north", "south", "east", "west" -> {
                    // Tjekker om det er muligt at gå igennem.
                    if(adventur.checkLock(input) && adventur.getRoomName() != null)
                        System.out.println("The door is locked. Find another way");
                    else
                        System.out.println(adventur.move(input));
                }
                case "look" -> System.out.println(adventur.checkLocations());
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
