import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * This inserts the individual gates based on what's selected in the menu.
 */

public class InsertGate {
	public static JLabel gate = null;
	static String gateType;
	public static Input inGate = null;
	
	static Gate newGate = null;
	public InsertGate(String gate_type) {
		
		gateType = gate_type;
	}
	
	public void setGate()
	{
		System.out.println(gateType);
		if(gateType == "OR")
		{
			gate = new JLabel(new ImageIcon("./Pictures/ORDeasserted.png"));
			newGate = new Or(GatePanel.allGates);
		}
		if(gateType == "XOR")
		{
			gate = new JLabel(new ImageIcon("./Pictures/XORDeasserted.png"));
			newGate= new Xor(GatePanel.allGates);
		}
		if(gateType == "AND")
		{
			gate = new JLabel(new ImageIcon("./Pictures/AndDeasserted.png"));
			newGate = new AndGate(GatePanel.allGates);
		}
		if(gateType == "NAND")
		{
			gate = new JLabel(new ImageIcon("./Pictures/NANDDeasserted.png"));
			newGate = new Nand(GatePanel.allGates);
		}
		if(gateType == "OUTPUT")
		{
			gate = new JLabel(new ImageIcon("./Pictures/OutputDeasserted.png"));
			newGate = new Output(GatePanel.allGates);
		}
		if(gateType == "INPUT")
		{
			gate = new JLabel(new ImageIcon("./Pictures/InputDeasserted.png"));
			newGate = new Input(GatePanel.allGates);
		}
		if(gateType == "NOT")
		{
			gate = new JLabel(new ImageIcon("./Pictures/NotDeasserted.png"));
			newGate = new Not(GatePanel.allGates);
		}
	}
	
	public static JLabel getGate()
	{
		return gate;
	}
	
	public static Gate insertNewGate()
	{
		newGate.setGateValue(newGate.calculateGateValue(GatePanel.allGates));
		System.out.println("Gate Inserted : " + newGate.getGateName());
		return newGate;
	}

}

