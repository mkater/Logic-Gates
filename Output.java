import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * Output inherits from Gate.  As all outputs are aligned on the end, its depth is calculated differently.  We need to loop through the ArrayList of gates and find the one NOT named
 * output with the greatest depth and add 1 to that to find the depth for output.  Output only receives one input, and its assertion is the assertion of the previous input.
 */
public class Output extends Gate
{
	
int calculateDepth(ArrayList<Gate> gate)
{
	int largest = 0;
	for(int i = 0; i < gate.size(); i++)
	{
		if(!"output".equals(gate.get(i).getGateName()))
		{
		if(gate.get(i).getDepth() > largest)
		{
			largest = gate.get(i).getDepth();
		}
		}
	}
	return largest += 1;
}

Output(ArrayList<Gate> gate)
{
	name = "output";
	hasGateA = true;
	hasGateB = false;
}

Output(Gate previous, ArrayList<Gate> gate)
{
	if(previous.getAssertionValue() == 0)
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	
	depth = calculateDepth(gate);
	previousGateA = previous;
	name = "output";
	previousGateB = null;
	hasGateA = true;
	hasGateB = false;
}

@Override
JLabel execute()
{
	JLabel out = null;
	if(this.getPreviousGateA().getAssertionValue() == 0)
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	if(assertionValue == 0)
	{
		out = new JLabel(new ImageIcon("./Pictures/OutputDeasserted.png"));
	}
	else
	{
		out = new JLabel(new ImageIcon("./Pictures/OutputAsserted.png"));
	    
	}
	
	return out;
}

}
