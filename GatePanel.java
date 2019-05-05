import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GatePanel extends JPanel{

	
	static ArrayList<JLabel> objects = new ArrayList<JLabel>();
	//static ArrayList<JLabel> wires = new ArrayList<JLabel>();
	static ArrayList<Gate>allGates = new ArrayList<Gate>();
	static JLabel[] icons;
	
	//private int mouseX = 0;
	//private int mouseY = 0;
	
	GateMovement move = null;
	ConnectingGates drawWire = null;
	InsertGate gates = null;
	
	static GatePanel playingField;
	Boolean newFile = false;
	
	ArrayList<drawWires> wires = new ArrayList<drawWires>();
	ArrayList<Gate> temp = null;
	GateMovement moveWire = null;
	
	int offsetX = 0;
	int offsetY = 0;
	MouseMovements click = null;
	AssertInput assertInp = null;
	
	public GatePanel(String filedata) {
		
		try 
		{
			ReadFile readFile = new ReadFile();
			readFile.readtheFile(filedata, allGates);
			newFile = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Fill array "icons" with gates read from fill and add them to the panel
		updating();
		
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		
			super.paintComponent(g);
			
			assertInp = new AssertInput(objects);
			
			if(AssertInput.assertChanged)
			{
				removeAll();
				
				GateHeight gh = new GateHeight();
				gh.calculateGateHeight(allGates);
				
				for(int i = 0; i < allGates.size(); i++)
				{
					 allGates.get(i).setGateHeight(-1);
				}
				 
				objects.clear();
				updating();
				AssertInput.assertChanged = false;
			}
			
			wires.clear();
			
			if(Panel.isOptimize)
			{
				removeAll();
				
				GateHeight gh = new GateHeight();
				gh.calculateGateHeight(allGates);
				
				for(int i = 0; i < allGates.size(); i++)
				{
					 allGates.get(i).setGateHeight(-1);
				}
				 
				objects.clear();
				updating();
				Panel.isOptimize = false;
			}
			
			// Loop through allGates to find any connections
			for(int i = 0; i < allGates.size(); i++)
			{
				if(allGates.get(i).getOutgoingGateSize() > 0)
				{
					temp = allGates.get(i).getOutgoingGates();
					
					for(int j = 0; j < temp.size(); j++) 
					{		
						for(int k = 0; k < allGates.size(); k++)
						{
							if(temp.get(j) == allGates.get(k))	
							{
								drawWire = new ConnectingGates (allGates.get(i), temp.get(j));
								drawWires wire = new drawWires(drawWire.x1 + 20, drawWire.y1 + 25, drawWire.x2 + 20, drawWire.y2 + 25);
								wires.add(wire);
							}
						}
					}	
				}
			}
			
			click = new MouseMovements(objects);
			
			if(drawWire.isLineReady())
			{
				drawWires wire = new drawWires(drawWire.x1 + 20, drawWire.y1 + 25, drawWire.x2 + 20, drawWire.y2 + 25);
				
				wires.add(wire);
				g.setColor(wire.getColor());
				
				for(int i = 0; i < wires.size(); i++)
				{
					g.drawLine(wires.get(i).getX1(), wires.get(i).getY1(), wires.get(i).getX2(), wires.get(i).getY2());
				}
				
				drawWire = new ConnectingGates (allGates, objects);
			}
			
			if(menuButtons.isInserting())
			{
				if(offsetY > 500)
				{
					offsetY = 0;
					offsetX +=100;
				}
				if(offsetX > 500)
				{
					offsetX = 0;
				}
				// Adding JLabel to object arraylist
				objects.add(InsertGate.getGate());
				
				// Adding JLable to GatePanel
				add(objects.get(objects.size() -1));
				
				// Setting bounds on JLabel added
				objects.get(objects.size() - 1).setBounds(300 + offsetX, 300 + offsetY, 100, 80);
				
				offsetY +=100;
			
				// Will be adding the type to the Gates List
				allGates.add(InsertGate.insertNewGate());

				menuButtons.inserting =false;
			}
			
			move = new GateMovement(objects);
	   }
	
	 public class DrawPanel implements ActionListener{
		 @Override
		 public void actionPerformed(ActionEvent e) 
		 {
			 repaint(); 
		 }
	   }
	 
	 public void ReloadFile(String path)
	 {
		 //System.out.println("inside ReloadFile");
		 try {
				ReadFile readFile = new ReadFile();
				readFile.readtheFile(path, allGates);
			} 
		catch (FileNotFoundException e) {	
				e.printStackTrace();
			}

		wires.removeAll(wires);
		
	 	int depth = 0;
		int height = 0;
		GateHeight gh = new GateHeight();
		gh.calculateGateHeight(allGates);
		
		// Fill array "icons" with gates read from fill and add them to the panel
		for(int i = 0; i < allGates.size(); i++)
		{
			depth= allGates.get(i).getDepth() * 110;
			height= allGates.get(i).getHeight() * 150;
			
			//System.out.println("height "+height);
			
			// Adding JLabels to object arraylist
			objects.add(allGates.get(i).execute());
			
			// Setting bounds for JLabel added
			objects.get(i).setBounds(depth, height, 100, 80);
			
			// Adding the JLabel to GatePanel
			add(objects.get(i));			
		}
		
		// Loop through allGates to find any connections
		for(int i = 0; i < allGates.size(); i++)
		{
			if(allGates.get(i).getOutgoingGateSize() > 0)
			{
				temp = allGates.get(i).getOutgoingGates();
				
				for(int j = 0; j < temp.size(); j++) 
				{
					for(int k = 0; k < allGates.size(); k++)
					{
						if(temp.get(j) == allGates.get(k))	
						{
							drawWire = new ConnectingGates (allGates.get(i), temp.get(j));
							drawWires wire = new drawWires(drawWire.x1+20, drawWire.y1+25, drawWire.x2+20, drawWire.y2+25);
							wires.add(wire);
						}
					}
				}	
			}
		}
		//drawWire = new ConnectingGates (allGates, objects);
		setLayout(null); 
	 }
	 
	 public void updating()
	 {
		 GateHeight gh = new GateHeight();
		 gh.calculateGateHeight(allGates);
		
		// Fill array "icons" with gates read from fill and add them to the panel
			for(int i = 0; i < allGates.size(); i++)
			{
				int depth= allGates.get(i).getDepth() * 110;
				int height= allGates.get(i).getHeight() * 150;
				
				// Adding JLabels to object arraylist
				objects.add(allGates.get(i).execute());
				
				// Setting bounds for JLabel added
				objects.get(i).setBounds(depth, height, 100, 80);
				
				// Adding the JLabel to GatePanel
				add(objects.get(i));			
			}
			move = new GateMovement(objects);
	 }
}
