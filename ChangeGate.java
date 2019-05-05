/*
 * Function used to swap out gates for changing in edit mode.
 */
public class ChangeGate 
{

	ChangeGate(Gate current, Gate previous, Gate newGate)
	{
		previous.removeOutgoingGate(current);
		if(previous.getGateValue() == current.getPreviousGateA().getGateValue())
		{
			current.setPreviousGateA(newGate);
		}
		else
		{
			current.setPreviousGateB(newGate);
		}
		newGate.addGate(current);
		
	}
}
