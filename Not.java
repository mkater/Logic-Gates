import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Not extends Gate
{
/*
 * Not inherits from Gate and has only one incoming gate.  It is asserted if its incoming value is 0, else its value is 0.  The only other difference is its depth is calculated just based
 * off of its one previous gate.
 */
Not(ArrayList<Gate> gate)
{
	name = "not";
	hasGateA = true;
	hasGateB = false;
}

Not(Gate previousA, ArrayList<Gate> gate)
{
	if(previousA.getAssertionValue() == 0)
	{
		assertionValue = 1;
	}
	else
	{
		assertionValue = 0;
	}
	
	depth = calculateDepth(previousA);
	previousGateA = previousA;
	name = "not";
	hasGateA = true;
	hasGateB = false;
}

int calculateDepth(Gate previous)
{
	return previous.getDepth() + 1;
}

@Override
JLabel execute()
{
	JLabel notGate = null;
	if(this.getPreviousGateA().getAssertionValue() == 0)
	{
		assertionValue = 1;
	}
	else
	{
		assertionValue = 0;
	}
	
	if (assertionValue == 0)
	{
		notGate = new JLabel(new ImageIcon("./Pictures/NotDeasserted.png"));
	}
	else
	{
		notGate = new JLabel(new ImageIcon("./Pictures/NotAsserted.png")); 
	}
	return notGate;
}
}
