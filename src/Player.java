import java.util.ArrayList;

public class Player {
    private Room currentRoom, lastRoom;
    private boolean firstTeleport;
    // Den kan være final fordi, at den kigger på object reference.
    private final ArrayList<Item> inventory;
    private int health;
    private double bagWeight;
    private Weapon weapon;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        firstTeleport = false;
        inventory = new ArrayList<>();
        health = 50;
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

        // Tjekker om man har rykket sig.
        return !getRoom().equals(temp)
                ? getRoom()
                : null;
    }

    // Sætter til sand.
    public void setHasVisitedTrue() {
        // sætter hasVisted for rummet.
        if (!getRoom().getHasVisited()) {
            getRoom().setHasVisited(!getRoom().getHasVisited());
        }
    }

    // Del 2
    // Henter items i inventory.
    public String checkInventory() {
        String temp = "";
        // Henter alt ind
        for (Item item : inventory) {
            temp += item.toString();
        }

        return temp;
    }

    // Returnere sandt eller falsk, hvis item eksistere.
    public Item takeItem(Item item) {
        // Fjern fra rum og tilføje til inventory.
        inventory.add(item);
        getRoom().getItems().remove(item);
        bagWeight -= item.getWeight();

        if (item instanceof Weapon)
            weapon = (Weapon) item;

        // hvis man man kan bærer det.
        if (bagWeight >= 0)
            return item;
        else {
            bagWeight += item.getWeight();
            return null;
        }
    }

    // Kontrollere om item exist.
    public Item checkItemInRoom(String input) {
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
    public FoundStatus eat(String input) {
        Item temp = checkItemInRoom(input);

        // Hvis der ikke er noget i temp. Led i inventory.
        if (temp == null) {
            for (Item item : inventory) {
                temp = item;

                if (item instanceof Food && item.getNAME().toLowerCase().equals(input))
                    break;

            }
        }

        // Hvis det ikke er mad.
        if (temp != null && !(temp instanceof Food) && temp.getNAME().toLowerCase().equals(input))
            return FoundStatus.ANOTHER_ITEM;

        // Dobbelt tjekker, da temp stadig kan være tom.
        if (temp instanceof Food) {
            // fjern, hvis der er en.
            inventory.remove(temp);
            getRoomItems().remove(temp);

            int newHealth = healthFromFood((Food) temp);
            setHealth(newHealth);
            return FoundStatus.SUCCESS;
        }

        // Hvis intet er fundet.
        return FoundStatus.DOESNT_EXIST;
    }

    // Tilføjer liv.
    private int healthFromFood(Food temp) {
        // Add health
        health += temp != null ? temp.healthPoints : 0;

        // Hvis liv bliver større end MAX_HEALTH. Sæt til MAX_HEALTH.
        int MAX_HEALTH = 50;
        if (health > MAX_HEALTH) health = MAX_HEALTH;

        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Your health: " + getHealth() + ". " + checkHealthStatus();
    }

    private String checkHealthStatus() {
        if (getHealth() > 50) return "You're in condition to fight.";
        else return "Avoid fighting. Get something to eat.";
    }

    // Del 4

    // Returnere om man kan bærer våbnet.
    public FoundStatus equipWeapon(String input) {
        Item item = takeFromInventory(input);

        // Hvis det er et våben, tag.
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
            return FoundStatus.SUCCESS;
        }

        return item != null
                ? FoundStatus.ANOTHER_ITEM
                : FoundStatus.DOESNT_EXIST;
    }

    // kontrollere om der er det indtastede i bag.
    private Item takeFromInventory(String input) {
        for (Item item : inventory) {
            if (item.getNAME().equalsIgnoreCase(input))
                return item;
        }

        return null;
    }

    // Del 5

    public void useAmmo() {
        getWeapon().useAmmunition();
    }

    public Enemy attack(String input) {
        Enemy enemy = null;

        if (input.isEmpty())
            enemy = getRoom().getClosestEnemy();
        else
            enemy = getRoom().getSpecificEnemy(input);

        return enemy != null
                ? damage(enemy)
                : null;
    }
    // p = player
    // e = enemy
    private Enemy damage(Enemy enemy) {
        // Skade for eneny
        int pDamage = weapon.getDamage();
        enemy.setHealth(enemy.getHealth() - pDamage);

        // Skade for spiller
        int eDamage = enemy.getWeapon().getDamage();
        setHealth(getHealth() - eDamage);

        return enemy;
    }

    // Bruger en i ammo
    public void useEnemyAmmo(Enemy enemy) {
        if (enemy.getWeapon().getAmmunition() > 0) {
            enemy.useAmmo();
        }
    }

    // ------------------------- GET / SET -------------------------
    public Room getRoom() {
        return currentRoom;
    }

    public void setRoomDescription(String description) {
        getRoom().setDescription(description);
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

    public double getBagWeight() {
        return bagWeight;
    }

    // Del 4

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean getCanAttack() {
        return getWeapon().canAttack();
    }

    // Del 5

    public ArrayList<Enemy> getEnemies() {
        return getRoom().getEnemies();
    }

    public void addItemToRoom(Item item){
        getRoom().addItem(item);
    }

    // ------------------------- GET / SET -------------------------
}
