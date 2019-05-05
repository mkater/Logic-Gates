import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

public class AssertInput implements MouseListener{
	
	private int mouseX = 200;
	private int mouseY = 0;
	private JLabel item = null;
	public static Boolean assertChanged = false;
	
	public AssertInput(ArrayList<JLabel> objects)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).addMouseListener(this);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < GatePanel.objects.size(); i++)
		{
			
    	   if (e.getSource() == GatePanel.objects.get(i))
           {
    		   if (GatePanel.allGates.get(i).getGateName().equals("input") && Panel.isEditMode == false)
               {
    			   GatePanel.allGates.get(i).activateTheInput();
    			   System.out.println("Changing assertion value");
    			   assertChanged = true;
               }
           }
        }
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
}
