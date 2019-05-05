import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
/*
 * The main class where all the classes are instantiated into objects and the program is run.
 */
public class Frame extends JFrame
{
   public Frame() throws FileNotFoundException
   {
      super("");

      menuButtons menu = new menuButtons();
      setJMenuBar(menu);
      
      
      Container contents = getContentPane();
      
      Panel mainField = new Panel();
      GatePanel.playingField = new GatePanel("./src/Test.txt");
    
      contents.add(mainField, BorderLayout.LINE_END);
      contents.add(GatePanel.playingField);

      Dimension d = GatePanel.playingField.getSize(); 
   
      setSize(1000,1000);
      setVisible(true);      
   }

   public static void main(String[] args) throws FileNotFoundException
   {
      Frame theFrame = new Frame();

      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   
}