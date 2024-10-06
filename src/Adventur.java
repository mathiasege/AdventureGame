import Models.Item;
import Models.Room;
import Models.Weapon;

public class Adventur {
    private Map map;
    private Player player;

    public Adventur() {
        // Istansiere mine variabler.
        map = new Map();
        player = new Player(map.getBoard());
    }

    // returnere navnet på det nye rum.
    public String teleport() {
        return player.teleport();
    }

    // Metoden for at gå.
    public String move(String input) {
        // Sætter visited for rum du kan tilgå
        setVisited(input);

        // prøver at rykke mig.
        Room room = player.move(input);

        // samligner om jeg har rykket mig.
        if (room == null) return "You cannot go that way.";

        // Tjekker om man har været der.
        return !room.getHasVisited()
                ? "You moved on to: " + room.toString()
                : player.getROOM_NAME() + "." +
                "\nBeen there done that.";
    }

    // sætter true på rum du er tilgået.
    private void setVisited(String input) {
        // Sætter alle til modsat af den værdi de har.
        if (input.equals("north") && !player.getRoomIsNorthVisited())
            player.setRoomIsNorthVisited(!player.getRoomIsNorthVisited());
        if (input.equals("south") && !player.getRoomIsSouthVisited())
            player.setRoomIsSouthVisited(!player.getRoomIsSouthVisited());
        if (input.equals("east") && !player.getRoomIsEastVisited())
            player.setRoomIsEastVisited(!player.getRoomIsEastVisited());
        if (input.equals("west") && !player.getRoomIsWestVisited())
            player.setRoomIsWestVisited(!player.getRoomIsWestVisited());
    }

    // Metoden sender result tilbage, når alle rum er blevet besøgt.
    public String locationStatus() {
        // opretter lokale variabler.
        String result = "";

        // Hvis rummet er forskellig for null
        if (player.getRoomNorth() != null) result += "You can go north.\n";
        if (player.getRoomSouth() != null) result += "You can go south.\n";
        if (player.getRoomEast() != null) result += "You can go east.\n";
        if (player.getRoomWest() != null) result += "You can go north.\n";

        // result kommer kun tilbage hvis alle er true.
        return player.getRoomIsNorthVisited()
                && player.getRoomIsSouthVisited()
                && player.getRoomIsEastVisited()
                && player.getRoomIsWestVisited()
                ? result + "All rooms have been visited."
                : "";
    }

    // Del 2
    // Gennemgår items i et rum
    public String checkForItem() {
        String temp = "";

        // henter items.
        for (var item : player.getRoomItems()) {
            temp += item.toString() + "\n";
        }

        // returner ingenting, hvis temp er tom. ellers temp.
        return temp.isEmpty()
                ? ""
                : temp;
    }

    // Returnere en besked udfra en String fra player.checkItem.
    public String checkInventory() {
        String temp = player.checkInventory();
        if (temp.isEmpty())
            return "Inventory is empty.";
        else {
            String returnString = "";
            returnString = "Your inventory: " + temp
                    + "\nYou can carry " + player.getBagWeight() + " kg."
                    + "\nEquipped weapon: ";

            if (player.getWeapon() != null) returnString += player.getWeapon().getNAME();
            else returnString += "nothing";

            return returnString;
        }
    }

    // Returnere en besked udfra en bool fra player.takeItem.
    public String takeItem(String input) {
        Item temp = player.checkItemInRoom(input);
        if (temp == null) return "There is nothing like " + input + " to take around here";

        Item item = player.takeItem(temp);
        return item != null
                ? "You took the item: " + item.toString() + " Your bag weight: " + player.getBagWeight() + " kg."
                : "You can't carry any more. Max weight is 100 kg." +
                "\nYour bag weight: " + player.getBagWeight() + " kg. Item weight: " + temp.getWeight() + " kg.";

    }

    // Returnere en besked udfra en bool fra player.dropItem.
    public String dropItem(String input) {
        Item item = player.dropItem(input);
        return item != null
                ? "You dropped the " + item.toString()
                : "You don't have anything like " + input + " in your inventory";
    }

    // Tjekker døren.
    public boolean checkDoor(String input) {
        return (getRoomEast() != null && input.equals("east") && getEastIsLocked()) ||
                (getRoomWest() != null && input.equals("west") && getWestIsLocked());
    }

    // Prøver nøglen
    public Room tryKey() {
        return player.tryKey(player.getKeys());
    }

    // Del 3

    // Smider en string tilbage efter min enum.
    public String eat(String input) {
        int tempHealth = player.getHealth();

        FoundStatus eatStatus = player.eat(input);

        // sætter beskrivelsen udfra min enum
        String status = switch (eatStatus) {
            case DOESNT_EXIST -> input + " isn't food.";
            case SUCCESS -> "You ate " + input + " Your health: " + player.getHealth() + ".";
            case ANOTHER_ITEM -> "No food found.";
        };

        // fortæller om player har mistet eller fået liv.
        // Eller om det overhovedet var spiseligt.
        if (tempHealth > player.getHealth())
            return status + " You lost health from unhealthy food.";
        else if (tempHealth < player.getHealth())
            return status + " You gain health.";
        else if (tempHealth == player.getHealth())
            return status + " Your health was unaffected.";
        else
            return status;
    }

    // Del 4

    public String equipWeapon(String input) {
        FoundStatus temp = player.equipWeapon(input);

        return switch (temp) {
            case ANOTHER_ITEM -> "It's not a weapon: " + input;
            case DOESNT_EXIST -> "Doesn't exist: " + input;
            case SUCCESS -> "You equipped: " + input;
        };
    }

    public boolean checkCanUse() {
        if (player.getWeapon() != null)
            return player.getCanUse();

        return false;
    }

    public String attack(){
        return player.useAmmo();
    }


    // ------------------------- GET / SET -------------------------
    public Room getRoom() {
        return player.getRoom();
    }

    public boolean getRoomHasVisited() {
        return getRoom().getHasVisited();
    }

    // Del 2
    public Room getRoomEast() {
        return player.getRoomEast();
    }

    public boolean getEastIsLocked() {
        return player.getEastIsLocked();
    }

    public Room getRoomWest() {
        return player.getRoomWest();
    }

    public boolean getWestIsLocked() {
        return player.getWestIsLocked();
    }

    // Del 3
    public String getPlayerInfo() {
        return player.toString();
    }

    // Del 4

    public String getWeaponInfo(){
        return player.getWeapon().toString();
    }

    public int getAmmo(){
        return player.getAmmo();
    }

    public Weapon getWeapon(){
        return player.getWeapon();
    }

    // ----------------------------------------------------------
}
