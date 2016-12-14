import java.util.Random;

import javax.swing.JButton;


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
	int[] room16Neighbor = {12,8};
	int[] room17Neighbor = {15};
	int[] room18Neighbor = {15};
	int[] room19Neighbor = {15};
	int[] room20Neighbor = {15};
	
	String[] room0NeighborS = {"JapaneseGarden","ThePyramid","WestWalkway","HealthCenter"};
	String[] room1NeighborS = {"GeorgeAllenField","StudentParking","ThePyramid"};
	String[] room2NeighborS = {"JapaneseGarden","ThePyramid","HealthCenter","ForbiddenParking"};
	String[] room3NeighborS = {"GeorgeAllenField","JapaneseGarden","StudentParking","HealthCenter"};
	String[] room4NeighborS = {"GeorgeAllenField","HealthCenter","Library"};
	String[] room5NeighborS = {"GeorgeAllenField","StudentParking","ThePyramid","WestWalkway","ForbiddenParking"};
	String[] room6NeighborS = {"StudentParking","HealthCenter","EastWalkway"};
	String[] room7NeighborS = {"WestWalkway","Union"};
	String[] room8NeighborS = {"Library","BratwurstHall","Elevator"};
	String[] room9NeighborS = {"Union","EastWalkway"};
	String[] room10NeighborS = {"ForbiddenParking","BratwurstHall"};
	String[] room11NeighborS = {"NorthHall"};
	String[] room12NeighborS = {"Lab","RoomofRetirement","302","SouthHall","Elevator"};
	String[] room13NeighborS = {"NorthHall"};
	String[] room14NeighborS = {"NorthHall","SouthHall"};
	String[] room15NeighborS = {"NorthHall","302","308","Eat","Conference","NoisyRoom"};
	String[] room16NeighborS = {"NorthHall","Union"};
	String[] room17NeighborS = {"SouthHall"};
	String[] room18NeighborS = {"SouthHall"};
	String[] room19NeighborS = {"SouthHall"};
	String[] room20NeighborS = {"SouthHall"};
	
	Room	room0 = new Room(0,room0Neighbor,190,300, "GeorgeAllenField", room0NeighborS);
	Room	room1 = new Room(1,room1Neighbor,660,114, "JapaneseGarden", room1NeighborS);
	Room	room2 = new Room(2,room2Neighbor,1270,230, "StudentParking", room2NeighborS);
	Room	room3 = new Room(3,room3Neighbor,660,400, "ThePyramid", room3NeighborS);
	Room	room4 = new Room(4,room4Neighbor,100,790, "WestWalkway", room4NeighborS);
	Room	room5 = new Room(5,room5Neighbor,660,700, "HealthCenter", room5NeighborS);
	Room	room6 = new Room(6,room6Neighbor,1270,700, "ForbiddenParking", room6NeighborS);
	Room	room7 = new Room(7,room7Neighbor,100,1880, "Library", room7NeighborS);
	Room	room8 = new Room(8,room8Neighbor,660,1880, "Union", room8NeighborS);
	Room	room9 = new Room(9,room9Neighbor,1270,1880, "BratwurstHall", room9NeighborS);
	Room	room10 = new Room(10,room10Neighbor,1490,1280, "EastWalkway", room10NeighborS);
	Room	room11 = new Room(11,room11Neighbor,300,1030, "Lab", room11NeighborS);
	Room	room12 = new Room(12,room12Neighbor,400,1250, "NorthHall", room12NeighborS);
	Room	room13 = new Room(13,room13Neighbor,350,1450, "RoomofRetirement", room13NeighborS);
	Room	room14 = new Room(14,room14Neighbor,800,1030, "302", room14NeighborS);
	Room	room15 = new Room(15,room15Neighbor,1000,1250, "SouthHall", room15NeighborS);
	Room	room16 = new Room(16,room16Neighbor,630,1450, "Elevator", room16NeighborS);
	Room	room17 = new Room(17,room17Neighbor,930,1450, "308", room17NeighborS);
	Room	room18 = new Room(18,room18Neighbor,1080,1030, "Eat", room18NeighborS);
	Room	room19 = new Room(19,room19Neighbor,1280,1030, "Conference", room19NeighborS);
	Room	room20 = new Room(20,room20Neighbor,1280,1450, "NoisyRoom", room20NeighborS);
	
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
	
	
	public Initialize(){
		
	}
	
	// run the game until 1 player has 150 vp or human quit
	// each player get 3 turn per while loop
	public static void Game(Initialize gameinstant, Room[] campus) {

		Room newroom = new Room(0,campus[0].GetNeighbor(),0,0,"room0", gameinstant.room0NeighborS);
		// game start
		boolean check = true;
		while(check == true){
			// game end when 1 of 3 hit 150 VP
			if(gameinstant.human.GetVP() == 150 || gameinstant.AI1.GetVP() == 150 || gameinstant.AI2.GetVP() == 150){
				check = false;
			}		
			// ai1 turn 3 move
			for(int i = 0; i < 3; i++){
				// get array of neighbor
				int[] arrofNeighbor = gameinstant.AI1.GetLocation().GetNeighbor();
				// if only 1 neighbor exist
				if(arrofNeighbor.length == 1){					
					// loop through the campus(array of room)
					// pick up the room that match the value
					for(int j = 0; j < campus.length; j++){
						if(arrofNeighbor[0] == campus[j].getvalue()){
							newroom = campus[j];
							break;
						}
					}
					// move to new room
					gameinstant.AI1.Move(gameinstant.AI1.Getmovevalue(), newroom);
					// decrease move value by 1
					//gameinstant.AI1.SetMoveValue(gameinstant.AI1.Getmovevalue()-1);
				}
				else{
					Random rand1 = new Random();
					int  neighborIndex = rand1.nextInt(arrofNeighbor.length - 1);
					// loop through the campus(array of room)
					// pick up the room that match the value
					for(int j = 0; j < campus.length; j++){
						if(arrofNeighbor[neighborIndex] == campus[j].getvalue()){
							newroom = campus[j];
							break;
						}
					}
					// move to new room
					gameinstant.AI1.Move(gameinstant.AI1.Getmovevalue(), newroom);
					// decrease move value by 1
					//gameinstant.AI1.SetMoveValue(gameinstant.AI1.Getmovevalue()-1);
				}
			}
			//reset the AI1 move value
			//gameinstant.AI1.SetMoveValue(3);
					
			// ai2 turn 3 move
			for(int i = 0; i < 3; i++){
				// get array of neighbor
				int[] arrofNeighbor = gameinstant.AI2.GetLocation().GetNeighbor();
				// if only 1 neighbor exist
				if(arrofNeighbor.length == 1){					
					// loop through the campus(array of room)
					// pick up the room that match the value
					for(int j = 0; j < campus.length; j++){
						if(arrofNeighbor[0] == campus[j].getvalue()){
							newroom = campus[j];
							break;
						}
					}
					// move to new room
					gameinstant.AI2.Move(gameinstant.AI2.Getmovevalue(), newroom);
					// decrease move value by 1
					//gameinstant.AI2.SetMoveValue(gameinstant.AI2.Getmovevalue()-1);
				}
				else{
					Random rand1 = new Random();
					int  neighborIndex = rand1.nextInt(arrofNeighbor.length - 1);
					// loop through the campus(array of room)
					// pick up the room that match the value
					for(int j = 0; j < campus.length; j++){
						if(arrofNeighbor[neighborIndex] == campus[j].getvalue()){
							newroom = campus[j];
							break;
						}
					}
					// move to new room
					gameinstant.AI2.Move(gameinstant.AI2.Getmovevalue(), newroom);
					// decrease move value by 1
					//gameinstant.AI2.SetMoveValue(gameinstant.AI2.Getmovevalue()-1);
				}
			}
			//reset the AI1 move value
			//gameinstant.AI2.SetMoveValue(3);	
			check = false;
		}
		//gameinstant.human.SetMoveValue(3);
		//moveButton.enable(true);
	}
	
}
