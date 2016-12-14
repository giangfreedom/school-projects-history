
/**
 * create the private member and methods require to run the game
 * @author giang truong
 * @version date 11/23/2014
 */
import java.net.*;
import java.lang.*;
import java.io.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Panel extends JPanel implements MouseListener,Runnable{
   /** arraylist of 10 planet */
   private  ArrayList<Planet> m_PlanetList;
   /** arraylist of deployed ship */
   private  ArrayList<Ship> m_ShipList;
   /** player object */
   private  Player m_player;
   /** AI object */
   private  Player m_AI;
   
   /** a normal counter for mouse click */
   private  int   m_counter = 0;
   /** each ship source planet */
   private  Planet   m_SourcePlanet;
   /** a ship object */
   private Ship newShip;
   /** an object that random generate number */
   private Random m_generator = new Random();
   /** planet x cord use in planet generator */
   private  int m_x;
   /** planet y cord use in planet generator */
   private  int m_y;
   /** planet size use in planet generator */
   private int m_size;
   /** planet population use in planet generator */
   private int m_population;
   /** the thread that run panel */
   private Thread m_t;
   /** the thread that run regenerator for player*/
   private	Thread m_regenerate;
   /** the thread that run regenerator for ai*/
   private Thread m_regenerate1;
   private Socket s = null;	  	     

   /** the thread that send data to server*/
   private	Thread	m_sendToServer = new Thread(){
	   @Override
	   public void run() {
		  String	PlanetString = new String();
		  String	arrayplanet	= "";
		  PrintStream out;
		  try{
	          out = new PrintStream(s.getOutputStream());
			  while(true){
				  // write a string to server
		  		  // loop 10 time in the planet array and send each planet information
		  		  // to the server follow X,Y,Owner,Size,Population
		  		  for(int i = 0; i < m_PlanetList.size(); i++){
		  			  PlanetString = ""+m_PlanetList.get(i).GetX()+",";
		  			  PlanetString = PlanetString + m_PlanetList.get(i).GetY()+",";
		  			  PlanetString = PlanetString + m_PlanetList.get(i).GetOwner()+",";
		  			  PlanetString = PlanetString + m_PlanetList.get(i).GetSize()+",";
		  			  PlanetString = PlanetString + m_PlanetList.get(i).GetPopulation();
		  			  // send a planet string to server
		  			  if(arrayplanet.equalsIgnoreCase("")){
		  				  arrayplanet = arrayplanet+PlanetString;
		  			  }
		  			  else{
		  				  arrayplanet = arrayplanet+"/"+PlanetString;
		  			  }
		  		  }
		  		  out.println(arrayplanet);
		  		  Thread.sleep(10000); 
		      }
		  }catch(Exception e) {
	        	 System.out.println("error 1");
	      }		  	     
	   }
   };
   /** the thread that receive datafrom server  */
   private	Thread	m_receiveFromServer = new Thread(){
	   @Override
	   public void run() {
	      BufferedReader in;
	      String line = "";
	      try{
	    	  in = new BufferedReader(new InputStreamReader(s.getInputStream()));	
	    	  while(true){	 
	 	        	 // get ai planet and its target from server
	 	        	 line = in.readLine();
	 	        	 // more than 1 ai planet get to atk
	 	  		   	 if(line.contains(".")){
	 	  		   	System.out.println("d");
	 	  		   		 String[]	str = line.split(".");
	 	  		   		 for(int y = 0; y < str.length; y++){
	 	  		   			 String[] s1 = str[y].split("/");
	 	  		   			 AiAtk(s1);
	 	  		   		 }
	 	  		   	 }
	 	  		   	 // only 1 ai planet get to atk
	 	  		   	 else{
	 	  		   		 String[]	s2 = line.split("/");
	 	  		   		 AiAtk(s2);
	 	  		   	System.out.println("k");
	 	  		   	 }
	 	  		  System.out.println("e");
	 	        	 Thread.sleep(100); 
	 	      }
	      }catch(Exception e) {
	    	  System.out.println("error 2");
		  }
	   }
   };
   /** an index */
   private int index = 0;
   private int index1 = 0;
   /** a variable to identify the largest planet for user first planet */
   private int largest = 0;
   
   public	void	AiAtk (String[] s1) {
	   //String	PlanetString = new String();
	   //String	target = new String();
	   //String	AIatker = new String();
	   Planet	targetplanet = new Planet(0,0,0,0,0);
	   Planet	aiatkerplanet = new Planet(0,0,0,0,0);
	   int xcord;
	   int ycord;
	   int owner;
	   int size;
	   int population;
	   System.out.println("b");
	   System.out.println(""+s1[1]);
	   String[]	targettinfo = s1[1].split(",");
	   System.out.println("hhhhhhhhhhh");
	   System.out.println(""+targettinfo[0]);
	   xcord 	= Integer.parseInt(targettinfo[0]);
	   System.out.println("kkkkkkkk");
	   ycord	= Integer.parseInt(targettinfo[1]);
	   System.out.println("xxxxxxxxxx");
	   owner	= Integer.parseInt(targettinfo[2]);
	   System.out.println("ttttttttt");
	   size	= Integer.parseInt(targettinfo[3]);
	   System.out.println("yyyyyyyyyyyyyy");
	   population	= Integer.parseInt(targettinfo[4]);
	   System.out.println("vvvvvvv");
	   targetplanet.SetX(xcord);
	   System.out.println("ppppppp");
	   targetplanet.SetY(ycord);
	   System.out.println("11111111");
	   targetplanet.SetOwner(owner);
	   System.out.println("222222222");
	   targetplanet.SetSize(size);
	   System.out.println("33333333333");
	   targetplanet.SetPopulation(population);
	   System.out.println("c");
	   // ai attacker planet
	   String[]	AIatkerinfo = s1[0].split(",");
	   xcord 	= Integer.parseInt(AIatkerinfo[0]);
	   ycord	= Integer.parseInt(AIatkerinfo[1]);
	   owner	= Integer.parseInt(AIatkerinfo[2]);
	   size	= Integer.parseInt(AIatkerinfo[3]);
	   population	= Integer.parseInt(AIatkerinfo[4]);
	   aiatkerplanet.SetX(xcord);
	   aiatkerplanet.SetY(ycord);
	   aiatkerplanet.SetOwner(owner);
	   aiatkerplanet.SetSize(size);
	   aiatkerplanet.SetPopulation(population);
	   System.out.println("lkl");
	   for(int k = 0; k < m_PlanetList.size(); k++){
		   // check for aiatker planet in array
		   if(m_PlanetList.get(k).GetX() == aiatkerplanet.GetX()
			 && m_PlanetList.get(k).GetY() == aiatkerplanet.GetY()){
			   for(int u = 0; u < m_PlanetList.size(); u++){
				   // check for target planet in the array
				   if(m_PlanetList.get(u).GetX() == targetplanet.GetX()
					 && m_PlanetList.get(u).GetY() == targetplanet.GetY()){
					   int deployedShip = aiatkerplanet.Deploy();
                       int shipXcord;
                       int shipYcord;
                       // source planet on the left of destination planet
                       if(m_PlanetList.get(k).GetX() < m_PlanetList.get(u).GetX()){
                          // source planet is on top of destination planet
                          if(m_PlanetList.get(k).GetY() < m_PlanetList.get(u).GetY()){
                             shipXcord = m_PlanetList.get(k).GetX() + m_PlanetList.get(k).GetSize();
                             shipYcord = m_PlanetList.get(k).GetY() + m_PlanetList.get(k).GetSize();
                          }
                          // source planet is at bottom of destination planet
                          else{
                             shipXcord = m_PlanetList.get(k).GetX() + m_PlanetList.get(k).GetSize();
                             shipYcord = m_PlanetList.get(k).GetY();
                          }   
                       }
                       // source planet on the right of destination planet
                       else{
                          if(m_PlanetList.get(k).GetY() < m_PlanetList.get(u).GetY()){
                             shipXcord = m_PlanetList.get(k).GetX();
                             shipYcord = m_PlanetList.get(k).GetY() + aiatkerplanet.GetSize();
                          }
                          // source planet is at bottom of destination planet
                          else{
                             shipXcord = m_PlanetList.get(k).GetX();
                             shipYcord = m_PlanetList.get(k).GetY();
                          }   
                       }
                       // add all deployed ship to ship array
                       for(int s = 0; s < deployedShip; s++){
                          newShip = new Ship(m_PlanetList.get(k),m_PlanetList.get(u),
                                                  shipXcord,shipYcord,3,2);
                          m_ShipList.add(newShip);
                          System.out.println(s);
                       }                        
				  }
			   }
		   }
	   }	
	   System.out.println("jhg");
   }
  
   /**
    * reate the back ground color and 10 planet, set the largest planet
    * as user initial planet and run 2 thread
    */
   public Panel(){
      this.setBackground(Color.BLACK);
      addMouseListener(this);
      m_PlanetList = new ArrayList<Planet>();
      m_ShipList = new ArrayList<Ship>();
      // init thread
      m_t = new Thread(this);
      // thread that run server and client
      Planet newPlanet = PlanetGenerator();
      // make 10 comp planet and add them to aray
      for(int i = 0; i < 10; i++){   
         if(m_PlanetList.isEmpty()){
            m_PlanetList.add(newPlanet);
            newPlanet = PlanetGenerator();
         }
         else{
            if(IsWithinOldPlanet(newPlanet) == true){
            	System.out.println("1");
            	System.out.println("12");
            	System.out.println("123");
            	newPlanet = PlanetGenerator();
            	i--;
            }
            else{
            	m_PlanetList.add(newPlanet);
            	newPlanet = PlanetGenerator();
            }
         }  
      }
      // pick the largest planet
      for(int l = 0; l < 10; l++){
         if(m_PlanetList.get(l).GetSize() > largest){
            largest = m_PlanetList.get(l).GetSize();
            index = l;
         }
      }
      // set the largest planet to user control
      m_PlanetList.get(index).SetOwner(1);
      // set the planet player control population to 100
      m_PlanetList.get(index).SetPopulation(100);
      // reset largest
      largest = 0;
      for(int v = 0; v < 10; v++){
          if(m_PlanetList.get(v).GetSize() > largest && m_PlanetList.get(v).GetOwner() == 2){
             largest = m_PlanetList.get(v).GetSize();
             index1 = v;
          }
       }
      // set the 2nd largest planet to AI control
      m_PlanetList.get(index1).SetOwner(3);

      // set the planet AI control population to 100
      m_PlanetList.get(index1).SetPopulation(100);

      
      // start the thread
      m_player = new Player(1,m_PlanetList.get(index));
      m_AI = new Player(3,m_PlanetList.get(index1));
      m_regenerate = new Thread(m_player);
      m_regenerate1 = new Thread(m_AI);
      
      try{
   	   InetAddress ip = InetAddress.getByName("localhost");
   	   s = new Socket(ip, 3075);
      }catch(Exception e) {
     	 System.out.println("error 0");
      }	
      
      m_t.start();
      m_regenerate.start();
      m_regenerate1.start();
      m_sendToServer.start();
      m_receiveFromServer.start();
   }
   
   /**
    * a function that generate planet at random cord/size/pop
    * @return a new planet
    */
   public   Planet   PlanetGenerator(){
      m_x = m_generator.nextInt(500);
      m_y = m_generator.nextInt(500);
      m_size = (m_generator.nextInt(4)+2);
      m_population = m_size * 20;
      Planet planet = new Planet(m_population, m_x, m_y, m_size * 20, 2);
      return planet;
   }
   /* mouse click check the use mouse click 
    * (non-Javadoc)
    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
    */
   public void mouseClicked (MouseEvent event) {
      if(m_counter == 0){
    	 int x_pos = event.getX();
         int y_pos = event.getY();
         for(int p = 0; p < 10; p++){
            // check is the 1st click within a planet of the 10 planets
            if(x_pos <= m_PlanetList.get(p).GetX() + m_PlanetList.get(p).GetSize()
                  && x_pos >= m_PlanetList.get(p).GetX()){
                  if(y_pos <= m_PlanetList.get(p).GetY() + m_PlanetList.get(p).GetSize()
                     && y_pos >= m_PlanetList.get(p).GetY()){
                     // check if the planet belong to user
                     // if it is increase counter by 1 counter = 1 now
                     if(m_PlanetList.get(p).GetOwner() == 1){
                        m_counter++;
                        m_SourcePlanet = m_PlanetList.get(p);
                     }
                  }  
            }     
         }
      }
      else if(m_counter == 1){
    	  int xpos = event.getX();
          int ypos = event.getY();
    	 // check 10 planets
         for(int p = 0; p < 10; p++){
            // check is the 1st click within a planet of the 10 planets
            if(xpos <= m_PlanetList.get(p).GetX()+m_PlanetList.get(p).GetSize()
                  && xpos >= m_PlanetList.get(p).GetX()){
                  if(ypos <= m_PlanetList.get(p).GetY()+m_PlanetList.get(p).GetSize()
                     && ypos >= m_PlanetList.get(p).GetY()){
                     // check if the planet belong to user
                     // if it is and it is not source planet 
                     //then deploy troop to that planet for reinforcfement
                     if(m_PlanetList.get(p).GetOwner() == 1 && 
                           (m_PlanetList.get(p).GetX() != m_SourcePlanet.GetX() ||
                           m_PlanetList.get(p).GetY() != m_SourcePlanet.GetY())){
                        int deployedShip = m_SourcePlanet.Deploy();
                        int shipXcord;
                        int shipYcord;
                        // source planet on the left of destination planet
                        if(m_SourcePlanet.GetX() < m_PlanetList.get(p).GetX()){
                           // source planet is on top of destination planet
                           if(m_SourcePlanet.GetY() < m_PlanetList.get(p).GetY()){
                              shipXcord = m_SourcePlanet.GetX() + m_SourcePlanet.GetSize();
                              shipYcord = m_SourcePlanet.GetY() + m_SourcePlanet.GetSize();
                           }
                           // source planet is at bottom of destination planet
                           else{
                              shipXcord = m_SourcePlanet.GetX() + m_SourcePlanet.GetSize();
                              shipYcord = m_SourcePlanet.GetY();
                           }   
                        }
                        // source planet on the right of destination planet
                        else{
                           if(m_SourcePlanet.GetY() < m_PlanetList.get(p).GetY()){
                              shipXcord = m_SourcePlanet.GetX();
                              shipYcord = m_SourcePlanet.GetY() + m_SourcePlanet.GetSize();
                           }
                           // source planet is at bottom of destination planet
                           else{
                              shipXcord = m_SourcePlanet.GetX();
                              shipYcord = m_SourcePlanet.GetY();
                           }   
                        }
                        // add all deployed ship to ship array
                        for(int s = 0; s < deployedShip; s++){
                           newShip = new Ship(m_SourcePlanet,m_PlanetList.get(p),
                                                   shipXcord,shipYcord,1,2);
                           m_ShipList.add(newShip);
                        }                        
                     }
                     // computer planet ==> attack!!!
                     else if(m_PlanetList.get(p).GetOwner() == 2){
                        int deployedShip = m_SourcePlanet.Deploy();
                        int shipXcord;
                        int shipYcord;
                        // source planet on the left of destination planet
                        if(m_SourcePlanet.GetX() < m_PlanetList.get(p).GetX()){
                           // source planet is on top of destination planet
                           if(m_SourcePlanet.GetY() < m_PlanetList.get(p).GetY()){
                              shipXcord = m_SourcePlanet.GetX() + m_SourcePlanet.GetSize();
                              shipYcord = m_SourcePlanet.GetY() + m_SourcePlanet.GetSize();
                           }
                           // source planet is at bottom of destination planet
                           else{
                              shipXcord = m_SourcePlanet.GetX() + m_SourcePlanet.GetSize();
                              shipYcord = m_SourcePlanet.GetY();
                           }   
                        }
                        // source planet on the right of destination planet
                        else{
                           if(m_SourcePlanet.GetY() < m_PlanetList.get(p).GetY()){
                              shipXcord = m_SourcePlanet.GetX();
                              shipYcord = m_SourcePlanet.GetY() + m_SourcePlanet.GetSize();
                           }
                           // source planet is at bottom of destination planet
                           else{
                              shipXcord = m_SourcePlanet.GetX();
                              shipYcord = m_SourcePlanet.GetY();
                           }   
                        }
                        // add all deployed ship to ship array
                        for(int s = 0; s < deployedShip; s++){
                           newShip = new Ship(m_SourcePlanet,m_PlanetList.get(p),
                                                   shipXcord,shipYcord,1,2);
                           m_ShipList.add(newShip);
                        }                        
                     }
                     else{
                        System.out.println("error mouse click");
                     }
                  }  
            }     
         }
         // reset counter either the player click 2nd time correctly or not still
         // have to reset
         m_counter = 0;
      }
      else{
    	  System.out.println("error 11111");
      }
      
   }
   public void mousePressed (MouseEvent event) {}
   public void mouseReleased (MouseEvent event) {}
   public void mouseEntered (MouseEvent event) {}
   public void mouseExited (MouseEvent event) {}

   /**
    * paint the planets and ships
    */
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      //draw planet if we have them in array list
      if(!m_PlanetList.isEmpty()){
         for(int q = 0; q < m_PlanetList.size(); q++){
        	 if(m_PlanetList.get(q).GetOwner() == 2){
        		 m_PlanetList.get(q).DrawPlanet(g);
        	 }
        	 if(m_PlanetList.get(q).GetOwner() == 3){
        		 m_PlanetList.get(q).DrawPlanet(g);
        	 }
        	 else{
        		 for(int j = 0; j < m_player.GetNumPlanet(); j++){
        			 m_player.GetArray().get(j).DrawPlanet(g);
        		 }
        	 }
         }
         
         if(m_player.GetNumPlanet() >= 10){
        	 g.setColor(Color.WHITE);
        	 g.drawString("YOU WIN", 350, 350);
         }
         if(m_AI.GetNumPlanet() >= 10){
        	 g.setColor(Color.WHITE);
        	 g.drawString("YOU LOSE", 350, 350);
         }
      }
 
      if(!m_ShipList.isEmpty()){
    	  // draw the ship
    	  for(int n = 0; n < m_ShipList.size(); n++){
              m_ShipList.get(n).DrawShip(g);
              System.out.println("aaaaaaaaa");
          }
    	  // check is the ship arrived and move
          for(int z = 0; z < m_ShipList.size(); z++){
        	  Planet m_destination = m_ShipList.get(z).GetDestination();
        	  // player ship conquer
        	  if(m_destination.GetPopulation() <= 0 && m_ShipList.get(z).GetOwner() == 1){
        		  m_player.ClaimPlanet(m_destination);
        	  }
        	  else if(m_destination.GetPopulation() <= 0 && m_ShipList.get(z).GetOwner() == 3){
        		  m_AI.ClaimPlanet(m_destination);
        	  }

             // ship arrived, destination planet get +1 population
             if(m_ShipList.get(z).CheckDestination(m_destination)){
                if(m_ShipList.get(z).GetOwner() == 1 && m_destination.GetOwner() == 1){
                	m_destination.SetPopulation(m_destination.GetPopulation() + 1);
                }
                else if (m_ShipList.get(z).GetOwner() == 1 && m_destination.GetOwner() != 1){
                	m_destination.SetPopulation(m_destination.GetPopulation() - 1);
                }
                else if(m_ShipList.get(z).GetOwner() == 3 && m_destination.GetOwner() == 3){
                	m_destination.SetPopulation(m_destination.GetPopulation() + 1);
                }
                else if(m_ShipList.get(z).GetOwner() == 3 && m_destination.GetOwner() != 3){
                	m_destination.SetPopulation(m_destination.GetPopulation() - 1);
                }
                m_ShipList.remove(z);
                // bonus if the planet pop reach 0 terminate the loop and wipe out the remaining ship in array
             }
             // ship has not arrived
             else{
                m_ShipList.get(z).Move(m_destination);
             }
          }         
       }  
   }
   /**
    * override needed for run method
    */
   @Override
   public void run() {
      while(true){
         repaint();
         try {
         Thread.sleep(100); //60 fps
         } catch (InterruptedException e) {
        	 System.out.println("run in panel error");
         }
      }
   }
   /*
    * check to see is a new planet within an old planet
    * or an old planet within a new planet, basically check
    * for planet conflict while create 10 planet
    */
   public	Boolean	IsWithinOldPlanet(Planet	newPlanet){
      for(int j = 0; j < m_PlanetList.size(); j++){
	     // new <= old
		 // check top left cord of newplanet (x cord)
		 if(newPlanet.GetX() <= m_PlanetList.get(j).GetX() + m_PlanetList.get(j).GetSize()
		    && newPlanet.GetX() >= m_PlanetList.get(j).GetX()){
		    // now check y cord
		    if(newPlanet.GetY() <= m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() + 100
		       && newPlanet.GetY() >= m_PlanetList.get(j).GetY() - 100){
		       return true;
		       }  
		 }
		 // top right 
		 else if(newPlanet.GetX() + newPlanet.GetSize() <= m_PlanetList.get(j).GetX()+m_PlanetList.get(j).GetSize()
		    && newPlanet.GetX() + newPlanet.GetSize() >= m_PlanetList.get(j).GetX()){
		    // now check y cord
		    if(newPlanet.GetY() <= m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() + 100
		       && newPlanet.GetY() >= m_PlanetList.get(j).GetY() - 100){
		       return true;
		    }  
		 }
		 // bottom left
		 else if(newPlanet.GetX() <= m_PlanetList.get(j).GetX() + m_PlanetList.get(j).GetSize()
		     && newPlanet.GetX() >= m_PlanetList.get(j).GetX()){
		     // now check y cord
		     if(newPlanet.GetY() + newPlanet.GetSize() <= m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() + 100
		        && newPlanet.GetY() + newPlanet.GetSize() >= m_PlanetList.get(j).GetY() - 100){
		        return true;
		     }  
		 }
		 // bottom right
		 else if(newPlanet.GetX() + newPlanet.GetSize() <= m_PlanetList.get(j).GetX()+m_PlanetList.get(j).GetSize()
		     && newPlanet.GetX() + newPlanet.GetSize() >= m_PlanetList.get(j).GetX()){
		     // now check y cord
		     if(newPlanet.GetY() + newPlanet.GetSize() <= m_PlanetList.get(j).GetY()+m_PlanetList.get(j).GetSize() + 100
		        && newPlanet.GetY() + newPlanet.GetSize() >= m_PlanetList.get(j).GetY() - 100){
		        return true;
		     }  
		 }
		 // new >= old
		 else if(m_PlanetList.get(j).GetX() <= newPlanet.GetX() + newPlanet.GetSize()
		    && m_PlanetList.get(j).GetX() >= newPlanet.GetX()){
		    // now check y cord
		    if(m_PlanetList.get(j).GetY() <= newPlanet.GetY() + m_PlanetList.get(j).GetSize() + 100
		       && m_PlanetList.get(j).GetY() >= newPlanet.GetY() - 100){
		       return true;
		       }  
		 }
		 // top right 
		 else if(m_PlanetList.get(j).GetX() + m_PlanetList.get(j).GetSize() <= newPlanet.GetX() + newPlanet.GetSize()
		     && m_PlanetList.get(j).GetX() + m_PlanetList.get(j).GetSize() >= newPlanet.GetX()){
		     // now check y cord
		     if(m_PlanetList.get(j).GetY() <= newPlanet.GetY() + m_PlanetList.get(j).GetSize() + 100
		        && m_PlanetList.get(j).GetY() >= newPlanet.GetY() - 100){
		        return true;
		        }  
		 }
		 // bottom left
		 else if(m_PlanetList.get(j).GetX() <= newPlanet.GetX() + newPlanet.GetSize()
		     && m_PlanetList.get(j).GetX() >= newPlanet.GetX()){
		     // now check y cord
		     if(m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() <= newPlanet.GetY()+m_PlanetList.get(j).GetSize() + 100
		        && m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() >= newPlanet.GetY() - 100){
		        return true;
		     }  
		 }
		 // bottom right
		 else if(m_PlanetList.get(j).GetX() + m_PlanetList.get(j).GetSize() <= newPlanet.GetX() + newPlanet.GetSize()
		     && m_PlanetList.get(j).GetX() + m_PlanetList.get(j).GetSize() >= newPlanet.GetX()){
		     // now check y cord
		     if(m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() <= newPlanet.GetY() + m_PlanetList.get(j).GetSize() + 100
		        && m_PlanetList.get(j).GetY() + m_PlanetList.get(j).GetSize() >= newPlanet.GetY() - 100){
		        return true;
		        }  
		 }            		                		  
      }
	   return false;
   }
}
