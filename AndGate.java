import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/*
 * AndGate class overrides execute from Gate and has two previous Gates going into it.  It will be 1 or asserted only if both previous gates are asserted, otherwise it will be 0.
 */
public class AndGate extends Gate 
{
AndGate(ArrayList<Gate> gate)
{
	name = "and";
	hasGateA = true;
	hasGateB = true;
}

AndGate(Gate prevA, Gate prevB, ArrayList<Gate> gate)
{
	if(prevA.getAssertionValue() == prevB.getAssertionValue() && (prevB.getAssertionValue() != 0))
	{
		assertionValue = 1;
	}
	else
	{
		assertionValue = 0;
	}
	
	name = "and";
	depth = calculateDepth(prevA.getDepth(), prevB.getDepth());
	previousGateA = prevA;
	previousGateB = prevB;
	hasGateA = true;
	hasGateB = true;
	
}

@Override
JLabel execute()
{
	JLabel andGate = null;
	if(this.getPreviousGateA().getAssertionValue() == this.getPreviousGateB().getAssertionValue() && (this.getPreviousGateB().getAssertionValue() != 0))
	{
		assertionValue = 1;
	}
	else
	{
		assertionValue = 0;
	}
	
	if (assertionValue == 0)
	{
		andGate = new JLabel(new ImageIcon("./Pictures/AndDeasserted.png"));
	}
	else
	{
		andGate = new JLabel(new ImageIcon("./Pictures/AndAsserted.png"));
	}
	return andGate;
}

}
