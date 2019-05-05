import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Xor extends Gate
/*
 * Xor inherits from Gate.  It has two incoming gates and is asserted only if the two incoming gates have different assertions, otherwise it is deasserted.
 */
{
Xor(ArrayList<Gate> gate)
{
	name = "xor";
	hasGateA = true;
	hasGateB = true;
}

Xor(Gate prevGateA, Gate prevGateB, ArrayList<Gate> gate)
{
	if(prevGateA.getAssertionValue() == prevGateB.getAssertionValue())
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	depth = calculateDepth(prevGateA.getDepth(), prevGateB.getDepth());
	previousGateA = prevGateA;
	previousGateB = prevGateB;
	name = "xor";
	hasGateA = true;
	hasGateB = true;
}

@Override
JLabel execute()
{
	JLabel xOr = null;
	if(this.getPreviousGateA().getAssertionValue() == this.getPreviousGateB().getAssertionValue())
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	if (assertionValue == 0)
	{
		xOr = new JLabel(new ImageIcon("./Pictures/XORDeasserted.png"));
	}
	else
	{
		xOr = new JLabel(new ImageIcon("./Pictures/XORAsserted.png"));
	    
	}
	return xOr;
}

}
