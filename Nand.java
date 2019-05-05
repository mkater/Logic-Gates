import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * Nand inherits from Gate.  It is the opposite of AND and is asserted if either of its incoming assertions is 0.
 */
public class Nand extends Gate
{
Nand(ArrayList<Gate> gate)
{
	name = "nand";
	hasGateA = true;
	hasGateB = true;
}

Nand(Gate prevA, Gate prevB, ArrayList<Gate> gate)
{
	if(prevA.getAssertionValue() == prevB.getAssertionValue() && (prevB.getAssertionValue() ==1))
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	depth = calculateDepth(prevA.getDepth(), prevB.getDepth());
	previousGateA = prevA;
	previousGateB = prevB;
	name = "nand";
	hasGateA = true;
	hasGateB = true;
}
@Override
JLabel execute()
{
	JLabel nand = null;
	if(this.getPreviousGateA().getAssertionValue() == this.getPreviousGateB().getAssertionValue() && (this.getPreviousGateB().getAssertionValue() ==1))
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	if (assertionValue == 0)
	{
		nand = new JLabel(new ImageIcon("./Pictures/NANDDeasserted.png"));
	}
	else
	{
		nand = new JLabel(new ImageIcon("./Pictures/NANDAsserted.png"));
	}
	return nand;
}

}
