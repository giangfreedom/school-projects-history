package lab9;
import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.lang.Object;

public class MyGraph {
	
	private LinkedList<MyNode> myGraph;
	private int NumEdges;
	
	public MyGraph(LinkedList<MyNode> listVertexs){
		myGraph = listVertexs;
	}
	
	public MyNode FirstNode(){
		return myGraph.getFirst();
	}
	
	public MyNode GetNode(int index){
		return myGraph.get(index);
	}
	
	public int GetSize(){
		return myGraph.size();
	}
}
