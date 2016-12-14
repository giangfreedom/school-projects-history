package lab9;
import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.lang.Object;

public class bft {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		
		String listOfInt;
		boolean check = true;
		
		while(check){
			System.out.println("please enter a list of int with comma delimiter");
			listOfInt = in.next();
			// split the string using comma into an array 
			String[] s = listOfInt.split(",");
			// create an array of integer with the same size as s array
			int[] arrOfedges = new int[s.length];
			try{
				for(int q = 0; q < s.length; q++){
					arrOfedges[q] = Integer.parseInt(s[q]);
				}
			}catch(Exception e){
				System.out.println("you need to enter a list of integer" +
						" seperate by comma");
				continue;
			}
			int[] arrOfVertex = new int[arrOfedges.length];
			
			int v = 0;
			for(int i = 0; i < arrOfedges.length; i++){
				if(!contain(arrOfedges[i],arrOfVertex)){
					arrOfVertex[v] = arrOfedges[i];
					v++;
				}
			}
			// correct the size of vertexs array
			int[] arrOfVertex2 = new int[v];
			for(int b = 0; b < arrOfVertex2.length; b++){
				arrOfVertex2[b] = arrOfVertex[b];
			}
			
			LinkedList<MyNode> listOfVertexNode = new LinkedList();
						
			for(int h = 0; h < arrOfVertex2.length; h++){
				int [] arrNeighbor = GetNeighborArr(arrOfVertex2[h], arrOfedges);
								
				MyNode newnode = new MyNode(arrOfVertex2[h], arrNeighbor);
				listOfVertexNode.add(newnode);
			}
			// display the list
			for(int h1 = 0; h1 < listOfVertexNode.size(); h1++){
				System.out.print(listOfVertexNode.get(h1).getvalue());
			}
			System.out.println();
			
			MyGraph myGraph = new MyGraph(listOfVertexNode);
			
			System.out.println(myGraph.GetSize());
			
			for(int j = 1; j < myGraph.GetSize(); j++){	
				//System.out.println("check" + j);
				boolean connected = false;
				connected = bfs(myGraph.FirstNode(), myGraph.GetNode(j), myGraph);
				//System.out.println("check" + j);
				// the first node is not connected to the target node
				if(connected == false){
					System.out.println("the distant between "+ myGraph.FirstNode().getvalue()
							+ " and " +  myGraph.GetNode(j).getvalue() + " is Undefine");
				}
				// connected now find the distance
				else{
					int distance = 1;
					boolean checky = true;
					MyNode n = myGraph.GetNode(j).getparents();
					
					if(myGraph.FirstNode().getvalue() == myGraph.GetNode(j).getvalue()){
						System.out.println("the distant between "+ myGraph.FirstNode().getvalue()
								+ " and " +  myGraph.GetNode(j).getvalue() + " is 0");
					}
											
					while(checky){
						if(myGraph.FirstNode().getvalue() != n.getvalue()){
							n = n.getparents();
							distance++;
						}
						else{
							checky = false;
						}
					}
					System.out.println("the distant between "+ myGraph.FirstNode().getvalue()
							+ " and " +  myGraph.GetNode(j).getvalue() + " is " + distance);
				}
				
				for(int jh = 1; jh < myGraph.GetSize(); jh++){
					myGraph.GetNode(jh).setmark(false);
				}
			}

			/*
			int v = 0;
			for(int i = 0; i < arrOfedges.length; i++){
				if(!contain(arrOfedges[i],arrOfVertex)){
					arrOfVertex[v] = arrOfedges[i];
					v++;
				}
			}
			// correct the size of vertexs array
			int[] arrOfVertex2 = new int[v];
			for(int b = 0; b < arrOfVertex2.length; b++){
				arrOfVertex2[b] = arrOfVertex[b];
			}
					
			int d;
			for(int j = 1; j < arrOfVertex2.length; j++){				
				d = bfs(arrOfVertex2[0], arrOfVertex2[j], arrOfedges);
				System.out.println("the distant between "+ arrOfVertex2[0]
						+ " and " +  arrOfVertex2[j] + " is " + d);
			}
			*/		
		}
	}
	// take a vertex and an array of edges 
	// return the array that contain the vertex neighbor vertex
	public static int[] GetNeighborArr(int base, int[] arrOfEdges){
		int [] neighbor = new int[arrOfEdges.length];
		int index10 = 0;
		int index5 = 0;
		int index6 = arrOfEdges.length - 1;
		
		// forward
		for(int bq = 0; bq < arrOfEdges.length/2; bq++){
			if(base == arrOfEdges[index5]){
				neighbor[index10] = arrOfEdges[index5+1];
				index10++;
			}
			index5 = index5 + 2;
		}
		// backward
		for(int bq1 = 0; bq1 < arrOfEdges.length/2; bq1++){
			if(base == arrOfEdges[index6]){
				// check for loop 1- 1 
				if(!contain(arrOfEdges[index6-1],neighbor)){
					neighbor[index10] = arrOfEdges[index6-1];
					index10++;
				}
			}
			index6 = index6 - 2;
		}
		
		int[] neighbortrim = new int[index10];
		for(int gq = 0; gq < index10; gq++){
			neighbortrim[gq] = neighbor[gq];
		}
		return neighbortrim;
	}
	
	public static boolean bfs(MyNode start, MyNode target, MyGraph graph){
		
		int distant = 0;
		boolean found = false;
		Queue<MyNode> q = new LinkedList<MyNode>();
		
	    //not all node got marked
	    while(AllMarked(graph) == false && found == false){
	    	q.add(start);
	    	//start.setmark(true);
	    	while(!q.isEmpty()){
	    		MyNode u = q.remove();
	    		u.setmark(true);
	    		if(u.getvalue() == target.getvalue()){
	    			found = true;
	    			break;
	    		}
	    		int[] uNeighbor = u.GetNeighbor();
	    			    	
	    		// check for the neighbor
	    		for(int ix = 0; ix < uNeighbor.length; ix++){
	    			for(int iy = 0; iy < graph.GetSize(); iy++){
	    				// if it is neighbor and it is not visited then add it to the queue and mark visited
	    				if(uNeighbor[ix] == graph.GetNode(iy).getvalue()
	    						&& graph.GetNode(iy).getmark() == false){
	    					q.add(graph.GetNode(iy));
	    					//graph.GetNode(iy).setmark(true);
	    					graph.GetNode(iy).setparents(u);
	    				}
	    			}
	    		}
	    	}
	    }
	    if(found == false){
	      return false;
	    }
	    return true;
	}
	
	public static boolean contain(int target, int[] arr){
		if(arr.length == 0){
			return false;
		}
		for(int x = 0; x < arr.length; x++){
			if(target == arr[x]){
				return true;
			}
		}
		return false;
	}
	
	public static boolean AllMarked(MyGraph graph){
		for(int i = 0; i < graph.GetSize(); i++){
			if(graph.GetNode(i).getmark() == false){
				return false;
			}
		}
		return true;
	}

}
