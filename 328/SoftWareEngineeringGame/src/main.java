import java.util.LinkedList;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		Room	room0 = new Room(0,room0Neighbor,0,0);
		Room	room1 = new Room(1,room1Neighbor,0,0);
		Room	room2 = new Room(2,room2Neighbor,0,0);
		Room	room3 = new Room(3,room3Neighbor,0,0);
		Room	room4 = new Room(4,room4Neighbor,0,0);
		Room	room5 = new Room(5,room5Neighbor,0,0);
		Room	room6 = new Room(6,room6Neighbor,0,0);
		Room	room7 = new Room(7,room7Neighbor,0,0);
		Room	room8 = new Room(8,room8Neighbor,0,0);
		Room	room9 = new Room(9,room9Neighbor,0,0);
		Room	room10 = new Room(10,room10Neighbor,0,0);
		Room	room11 = new Room(11,room11Neighbor,0,0);
		Room	room12 = new Room(12,room12Neighbor,0,0);
		Room	room13 = new Room(13,room13Neighbor,0,0);
		Room	room14 = new Room(14,room14Neighbor,0,0);
		Room	room15 = new Room(15,room15Neighbor,0,0);
		Room	room16 = new Room(16,room16Neighbor,0,0);
		Room	room17 = new Room(17,room17Neighbor,0,0);
		Room	room18 = new Room(18,room18Neighbor,0,0);
		Room	room19 = new Room(19,room19Neighbor,0,0);
		Room	room20 = new Room(20,room20Neighbor,0,0);
		
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
		
		// randomly select 1 of 3 player for human
		Random rand = new Random();
		int  n = rand.nextInt(3) + 1;
		if(n == 1){
			human = player1;
			AI1 = player2;
			AI2 = player3;
		}
		else if(n == 2){
			human = player2;
			AI1 = player2;
			AI2 = player3;
		}
		else{
			human = player3;
			AI1 = player2;
			AI2 = player3;
		}
		
		Game(human,AI1,AI2,campus);

	}
	
	// run the game until 1 player has 150 vp or human quit
	// each player get 3 turn per while loop
	public static void Game(Player human,Player AI1, Player AI2, Room[] campus) {
		int humanNewMovevalue = 3;
		int AI1NewMovevalue = 3;
		int AI2NewMovevalue = 3;
		Room newroom = new Room(0,campus[0].GetNeighbor(),0,0);
		// game start
		boolean check = true;
		while(check == true){
			// game end when 1 of 3 hit 150 VP
			if(human.GetVP() == 150 || AI1.GetVP() == 150 || AI2.GetVP() == 150){
				check = false;
			}
			// quit is selected condition
			//else if(){
			//	
			//}
					
			//human turn 3 move
			for(int i = 0; i < human.Getmovevalue(); i++){
				// displayer human player array of neighbor
				// get array of neighbor
				int[] arrofNeighbor = human.GetLocation().GetNeighbor();
				// take the choice from human to see where to go next
				// subtract move value everyloop
				humanNewMovevalue--;
				human.SetMoveValue(humanNewMovevalue);
			}
			//reset the human move value
			human.SetMoveValue(3);
			humanNewMovevalue = 3;
					
			// ai1 turn 3 move
			for(int i = 0; i < AI1.Getmovevalue(); i++){
				// get array of neighbor
				int[] arrofNeighbor = AI1.GetLocation().GetNeighbor();
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
					AI1.Move(AI1.Getmovevalue(), newroom);
					// decrease move value by 1
					AI1NewMovevalue--;
					AI1.SetMoveValue(AI1NewMovevalue);
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
					AI1.Move(AI1.Getmovevalue(), newroom);
					// decrease move value by 1
					AI1NewMovevalue--;
					AI1.SetMoveValue(AI1NewMovevalue);
				}
			}
			//reset the AI1 move value
			AI1.SetMoveValue(3);
			AI1NewMovevalue = 3;
								
		}
	}

}
