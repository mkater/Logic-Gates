
/*
 * TrueGate inherits from Gate.  It is always asserted.
 */

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TrueGate extends Gate
{
	
	TrueGate()
	{
		assertionValue = 1;
		name = "true";
		hasGateA = false;
		hasGateB = false;
	}
	
	@Override
	JLabel execute()
	{
		JLabel trueGate = new JLabel(new ImageIcon("./Pictures/trueGate.png"));
		return trueGate;
	}
	
}
