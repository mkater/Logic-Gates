import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * This class inherits from Gate for the input.  It has its depth permanently set to 0 as all inputs should be aligned on the left.  It has an additional function activateTheInput which is
 * used for Input Assertion within the GUI to switch back and forth from 0 to 1.
 */
public class Input extends Gate
{

Input(ArrayList<Gate> gate)
{
	name = "input";
	depth = 0;
	hasGateA = false;
	hasGateB = false;
}

Input(int isAsserted, ArrayList<Gate> gate)
{
	assertionValue = isAsserted;
	name = "input";
	depth = 0;
	hasGateA = false;
	hasGateB = false;
}

@Override
JLabel execute()
{
	JLabel input = null;
	if (assertionValue == 0)
	{
		
		input = new JLabel(new ImageIcon("./Pictures/InputDeasserted.png"));
	}
	else
	{
		
		input = new JLabel(new ImageIcon("./Pictures/InputAsserted.png"));
	}
	return input;
}

@Override
void activateTheInput()
{
	if(assertionValue == 0)
	{
		assertionValue = 1;
	}
	else if (assertionValue == 1)
	{
		assertionValue = 0;
	}
	
}

}
