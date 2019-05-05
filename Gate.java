import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
/*
 * Superclass for all of the Gate objects that inherit from it.  Has protected data members that the other classes can use and various setters and getters for those members.
 * It can calculate depth and height of the gates, add or remove gates from the arrayList.
 */
public class Gate
{
	protected int gateValue, assertionValue, depth=0, height = -1;
	protected Gate previousGateA, previousGateB;
	protected ArrayList<Gate> outgoingGates = new ArrayList<Gate>();
	protected String name;
	protected boolean hasGateA, hasGateB;
	
	ArrayList<Gate> getOutgoingGates()
	{
		return outgoingGates;
	}
	boolean hasPreviousGateA()
	{
		return hasGateA;
	}
	
	void setAssertionValue(int theAssertion)
	{
		assertionValue = theAssertion;
	}
	
	boolean hasPreviousGateB()
	{
		return hasGateB;
	}
		
	int getHeight()
	{
		return height;
	}
	
	int getOutgoingGateSize()
	{
		return outgoingGates.size();
	}
	
	JLabel execute() 
	{
		System.out.println("This is main gate, if called probably error\n");
		return null;
	}
	
	int calculateDepth(int depthPreviousA, int depthPreviousB)
	{
		if(depthPreviousB > depthPreviousA)
		{
			depth = depthPreviousB +1;
			return depthPreviousB += 1;
		}
		else
		{
			depth = depthPreviousA + 1;
			return depthPreviousA += 1;
		}
	}
	
	int calculateGateValue(ArrayList<Gate> gate)
	{
		int max = -1;
		for(int i = 0; i < gate.size(); i++)
		{
			if(max < gate.get(i).getGateValue())
			{
				max = gate.get(i).getGateValue();
			}
		}
		return (max+1);
	}
	String getGateName()
	{
		return name;	
	}
	
	Gate getPreviousGateA()
	{
		return previousGateA;
	}
	
	Gate getPreviousGateB()
	{
		return previousGateB;
	}
	
	
	void setGateHeight(int gHeight)
	{
		height = gHeight;
	}
	
	void setPreviousGateA(Gate prev)
	{
		previousGateA = prev;
	}
	
	void setPreviousGateB(Gate prev)
	{
		previousGateB = prev;
	}
	
	void setGateValue(int value)
	{
		gateValue = value;
	}
	
	int getGateValue()
	{
		return gateValue;
	}
	
	int getAssertionValue()
	{
		return assertionValue;
	}
	
	int getDepth()
	{
		return depth;
	}
	
	void addGate(Gate out)
	{
		outgoingGates.add(out);

	}
	
	boolean noOutgoingGate()
	{
		if(outgoingGates.isEmpty())
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	
	void removeOutgoingGate(Gate gate)
	{
		Iterator<Gate> itr = outgoingGates.iterator();
		
		while(itr.hasNext())
		{
			
			Gate x = (Gate)itr.next(); 
			if(x.getGateValue() == gate.getGateValue())
			{
				itr.remove();
			}
		}
	}
	
	void activateTheInput()
	{
		System.out.println("Shouldn't print this activateTheInput()");
	}
}
