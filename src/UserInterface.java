import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public void game(){
        Scanner scan = new Scanner(System.in);
        Adventur adventur = new Adventur();
        String move = "";

        System.out.println("\tYou're in room: " + adventur.getRoomName());
        System.out.println("\t"+ adventur.getDescription());

        while(!move.equals("exist")){
            System.out.println("\t-----------------------------------------------------");
            System.out.println("Type in:");
            System.out.println("\t(1) Move command: North or East or West or South");
            System.out.println("\t(2) Look around the room: look");
            System.out.println("\t(3) Need help: help");
            System.out.println("\t(4) quiet game: exist");

            move = scan.nextLine().toLowerCase();
            switch (move){
                case "north", "south", "east", "west" -> {
                    // Tjekker om det er muligt at gå igennem.
                    if(adventur.checkLock(move) && adventur.getRoomName() != null)
                        System.out.println("\tThe door is locked. Find another way");
                    else
                        System.out.println(adventur.move(move));
                }
                case "look" -> System.out.println(adventur.checkLocations());
                case "exist" -> System.out.println("\tYou will now exist game.");
                case "help" -> {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("\tMove around but be careful.");
                    System.out.println("\tIf you want to move type: ");
                    System.out.print("\tType in: North, East, West, South. to move");
                    System.out.println("\tPress enter if you understand");
                    scan2.nextLine();
                }
                default -> System.out.println("\tunknown command.");
            }
        }
    }
}
