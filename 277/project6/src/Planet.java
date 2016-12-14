
/**
 * create and initial a planet 
 * @author giang truong
 * @version date 11/23/2014
 */
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Planet {
	/** planet population start at 20 default */
   private int m_population = 20;
   /** planet x cord */
   private int m_x;
   /** planet y cord */
   private int m_y;
   /** planet size */
   private  int m_size;
   /** planet owner 1 for player 2 for neutral 3 for AI*/
   private int m_PlayerOwner;
   
   /**
    * initialize the planet (constructor)
    * @param P is population
    * @param x is x cord
    * @param y is y cord
    * @param S is planet size
    * @param owner is planet owner 1 or 2 or 3
    */
   public   Planet(int P, int x, int y, int S, int owner){
      m_population = P;
      m_x = x;
      m_y = y;
      m_size = S;
      m_PlayerOwner = owner;
   }
   /**
    * return 75% of planet population as deploy troop
    * @return deploy number
    */
   public   int   Deploy(){
      int   deployNumber = 0;
      deployNumber = (m_population * 75) /100; 
      m_population = m_population - deployNumber;
      return deployNumber;
   }
   /**
    * draw each planet base on its owner
    * @param g graphic
    */
   public   void  DrawPlanet(Graphics g){
      //computer planet
      if(m_PlayerOwner == 3){
         g.setColor(Color.RED);
         g.fillOval(m_x, m_y, m_size, m_size);
         g.setColor(Color.BLACK);
         g.drawString(""+m_population,m_x+(m_size/2),m_y+(m_size/2));
      }
      //player planet
      if(m_PlayerOwner == 1){
         g.setColor(Color.GREEN);
         g.fillOval(m_x, m_y, m_size, m_size);
         g.setColor(Color.BLACK);
         g.drawString(""+m_population,m_x+(m_size/2),m_y+(m_size/2));
      }
      if(m_PlayerOwner == 2){
    	  g.setColor(Color.GRAY);
          g.fillOval(m_x, m_y, m_size, m_size);
          g.setColor(Color.BLACK);
          g.drawString(""+m_population,m_x+(m_size/2),m_y+(m_size/2));
      }
   }
   /**
    * set planet population
    * @param P is population
    */
   public   void  SetPopulation(int P){
      m_population = P;
   }
   /**
    * set planet x cord
    * @param x is planet x cord
    */
   public   void  SetX(int x){
      m_x = x;
   }
   /**
    * set planet y cord
    * @param y is planet y cord
    */
   public   void  SetY(int y){
      m_y = y;
   }
   /**
    * set planet size
    * @param S is planet size
    */
   public   void  SetSize(int S){
      m_size = S;
   }
   /**
    * set planet owner
    * @param owner is planet owner
    */
   public   void  SetOwner(int owner){
      m_PlayerOwner = owner;
   }
   
   /**
    * return planet population 
    * @return m_population
    */
   public   int  GetPopulation(){
      return m_population;
   }
   /**
    * return planet x cord
    * @return m_x
    */
   public   int  GetX(){
      return m_x;
   }
   /**
    * return planet y cord
    * @return m_y
    */
   public   int  GetY(){
      return m_y;
   }
   /**
    * return planet size
    * @return m_size
    */
   public   int  GetSize(){
      return m_size;
   }
   /**
    * return planet owner
    * @return m_PlayerOwner
    */
   public   int  GetOwner(){
      return m_PlayerOwner;
   }

}
