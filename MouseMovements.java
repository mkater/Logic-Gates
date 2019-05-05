import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

/*
 * This class adds mouselisteners to the arrayList of JLabels so you can move them.
 */
public class MouseMovements implements MouseListener{
	
	public MouseMovements(ArrayList<JLabel> objects){
		
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		GatePanel.playingField.repaint();
		}
	
}
