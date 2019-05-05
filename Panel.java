import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/*
 * Sets the layout of the main panel with checks to see if you are in edit mode or build mode, and the way to call the optimize function to optimize the gates.
 * Here optimize will keep looping until none of the optimizes triggers, in case one removal triggers another.
 */
public class Panel extends JPanel
{
	public static Boolean isEditMode = true;
	public static Boolean isOptimize = false;
	Optimize optimize = new Optimize();
	
	public Panel()
	{
		setLayout(new GridBagLayout());		
		showButton();
	}

	private void showButton() 
	{
		ImageIcon star = new ImageIcon("./Pictures/Star.png");
		ImageIcon arrow = new ImageIcon("./Pictures/RightArrow.png");
		
		JButton starButton = new JButton(star);
		JButton arrowButton = new JButton(arrow);
		
		// sets window to Edit Mode
		starButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				isEditMode = false;
				isOptimize = true;
				
				for(int i = 0; i < GatePanel.allGates.size(); i++)
				{
					System.out.println(GatePanel.allGates.get(i).getGateName() + " " + GatePanel.allGates.get(i).getOutgoingGateSize());
				}
				
				if(isOptimize)
				{
					do
					{
						optimize.removeDeadGates(GatePanel.allGates);
						optimize.removeAlwaysGates(GatePanel.allGates);
						optimize.removeDuplicates(GatePanel.allGates);
						
					}while(optimize.getOptimizeAlways()!= 0 && optimize.getOptimizeDuplicate()!= 0 && optimize.getOptimzeDead()!=0);
				}
				GatePanel.playingField.repaint();
			}
		});
		
		// sets window to Play Mode
		arrowButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				isEditMode = !isEditMode;
				isOptimize = false;
			}
		});
		
		starButton.setPreferredSize(new Dimension(80,75));
		arrowButton.setPreferredSize(new Dimension(75,75));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 1;
		
		add(starButton);
		add(arrowButton, c);
	}	
	
	// returns windows current Mode
	public static Boolean getIsEditMode() 
	{
		return isEditMode;
	}
	public static Boolean getIsOptimzeMode() 
	{
		return isOptimize;
	}
}