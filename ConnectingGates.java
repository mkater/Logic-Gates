import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;


public class ConnectingGates implements MouseListener{

	Point start = null;
	Point end = null;
	Gate firstSelected = null;
	Gate secondSelected = null;
	
	JLabel firstSelectedLabel = null;
	JLabel secondSelectedLabel = null;
	
	int gatesSelected = 0;
	static int x1 = -1;
	static int y1 = -1;
	static int x2 = -1;
	static int y2 = -1;
	static Boolean lineReady = false;
	
	//ArrayList<Gate> temp = null;
	Gate temp = null;
	
	GatePanel.DrawPanel draw;
	
	static int j = 0;
	
	public ConnectingGates(Gate first, Gate second)
	{
		connectGateFromFile(first, second);
	}
	
	public ConnectingGates(ArrayList<Gate> gate, ArrayList<JLabel> objects)
	{
		System.out.println("Connecting gates got called ");
		
		for(int i = 0; i < objects.size(); i++)
		{
			if (Panel.isEditMode == true)
				objects.get(i).addMouseListener(this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("i: " + j);
		
		j++;
		
		if(gatesSelected > 1)
		{
			System.out.println("Clearing...");
			gatesSelected = 0;
			firstSelected = null;
			secondSelected = null;
			firstSelectedLabel = null;
			secondSelectedLabel = null;
		}
		Point location = e.getPoint();

			for (int i = 0; i < GatePanel.objects.size(); i++)
			{
	           if (e.getSource() == GatePanel.objects.get(i))
	            {
	        	   
	        	   	if(firstSelected == null)
	       			{
	        		   firstSelected = GatePanel.allGates.get(i);
	        		   firstSelectedLabel = GatePanel.objects.get(i);
	        		   gatesSelected++;
	        		   x1 = GatePanel.objects.get(i).getLocation().x;
	        		   y1 = GatePanel.objects.get(i).getLocation().y;
	        		   System.out.println(firstSelected.getGateName()+" selected 1st");

	       			}
	        	   	else 
	        	   	{
	        	   		secondSelected = GatePanel.allGates.get(i);
	        	   		secondSelectedLabel = GatePanel.objects.get(i);
	        	   		gatesSelected++;
	        	   		x2 = GatePanel.objects.get(i).getLocation().x;
	        	   		y2 = GatePanel.objects.get(i).getLocation().y;
	        	   		System.out.println(secondSelected.getGateName()+" selected 2nd");
		        		lineReady = true;
		        		
	        	   		if(firstSelected == secondSelected)
	        	   		{
	        	   			secondSelected = null;
	        	   			secondSelectedLabel = null;
	        	   			gatesSelected--;
	        	   			x2 = -1;
			        		y2 = -1;
			        		lineReady = false;	
	        	   		}
	        	   	}
	            }
			}
			
			if(firstSelected == secondSelected)
	   		{
				secondSelected = null;
	   		}
			
			if(firstSelected != null && secondSelected != null && gatesSelected > 1)
			{
				if(firstSelectedLabel.getLocation().x < secondSelectedLabel.getLocation().x)
					gateDetails(firstSelected, secondSelected);
				else
					gateDetails(secondSelected, firstSelected);
				GatePanel.playingField.repaint();
			}
	}
	
	private void gateDetails(Gate firstSelected, Gate secondSelected) 
	{
		firstSelected.addGate(secondSelected);
		
		for(int i = 0; i< GatePanel.allGates.size(); i++)
		{
			if(GatePanel.allGates.get(i).getGateValue() ==10)
			{
				Gate temp = GatePanel.allGates.get(i);
			}
		}
		
		if(secondSelected.hasPreviousGateA() && secondSelected.getPreviousGateA() == null)
		{
			secondSelected.setPreviousGateA(firstSelected);
			System.out.println(firstSelected.getGateName()+" Connected to "+secondSelected.getGateName());
		}
		else if(firstSelected.hasPreviousGateB() && secondSelected.getPreviousGateB() == null)
		{
			secondSelected.setPreviousGateB(firstSelected);
			System.out.println(firstSelected.getGateName()+" Connected to "+secondSelected.getGateName());
		}
	}

	static public Boolean isLineReady()
	{
		return lineReady;
	}
	
	public void connectGateFromFile(Gate first, Gate second)
	{
		if(gatesSelected > 1)
		{
			System.out.println("Clearing...");
			gatesSelected = 0;
			firstSelected = null;
			secondSelected = null;
			firstSelectedLabel = null;
			secondSelectedLabel = null;
		}
		
		for (int i = 0; i < GatePanel.allGates.size(); i++)
		{
           if (first == GatePanel.allGates.get(i))
           {
        	   firstSelected = GatePanel.allGates.get(i);
        	   firstSelectedLabel = GatePanel.objects.get(i);
        	   gatesSelected++;
        	   x1 = GatePanel.objects.get(i).getLocation().x;
    		   y1 = GatePanel.objects.get(i).getLocation().y;
           }
           else if (second == GatePanel.allGates.get(i))
           {
        	   secondSelected = GatePanel.allGates.get(i);
        	   secondSelectedLabel = GatePanel.objects.get(i);
        	   gatesSelected++;
        	   x2 = GatePanel.objects.get(i).getLocation().x;
    		   y2 = GatePanel.objects.get(i).getLocation().y;
    		   lineReady = true;
           }
		}
		if(firstSelected != null && secondSelected != null && gatesSelected > 1)
		{
			/*if(firstSelected.getDepth() < secondSelected.getDepth())
				System.out.println(firstSelected.getGateName()+" Connected to "+secondSelected.getGateName());
			else
				System.out.println(secondSelected.getGateName()+" Connected to "+firstSelected.getGateName());
		*/}
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
