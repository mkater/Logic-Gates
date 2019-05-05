import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * False wire inherits from Gate class and in constructor its assertion is always set to false.
 */
public class FalseGate extends Gate
{
	
	FalseGate()
	{
		assertionValue = 0;
		name = "false";
	}
	@Override
	JLabel execute()
	{
		JLabel falseGate = new JLabel(new ImageIcon("./Pictures/falseGate.png"));
		return falseGate;
	}
	
}
