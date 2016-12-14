package project5;

import java.net.*;
import java.util.ArrayList;
import java.lang.*;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
public	class	Server1{
	public static void main(String [] args){
		ArrayList<Planet> m_PlanetList = new ArrayList<Planet>();
		Planet newplanet;
		for(int q = 0; q < 10; q++){
			newplanet = new Planet(0,0,0,0,0);
			m_PlanetList.add(newplanet);
		}
		
		Thread	receiveFromClient = new Thread(){
			@Override
			public void run() {
			   int xcord;
			   int ycord;
			   int owner;
			   int size;
			   int population;
			   String line = "";
			   Planet closestplanet = new Planet(0,0,0,0,0);
			   int closestx = 0;
			   int closesty = 0;
			   Planet target;
			   Planet AI;
			   String line1 = "";
			   PrintStream out;
			   ServerSocket server;
			   Socket s = null;
			   BufferedReader in;
			   System.out.println("Waiting...");
			   try{
				   server = new ServerSocket(2075);
				   s = server.accept();
				   System.out.println("Connected: "+ s.getInetAddress());
				   //get message from client
			       in = new BufferedReader(new InputStreamReader(s.getInputStream())); 
			       out = new PrintStream(s.getOutputStream());
				   while(true){
				    	  line = in.readLine();
				    	  String[] arrayplanet = line.split("/");
							for(int i = 0; i < m_PlanetList.size(); i++){
								String[]	planetinfo = arrayplanet[i].split(",");
								xcord 	= Integer.parseInt(planetinfo[0]);
								ycord	= Integer.parseInt(planetinfo[1]);
								owner	= Integer.parseInt(planetinfo[2]);
								size	= Integer.parseInt(planetinfo[3]);
								population	= Integer.parseInt(planetinfo[4]);
								m_PlanetList.get(i).SetX(xcord);
								m_PlanetList.get(i).SetY(ycord);
								m_PlanetList.get(i).SetOwner(owner);
								m_PlanetList.get(i).SetSize(size);
								m_PlanetList.get(i).SetPopulation(population);	
							}
							
							for(int j = 0; j < m_PlanetList.size(); j++){
								// AI planet
								if(m_PlanetList.get(j).GetOwner() == 3){
									for(int v = 0; v < m_PlanetList.size(); v++){
										// check for closest planet
										if(m_PlanetList.get(v).GetOwner() == 1 || m_PlanetList.get(v).GetOwner() == 2){
											if(closestx == 0 && closesty == 0){
												closestx = Math.abs(m_PlanetList.get(j).GetX() - m_PlanetList.get(v).GetX());
												closesty = Math.abs(m_PlanetList.get(j).GetY() - m_PlanetList.get(v).GetY());
												closestplanet = m_PlanetList.get(v);
											}
											else if(Math.abs(m_PlanetList.get(j).GetX() - m_PlanetList.get(v).GetX()) < closestx
													&& Math.abs(m_PlanetList.get(j).GetY() - m_PlanetList.get(v).GetY()) < closesty){
												closestplanet = m_PlanetList.get(v);
											}// end of else if
										}// end of if
									}// end of inner for loop
									// convert target planet info to a string
									target = closestplanet;
									String	targetplanet = ""+target.GetX()+",";
									targetplanet = targetplanet+target.GetY()+",";
									targetplanet = targetplanet+target.GetOwner()+",";
									targetplanet = targetplanet+target.GetSize()+",";
									targetplanet = targetplanet+target.GetPopulation();
									// convert AI planet that selected this target planet to a string
									AI = m_PlanetList.get(j);
									String	AIatker = ""+AI.GetX()+",";
									AIatker = AIatker+AI.GetY()+",";
									AIatker = AIatker+AI.GetOwner()+",";
									AIatker = AIatker+AI.GetSize()+",";
									AIatker = AIatker+AI.GetPopulation();
									if(line1.equalsIgnoreCase("")){
										line1 = line1+AI+"/"+target;
									}
									else{
										line1 = line1+"."+AI+"/"+target;
									}
								}// end of outer if
							}// end of outer for loop
							// send the ai planet and its target planet to client
							out.println(line1);	
					  	  Thread.sleep(100); 
				   }				   
			   }catch(Exception e) {
			    	  System.out.println("error 3");
			   }		
			}
		};
		receiveFromClient.start();
	}
	//out.close();
	//s.close();
	
	
}