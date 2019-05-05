import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

public class GateMovement implements MouseListener{
	
	private int mouseX = 200;
	private int mouseY = 0;
	private JLabel item = null;
	static GateMovement click;
	//private static MouseMovements single_instance = null; 
	Boolean gateSelected = false;
	
	public GateMovement(ArrayList<JLabel >objects){
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).addMouseListener(this);
		}
	}
	
	public void newObjects(ArrayList<JLabel >objects)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).addMouseListener(this);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		
		for (int i = 0; i < GatePanel.objects.size(); i++)
		{
            if (e.getSource() == GatePanel.objects.get(i)) {
            	GatePanel.objects.get(i).setBounds(mouseX-50, mouseY-100, 100, 80);
            }
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point p = e.getPoint();
		mouseX = MouseInfo.getPointerInfo().getLocation().x;
		mouseY = MouseInfo.getPointerInfo().getLocation().y;

		for (int i = 0; i < GatePanel.objects.size(); i++)
		{
            if (e.getSource() == GatePanel.objects.get(i)) {
            	GatePanel.objects.get(i).setBounds(mouseX-50, mouseY-100, 100, 80);
            }
		}
		
		GatePanel.playingField.repaint();
		}
	
}
