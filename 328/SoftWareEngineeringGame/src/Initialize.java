
public class Initialize {

	int[] room0Neighbor = {1,3,4,5};
	int[] room1Neighbor = {0,2,3};
	int[] room2Neighbor = {1,3,5,6};
	int[] room3Neighbor = {0,1,2,5};
	int[] room4Neighbor = {0,5,7};
	int[] room5Neighbor = {0,2,3,4,6};
	int[] room6Neighbor = {2,5,10};
	int[] room7Neighbor = {4,8};
	int[] room8Neighbor = {7,9,16};
	int[] room9Neighbor = {8,10};
	int[] room10Neighbor = {6,9};
	int[] room11Neighbor = {12};
	int[] room12Neighbor = {11,13,14,15,16};
	int[] room13Neighbor = {12};
	int[] room14Neighbor = {12,15};
	int[] room15Neighbor = {12,14,17,18,19,20};
	int[] room16Neighbor = {12};
	int[] room17Neighbor = {15};
	int[] room18Neighbor = {15};
	int[] room19Neighbor = {15};
	int[] room20Neighbor = {15};
	
	String[] room0NeighborS = {"room1","room3","room4","room5"};
	String[] room1NeighborS = {"room0","room2","room3"};
	String[] room2NeighborS = {"room1","room3","room5","room6"};
	String[] room3NeighborS = {"room0","room1","room2","room5"};
	String[] room4NeighborS = {"room0","room5","room7"};
	String[] room5NeighborS = {"room0","room2","room3","room4","room6"};
	String[] room6NeighborS = {"room2","room5","room10"};
	String[] room7NeighborS = {"room4","room8"};
	String[] room8NeighborS = {"room7","room9","room16"};
	String[] room9NeighborS = {"room8","room10"};
	String[] room10NeighborS = {"room6","room9"};
	String[] room11NeighborS = {"room12"};
	String[] room12NeighborS = {"room11","room13","room14","room15","room16"};
	String[] room13NeighborS = {"room12"};
	String[] room14NeighborS = {"room12","room15"};
	String[] room15NeighborS = {"room12","room14","room17","room18","room19","room20"};
	String[] room16NeighborS = {"room12"};
	String[] room17NeighborS = {"room15"};
	String[] room18NeighborS = {"room15"};
	String[] room19NeighborS = {"room15"};
	String[] room20NeighborS = {"room15"};
	
	Room	room0 = new Room(0,room0Neighbor,40,40, "room0", room0NeighborS);
	Room	room1 = new Room(1,room1Neighbor,0,0, "room1", room1NeighborS);
	Room	room2 = new Room(2,room2Neighbor,0,0, "room2", room2NeighborS);
	Room	room3 = new Room(3,room3Neighbor,0,0, "room3", room3NeighborS);
	Room	room4 = new Room(4,room4Neighbor,0,0, "room4", room4NeighborS);
	Room	room5 = new Room(5,room5Neighbor,0,0, "room5", room5NeighborS);
	Room	room6 = new Room(6,room6Neighbor,0,0, "room6", room6NeighborS);
	Room	room7 = new Room(7,room7Neighbor,0,0, "room7", room7NeighborS);
	Room	room8 = new Room(8,room8Neighbor,0,0, "room8", room8NeighborS);
	Room	room9 = new Room(9,room9Neighbor,0,0, "room9", room9NeighborS);
	Room	room10 = new Room(10,room10Neighbor,0,0, "room10", room10NeighborS);
	Room	room11 = new Room(11,room11Neighbor,0,0, "room11", room11NeighborS);
	Room	room12 = new Room(12,room12Neighbor,0,0, "room12", room12NeighborS);
	Room	room13 = new Room(13,room13Neighbor,0,0, "room13", room13NeighborS);
	Room	room14 = new Room(14,room14Neighbor,0,0, "room14", room14NeighborS);
	Room	room15 = new Room(15,room15Neighbor,0,0, "room15", room15NeighborS);
	Room	room16 = new Room(16,room16Neighbor,1000,900, "room16", room16NeighborS);
	Room	room17 = new Room(17,room17Neighbor,900,700, "room17", room17NeighborS);
	Room	room18 = new Room(18,room18Neighbor,0,0, "room18", room18NeighborS);
	Room	room19 = new Room(19,room19Neighbor,0,0, "room19", room19NeighborS);
	Room	room20 = new Room(20,room20Neighbor,0,0, "room20", room20NeighborS);
	
	Room[] campus = {room0,room1,room2,room3,room4,room5,
					room6,room7,room8,room9,room10,room11,
					room12,room13,room14,room15,room16,room17,
					room18,room19,room20};
	
	Player player1 = new Player("Natsu",room17,17,2,2,2,3);
	Player player2 = new Player("Gray",room17,17,3,1,2,3);
	Player player3 = new Player("Erza",room17,17,0,3,3,3);
	Player human;
	Player AI1;
	Player AI2;
	
	int humanNewMovevalue = 3;
	int AI1NewMovevalue = 3;
	int AI2NewMovevalue = 3;
	
	public Initialize(){
		
	}
	
}
