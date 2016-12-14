
/**
 * @author giang truong
 * @version date 11/23/2014
 * @Program:  space conquer frame
 * Desc:  create the frame and panel object for the whole project
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Frame extends JFrame{
	/** panel object */
   private Panel panel;
   /**
    * frame constructor
    */
   public Frame() {
      //setBounds(100, 100, 700, 700);//x,y,w,h of window
      setSize(700,700);
      panel = new Panel();
      this.setContentPane(panel);
   }
   
   public static void main(String [] args){
      Frame f = new Frame();
      f.setTitle("Space Conquer");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
   }
}
