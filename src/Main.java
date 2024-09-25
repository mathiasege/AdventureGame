import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Adventur adventur = new Adventur();
        String move = "";

        while(!move.equals("exist")){

            System.out.println("You're in room: " + adventur.getRoom());
            System.out.println("You need to move around, and collect stuff.");
            System.out.print("Move command ");
            System.out.println("Type in: North, East, West, South. to move");
            System.out.println("Looking in room type in: look");
            System.out.println("For help Type in: help");
            System.out.println("If you want to quiet game. Type in: exist");


            move = scan.nextLine().toLowerCase();

            switch (move){
                case "north" -> System.out.println(adventur.move(move));
                case "south" -> System.out.println(adventur.move(move));
                case "east" -> System.out.println(adventur.move(move));
                case "west" -> System.out.println(adventur.move(move));
                case "look" -> System.out.println("Looking around");
                case "exist" -> System.out.println("You will now exist game.");
                case "help" -> {
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Move around and collect stuff.");
                    System.out.println("If you want to move type: ");
                    System.out.print("Type in: North, East, West, South. to move");
                    System.out.println("Press a key if you understand");
                    scan2.nextLine();
                }
                default -> System.out.println("unknown command.");
            }
        }
    }
}