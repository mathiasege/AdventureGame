import Models.Food;
import Models.Item;
import Models.Room;
import Models.Weapon;

import java.util.ArrayList;

public class Player {
    private Room currentRoom, lastRoom;
    private boolean firstTeleport;
    // Den kan være final fordi, at den kigger på object reference.
    private final ArrayList<Item> inventory;
    private int health;
    private int bagWeight;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        firstTeleport = false;
        inventory = new ArrayList<>();
        health = 100;
        bagWeight = 100;
    }

    // funktionen for at teleport.
    public String teleport() {
        // opretter en temp variabel og map.
        Room temp;
        Map map = new Map();

        // hvis ikke man har teleported sig før.
        if (!firstTeleport) {
            lastRoom = getRoom();
            currentRoom = map.getBoard();
        } else {
            temp = getRoom();
            currentRoom = lastRoom;
            lastRoom = temp;
        }

        // set firstTeleport til true
        firstTeleport = true;

        return getROOM_NAME();
    }

    // Prøver at låse op.
    public Room tryKey(ArrayList<String> keys) {
        // Hvis jeg har en denne nøgle
        if (keys.contains("KeyMine")) {
            // Lås op.
            getRoomEast().setIsLocked(!getRoom().getEastIsLocked());
            return getRoomEast();
        }

        // Hvis jeg har denne nøgle
        if (keys.contains("KeySuite")) {
            // lås op.
            getRoomWest().setIsLocked(!getRoom().getWestIsLocked());
            return getRoomWest();
        }

        return null;
    }

    // Metoden for at bevæge sig rundt.
    public Room move(String moveCommand) {
        Room temp = getRoom();

        // sætter hasVisted for rummet.
        if (!getRoom().getHasVisited()) getRoom().setHasVisited(!getRoom().getHasVisited());

        // sætter current til nyt rom og indhenter tekst på rom.
        if (moveCommand.equals("north") && getRoom().getNorth() != null) {
            currentRoom = getRoom().getNorth();
        } else if (moveCommand.equals("south") && getRoom().getSouth() != null) {
            currentRoom = getRoom().getSouth();
        } else if (moveCommand.equals("east") && getRoom().getEast() != null) {
            currentRoom = getRoom().getEast();
        } else if (moveCommand.equals("west") && getRoom().getWest() != null) {
            currentRoom = getRoom().getWest();
        }

        return !getRoom().equals(temp)
                ? getRoom()
                : null;
    }

    // Del 2
    // Henter items i inventory.
    public String checkInventory() {
        String temp = "";
        // Henter alt ind
        for (Item item : inventory) {
            temp += item.toString() + ".\n";
        }

        return temp;
    }

    // Returnere sandt eller falsk, hvis item eksistere.
    public Item takeItem(Item item) {
        Item temp = null;

        // Fjern fra rum og tilføje til inventory.
        inventory.add(item);
        getRoom().getItems().remove(item);
        bagWeight -= item.getWeight();
        temp = item;

        if (bagWeight >= 0)
            return temp;
        else {
            bagWeight += temp.getWeight();
            return null;
        }
    }

    // Kontrollere om item exist.
    public Item checkItem(String input) {
        for (Item item : getRoomItems()) {
            if (item.getNAME().toLowerCase().equals(input)) {
                return item;
            }
        }

        return null;
    }

    // Returnere sandt eller falsk, hvis item eksistere.
    public Item dropItem(String input) {
        for (Item item : inventory) {
            // Fjern fra player og tilføj til rum.
            if (item.getNAME().toLowerCase().equals(input)) {
                inventory.remove(item);
                getRoom().getItems().add(item);
                bagWeight += item.getWeight();
                return item;
            }
        }

        return null;
    }

    // Henter kun nøgle items.
    public ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<>();
        for (Item item : inventory) {
            // Hvis der er nøgle i ordet. Tilføj til liste
            if (item.getNAME().toLowerCase().contains("key")) keys.add(item.getNAME());
        }
        // Returner liste.
        return keys;
    }

    // Del 3

    // Spiser min mad.
    public EatStatus eat(String input) {
        if (input.isEmpty()) return EatStatus.NO_FOOD_TYPED;

        Food temp = null;
        ArrayList<Item> findFood = inventory;
        for (int i = 0; i < 2; i++) {
            // Hvis man ikke har fundet noget.
            if (temp == null) {
                for (Item item : findFood) {
                    // Hvis det ikke er mad.
                    if (!(item instanceof Food) && item.getNAME().toLowerCase().equals(input))
                        return EatStatus.CANT_EAT_ITEM;
                    if (item instanceof Food && item.getNAME().toLowerCase().equals(input)) {
                        temp = (Food) item;
                        break;
                    }
                }
                findFood = getRoomItems();
            }
        }

        // hvis der er fundet noget
        if (temp != null) {
            // fjern element
            inventory.remove(temp);
            getRoomItems().remove(temp);

            setHealth(temp);
        }

        // Returner String
        return temp != null
                ? EatStatus.SUCCESS
                : EatStatus.NO_FOOD_FOUND;
    }

    // Tilføjer liv.
    private void setHealth(Food temp) {
        // Add health
        health += temp != null ? temp.healthPoints : 0;

        // Hvis liv bliver større end 100. Sæt til 100.
        if (health > 100) health = 100;
    }

    @Override
    public String toString() {
        return "Your health: " + getHealth() + ". " + checkHealthStatus();
    }

    private String checkHealthStatus() {
        if (getHealth() > 50) return "You're in condition to fight. Your health: " + getHealth();
        else return "Avoid fighting. Get something to eat. Your health: " + getHealth();
    }

    // ------------------------- GET / SET -------------------------
    public Room getRoom() {
        return currentRoom;
    }

    public String getROOM_NAME() {
        return getRoom().getROOM_NAME();
    }

    public Room getRoomNorth() {
        return getRoom().getNorth();
    }

    public Room getRoomSouth() {
        return getRoom().getSouth();
    }

    public Room getRoomEast() {
        return getRoom().getEast();
    }

    public Room getRoomWest() {
        return getRoom().getWest();
    }

    public boolean getRoomIsNorthVisited() {
        return getRoom().isNorthVisited();
    }

    public void setRoomIsNorthVisited(boolean isNorthVisited) {
        getRoom().setNorthVisited(isNorthVisited);
    }

    public boolean getRoomIsSouthVisited() {
        return getRoom().isSouthVisited();
    }

    public void setRoomIsSouthVisited(boolean isSouthVisited) {
        getRoom().setSouthVisited(isSouthVisited);
    }

    public boolean getRoomIsEastVisited() {
        return getRoom().isEastVisited();
    }

    public void setRoomIsEastVisited(boolean isEastVisited) {
        getRoom().setEastVisited(isEastVisited);
    }

    public boolean getRoomIsWestVisited() {
        return getRoom().isWestVisited();
    }

    public void setRoomIsWestVisited(boolean isWestVisited) {
        getRoom().setWestVisited(isWestVisited);
    }

    // Del 2
    public ArrayList<Item> getRoomItems() {
        return getRoom().getItems();
    }

    public boolean getWestIsLocked() {
        return getRoomWest().getIsLocked();
    }

    public boolean getEastIsLocked() {
        return getRoomEast().getIsLocked();
    }

    // Del 3
    public int getHealth() {
        return health;
    }

    public int getBagWeight() {
        return bagWeight;
    }
    // ----------------------------------------------------------
}
