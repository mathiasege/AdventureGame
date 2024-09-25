public class Adventur {
    private Room current;



    public Adventur(){
        current = new Room("Room 1",
                "You're in room one");
        Room room2 = new Room("Room 2", "You're in room 2");
        Room room3 = new Room("Room 3", "You're in room 3");
        Room room4 = new Room("Room 4", "You're in room 4");
        Room room5 = new Room("Room 5", "You're in room 5");
        Room room6 = new Room("Room 6", "You're in room 6");
        Room room7 = new Room("Room 7", "You're in room 7");
        Room room8 = new Room("Room 8", "You're in room 8");
        Room room9 = new Room("Room 9", "You're in room 9");

        current.setRoomOne(room2);
        current.setRoomTwo(room4);
        current.setRoomThree(null);

        room2.setRoomOne(current);
        room2.setRoomTwo(room3);
        room2.setRoomThree(null);

        room3.setRoomOne(room2);
        room3.setRoomTwo(room6);
        room3.setRoomThree(null);

        room4.setRoomOne(current);
        room4.setRoomTwo(room7);
        room4.setRoomThree(null);

        room5.setRoomOne(room8);
        room5.setRoomTwo(null);
        room5.setRoomThree(null);

        room6.setRoomOne(room3);
        room6.setRoomTwo(room9);
        room6.setRoomThree(null);

        room7.setRoomOne(room4);
        room7.setRoomTwo(room8);
        room7.setRoomThree(null);

        room8.setRoomOne(room5);
        room8.setRoomTwo(room7);
        room8.setRoomThree(room9);

        room9.setRoomOne(room6);
        room9.setRoomTwo(room8);
        room9.setRoomThree(null);
    }
}
