import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * Or inherits from Gate and is asserted if either previous value is asserted.
 */
public class Or extends Gate
{

Or(ArrayList<Gate> gate)
{
	name = "or";
	hasGateA = true;
	hasGateB = true;
}

Or(Gate previousA, Gate previousB, ArrayList<Gate> gate)
{
	if(previousA.getAssertionValue() == previousB.getAssertionValue() && (previousA.getAssertionValue() == 0))
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	
	depth = calculateDepth(previousA.getDepth(), previousB.getDepth());
	previousGateA = previousA;
	previousGateB = previousB;
	name = "or";
	hasGateA = true;
	hasGateB = true;
}

@Override
JLabel execute()
{
	JLabel orGate = null;
	if(this.getPreviousGateA().getAssertionValue() == this.getPreviousGateB().getAssertionValue() && (this.getPreviousGateB().getAssertionValue() == 0))
	{
		assertionValue = 0;
	}
	else
	{
		assertionValue = 1;
	}
	if (assertionValue == 0)
	{
		orGate = new JLabel(new ImageIcon("./Pictures/ORDeasserted.png"));
	}
	else
	{
		orGate = new JLabel(new ImageIcon("./Pictures/ORAsserted.png"));
	}
	return orGate;
}
}
