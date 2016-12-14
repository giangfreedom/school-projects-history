package project5;
/**
 * create and initial ship object
 * @author giang truong
 * @version date 11/23/2014
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ship {
   /** the source planet of the ship */
   private  Planet m_Source;
   /** the destination planet of the ship */
   private  Planet m_Destination;
   /** the x cord of the ship */
   private  int   m_x;
   /** the y cord of the ship */
   private  int   m_y;
   /** the player that own the ship */
   private  int   m_PlayerOwner;
   /** the size of the ship */
   private  int   m_Size;// display size of the ship (5px?)
   /** a object that generate random number */
   private Random m_generator = new Random();
   
   /**
    * initialize ship object
    * @param Source is the source planet of the ship
    * @param Destination is the destination planet of the ship
    * @param x is the ship x cord
    * @param y is the ship y cord 
    * @param owner is the ship owner
    * @param size is the ship size
    */
   public   Ship(Planet Source, Planet Destination, int x, int y, int owner, int size){
      m_Source = Source;
      m_Destination = Destination;
      m_x = x;
      m_y = y;
      m_PlayerOwner = owner;
      m_Size = size;
   }
   
   /**
    * move the ship on the map
    * @param destination is the ship destination
    */
   public   void  Move(Planet destination){
      // ship is on the left of destination planet
      if(m_x <= destination.GetX()){
         // source planet is on top of destination planet
         if(m_y <= destination.GetY()){
            m_x = m_x + m_generator.nextInt(3);
            m_y = m_y + m_generator.nextInt(3);
         }
         // source planet is at bottom of destination planet
         else{
        	 m_x = m_x + m_generator.nextInt(3);
        	 m_y = m_y - m_generator.nextInt(3);
         }   
      }
      // source planet on the right of destination planet
      else{
         if(m_y <= destination.GetY()){
        	 m_x = m_x - m_generator.nextInt(3);
        	 m_y = m_y + m_generator.nextInt(3);
         }
         // source planet is at bottom of destination planet
         else{
        	m_x = m_x - m_generator.nextInt(3);
            m_y = m_y - m_generator.nextInt(3);
         }   
      }
   }
   
   /**
    * check to see if the ship at its destination or not
    * @param destination the ship destination
    * @return true or false
    */
   public   boolean  CheckDestination(Planet destination){
      if(m_x <= destination.GetX() + destination.GetSize()
            && m_x >= destination.GetX()){
            if(m_y <= destination.GetY()+destination.GetSize()
               && m_y >= destination.GetY()){
               return true;
            }
      }
      return false;
   }
   
   /**
    * draw the ship to the panel
    * @param g graphic
    */
   public   void  DrawShip(Graphics g){
      if(m_PlayerOwner == 3){
         g.setColor(Color.BLUE);
         g.fillOval(m_x, m_y,m_Size,m_Size);
      }
      //player planet
      else{
         g.setColor(Color.WHITE);
         g.fillOval(m_x, m_y,m_Size,m_Size);
      }
   }
   
   /**
    * set the source planet of the ship 
    * @param Source is the source planet
    */
   public   void  SetSource(Planet Source){
      m_Source = Source;
   }
   /**
    * set the destination planet of the ship
    * @param Des is the destination planet
    */
   public   void  SetDestination(Planet Des){
      m_Destination = Des;
   }
   /**
    * set the x cord of the ship 
    * @param x is the ship x cord
    */
   public   void  SetX(int x){
      m_x = x;
   }
   /**
    * set the y cord of the ship 
    * @param y is the ship y cord
    */
   public   void  SetY(int y){
      m_y = y;
   }
   /**
    * set the size of the ship 
    * @param S is the ship size
    */
   public   void  SetSize(int S){
      m_Size = S;
   }
   /**
    * set the owner of the ship
    * @param owner is the ship owner
    */
   public   void  SetOwner(int owner){
      m_PlayerOwner = owner;
   }
   
   /**
    * get the ship source planet
    * @return m_Source
    */
   public   Planet  GetSource(){
      return m_Source;
   }
   /**
    * get the ship destination planet
    * @return m_Destination
    */
   public   Planet  GetDestination(){
      return m_Destination;
   }
   /**
    * get the ship x cord
    * @return m_x
    */
   public   int  GetX(){
      return m_x;
   }
   /**
    * get the ship y cord
    * @return m_y
    */
   public   int  GetY(){
      return m_y;
   }
   /**
    * get the ship size
    * @return m_Size
    */
   public   int  GetSize(){
      return m_Size;
   }
   /**
    * get the ship owner
    * @return m_PlayerOwner
    */
   public   int  GetOwner(){
      return m_PlayerOwner;
   }
}
