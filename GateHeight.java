import java.util.ArrayList;
/*
 * This class uses a nested for loop to loop through the arrayList of gates with an initial height of -1.  If the inner loop finds a gate with the same depth as it, it adds 1 to the height of
 * the current gate.  This will keep happening as it finds matches so no two gates will have the same depth AND height (ie same location).
 */
public class GateHeight 
{

void calculateGateHeight(ArrayList<Gate>gateList)
{
	for(int i = 0; i < gateList.size(); i++)
	{
		int maxHeight = -1;
		for(int j = 0; j < gateList.size(); j++)
		{
		if(gateList.get(i).getDepth() == gateList.get(j).getDepth())
			{
			
				if(maxHeight < gateList.get(j).getHeight())
				{
					maxHeight = gateList.get(j).getHeight();
				}
			}
		}
		gateList.get(i).setGateHeight(maxHeight + 1);
	}
}
}
