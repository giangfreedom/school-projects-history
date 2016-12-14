
/**
 * create and initial a player object
 * @author giang truong
 * @version date 11/23/2014
 */
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Player implements Runnable{
	/** player id */
   private int m_idnumber;//1 for player, 2 for comp
   /** arraylist of planets that player own */
   private ArrayList<Planet> m_planetList1;
   /**
    * initialize a player object
    * @param id is player id
    * @param startPlanet is player beginning planet
    */
   public   Player(int id, Planet startPlanet){
      m_idnumber = id;
      m_planetList1 = new ArrayList<Planet>();
      m_planetList1.add(startPlanet);
   }
   /**
    * return the array that contain planets that player own
    * @return m_planetList1
    */
   public	ArrayList<Planet> GetArray(){
	   return m_planetList1;
   }
   /**
    * return number of planet that player own
    * @return m_planetList1.size()
    */
   public	int	GetNumPlanet(){
	   return m_planetList1.size();
   }
   
  
   /**
    * regenerate each planet player own base on it population every 2 seconds
    */
   public   void  Regenerate(){
      for(int i = 0; i < m_planetList1.size(); i++){
    	  m_planetList1.get(i).SetPopulation(m_planetList1.get(i).GetPopulation() + 
    			  (m_planetList1.get(i).GetSize() / 25));
      }   
   }
   /**
    * take a planet that player conquered
    * @param target conquered planet
    */
   public void ClaimPlanet(Planet target){
      //method to claim a planet and add it to the arraylist of planets
	   int check = 0;
      target.SetOwner(1);
      for(int i = 0; i < m_planetList1.size(); i++){
    	  if(target.GetX() == m_planetList1.get(i).GetX()){
    		  if(target.GetY() == m_planetList1.get(i).GetY()){
    	    		 check = 1;
        	  }    	  
    	  }    	  
      }
      
      if(check == 0){
    	  m_planetList1.add(target);
      }
   }
/**
 * over ride run method
 */
   @Override
   public void run() {
	   while(true){
		   try{
			   Regenerate();
			   Thread.sleep(2000);
		   }catch(InterruptedException e){}
	   }
   }
}
