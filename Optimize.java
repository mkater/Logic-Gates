import java.util.ArrayList;
import java.util.Iterator;


public class Optimize 
{
	private int optimizeDead, optimizeDuplicate, optimizeAlways;	//these ints keep track/count if each of their respective functions triggered a replacement
	
	
	
	/*
	 * RemoveDeadGates iterates through the ArrayList of Gates and finds Gates that have no outgoing gates and are NOT Output Gates as it never does.
	 * This means that the Gate is a Dead gate as it is not going through to an output.  It will keep looping through this iteration while there are dead Gates
	 * Which means if there is a chain of dead gates in a row they will be removed iteration by iteration.
	 */
void removeDeadGates(ArrayList<Gate>allGates)
{
	int count = 0;
	optimizeDead = 0;
	do {
	Iterator<Gate> itr = allGates.iterator();
	count = 0;	//verify placement of this is ok.
	while(itr.hasNext())
	{
		Gate x = (Gate)itr.next(); 
		if(x.noOutgoingGate() && !"output".equals(x.getGateName()))
		{
			if(x.hasPreviousGateA() && x.hasPreviousGateB() == true)
			{
				if(x.getPreviousGateA()!= null && x.getPreviousGateB()!=null)
				{
				x.getPreviousGateA().removeOutgoingGate(x);
				x.getPreviousGateB().removeOutgoingGate(x);
				}
			}
			else if(x.hasPreviousGateA() == true)
			{
				if(x.getPreviousGateA()!=null)
				{
				x.getPreviousGateA().removeOutgoingGate(x);
				}
			}
			System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
			System.out.println("outgoing gate count: " + x.getOutgoingGates().size());
			itr.remove();
			optimizeDead++;
			count++;
		}
	}
	
	}while(count !=0);
}

int getOptimzeDead()
{
	return optimizeDead;
}

int getOptimizeAlways()
{
	return optimizeAlways;
}

int getOptimizeDuplicate()
{
	return optimizeDuplicate;
}

/*
 * Remove Always iterates through the ArrayList of Gates and searches for either always true or false gates and removes them.  For true wire, we need only search for Or Gates and Not Gates.
 * Or Gates are always true with a true wire and Not Gates are always false.  We remove the gate and place its outgoing gates in the previous Gates outgoing Gates.  For the False wire,
 * Not and Nand will always be true, and And will always be false, which is what we searched for.  Replacements similar to the first part are done, where the Gate is removed, its children
 * placed as the output of its parent now.
 * 
 */

void removeAlwaysGates(ArrayList<Gate>allGates)
{
	int count = 0;
	optimizeAlways = 0;
	do
	{
		Iterator<Gate> itr = allGates.iterator();
		count = 0;
		while(itr.hasNext())
		{
			Gate x = (Gate)itr.next();
			if((x.hasPreviousGateA() && x.getPreviousGateA().getGateName().equals("true")) && (x.getGateName().equals("or") || x.getGateName().equals("not")))
				{
					for(int i = 0; i < x.getOutgoingGateSize(); i++)
					{
						if(x.getOutgoingGates().get(i).getPreviousGateA() == x)
						{
							x.getOutgoingGates().get(i).setPreviousGateA(x.getPreviousGateA());	//setting prevGateA to be the true gate now.
							x.getPreviousGateA().addGate(x.getOutgoingGates().get(i));	//adding the gate to prevGAteA (the true gate) ArrayList
							x.getPreviousGateA().removeOutgoingGate(x);		//removing Gate x from true gates outgoing ArrayList
						}
						else if(x.getOutgoingGates().get(i).getPreviousGateB() == x)
						{
							x.getOutgoingGates().get(i).setPreviousGateB(x.getPreviousGateA());	//doing same if it was Gate B
							x.getPreviousGateA().addGate(x.getOutgoingGates().get(i));
							x.getPreviousGateA().removeOutgoingGate(x);
						}
					}
					System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
					itr.remove();
					count++;
					optimizeAlways++;
				}//end of checking true gate  Or and Not for PreviousGateA
			else if((x.hasPreviousGateB() && x.getPreviousGateB().getGateName().equals("true")) && x.getGateName().equals("or"))	//same check if true gate is previous.
			{
				for(int i = 0; i < x.getOutgoingGateSize(); i++)
				{
					if(x.getOutgoingGates().get(i).getPreviousGateA() == x)
					{
						x.getOutgoingGates().get(i).setPreviousGateA(x.getPreviousGateB());	//setting prevGateA to be the true gate now.
						x.getPreviousGateB().addGate(x.getOutgoingGates().get(i));	//adding the gate to prevGAteA (the true gate) ArrayList
						x.getPreviousGateA().removeOutgoingGate(x);		//removing Gate x from true gates outgoing ArrayList
						x.getPreviousGateB().removeOutgoingGate(x);
					}
					else if(x.getOutgoingGates().get(i).getPreviousGateB() == x)
					{
						x.getOutgoingGates().get(i).setPreviousGateB(x.getPreviousGateB());	//doing same if it was Gate B
						x.getPreviousGateB().addGate(x.getOutgoingGates().get(i));
						x.getPreviousGateB().removeOutgoingGate(x);
						x.getPreviousGateA().removeOutgoingGate(x);
					}
				}
				System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
				itr.remove();
				count++;
				optimizeAlways++;
			}//end of checking true gate  Or for PreviousGateB
			else if((x.hasPreviousGateA() && x.getPreviousGateA().getGateName().equals("false")) && (x.getGateName().equals("not") || 
					x.getGateName().equals("and") || x.getGateName().equals("nand")))
			{
				for(int i = 0; i < x.getOutgoingGateSize(); i++)
				{
					if(x.getOutgoingGates().get(i).getPreviousGateA() == x)
					{
						x.getOutgoingGates().get(i).setPreviousGateA(x.getPreviousGateA());	//setting prevGateA to be the true gate now.
						x.getPreviousGateA().addGate(x.getOutgoingGates().get(i));	//adding the gate to prevGAteA (the true gate) ArrayList
						x.getPreviousGateA().removeOutgoingGate(x);		//removing Gate x from true gates outgoing ArrayList
						if(x.hasPreviousGateB())
						{
						x.getPreviousGateB().removeOutgoingGate(x);
						}
					}
					else if(x.getOutgoingGates().get(i).getPreviousGateB() == x)
					{
						x.getOutgoingGates().get(i).setPreviousGateB(x.getPreviousGateA());	//doing same if it was Gate B
						x.getPreviousGateA().addGate(x.getOutgoingGates().get(i));
						if(x.hasPreviousGateB())
						{
						x.getPreviousGateB().removeOutgoingGate(x);
						}
						x.getPreviousGateA().removeOutgoingGate(x);
					}
				}
				System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
				itr.remove();
				count++;
				optimizeAlways++;
			}//end of else if checking previousGate A and false wire
			else if((x.hasPreviousGateB() && x.getPreviousGateB().getGateName().equals("false")) &&
					(x.getGateName().equals("and") || x.getGateName().equals("nand")))
			{
				for(int i = 0; i < x.getOutgoingGateSize(); i++)
				{
					if(x.getOutgoingGates().get(i).getPreviousGateA() == x)
					{
						x.getOutgoingGates().get(i).setPreviousGateA(x.getPreviousGateB());	//setting prevGateA to be the true gate now.
						x.getPreviousGateB().addGate(x.getOutgoingGates().get(i));	//adding the gate to prevGAteA (the true gate) ArrayList
						x.getPreviousGateA().removeOutgoingGate(x);		//removing Gate x from true gates outgoing ArrayList
						x.getPreviousGateB().removeOutgoingGate(x);
					}
					else if(x.getOutgoingGates().get(i).getPreviousGateB() == x)
					{
						x.getOutgoingGates().get(i).setPreviousGateB(x.getPreviousGateB());	//doing same if it was Gate B
						x.getPreviousGateB().addGate(x.getOutgoingGates().get(i));
						x.getPreviousGateB().removeOutgoingGate(x);
						x.getPreviousGateA().removeOutgoingGate(x);
					}
				}
				System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
				itr.remove();
				count++;
				optimizeAlways++;
			}
		}//while itr iterating
	}while(count!=0);
}
	

/*
 * Remove Duplicates searches for the gates that are not the input or output, then removes them if two gates with the same gateType (using name to verify) have the exact same parents.
 * It iterates through the ArrayList then removes the first one matching the criteria.  Removes it from the outgoing arraylist of its parents, and adds all of its children to the outgoing
 * arraylist of the gate that matches it.
 */
void removeDuplicates(ArrayList<Gate>allGates)
{
	optimizeDuplicate = 0;
	int count = 0;
	do
	{
		Iterator<Gate> itr = allGates.iterator();
		count = 0;
		while(itr.hasNext())
		{
			Gate x = (Gate)itr.next(); 
			if(x.getGateName().equals("not"))	//if it is not gate, only one with 1 incoming Gate specifically
			{
				for(int i = 0; i < x.getPreviousGateA().getOutgoingGateSize(); i++)	//looping through x's previousA's outgoing ArrayList of gates, which includes x
				{
					if((x.getPreviousGateA().getOutgoingGates().get(i).getGateName().equals(x.getGateName()) && (x.getPreviousGateA().getOutgoingGates().get(i) != x)))
						//Above if statement checks if the ith element of previousGateA's outgoing ArrayList has the same name as x, but is NOT the same object.
					{
						Gate temp = x.getPreviousGateA().getOutgoingGates().get(i);	//We have a match, this is the Gate we're keeping
						for(int k = 0; k < x.getOutgoingGateSize(); k++)	//looping through x's outgoing arrayList
						{
							if(x.getOutgoingGates().get(k).getPreviousGateA() == x)
							{
								x.getOutgoingGates().get(k).setPreviousGateA(temp);	//setting it to the temp Gate instead of x
								temp.addGate(x.getOutgoingGates().get(k));			//adding the outgoing gate to temp's arraylist
								x.getPreviousGateA().removeOutgoingGate(x);			//removing outgoing gate from x
							}
							else if(x.getOutgoingGates().get(k).getPreviousGateB() == x)
							{
								x.getOutgoingGates().get(k).setPreviousGateB(temp);
								temp.addGate(x.getOutgoingGates().get(k));
								x.getPreviousGateA().removeOutgoingGate(x);
							}
						}
						System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
						itr.remove(); 
						count++;
						optimizeDuplicate++;
						break;
					}
				}
			}
			else if(x.getGateName().equals("and") || x.getGateName().equals("or") || x.getGateName().equals("nand") || x.getGateName().equals("xor"))		//now checking for the And Or Nand Xor.
			{
				for(int i = 0; i < allGates.size(); i++)
				{
					if((x.getPreviousGateA() == allGates.get(i).getPreviousGateA()) && (x.getPreviousGateB() == allGates.get(i).getPreviousGateB()) && 
							(allGates.get(i).getGateName().equals(x.getGateName())) && (allGates.get(i) != x))
						//Loops through all of the gates and checks if they share the exact parents with x, and have the same gate name, but are not the same gate.
					{
						Gate temp = allGates.get(i);	//the one we're keeping as we're removing x.
						for(int j = 0; j < x.getOutgoingGates().size(); j++)
						{
							if(x.getOutgoingGates().get(j).getPreviousGateA() == x)
							{
								x.getOutgoingGates().get(j).setPreviousGateA(temp);	//setting it to the temp Gate instead of x
								temp.addGate(x.getOutgoingGates().get(j));			//adding the outgoing gate to temp's arraylist
								x.getPreviousGateA().removeOutgoingGate(x);			//removing outgoing gate from x
								x.getPreviousGateB().removeOutgoingGate(x);
							}
							else if(x.getOutgoingGates().get(j).getPreviousGateB() == x)
							{
								x.getOutgoingGates().get(j).setPreviousGateB(temp);
								temp.addGate(x.getOutgoingGates().get(j));
								x.getPreviousGateA().removeOutgoingGate(x);
								x.getPreviousGateB().removeOutgoingGate(x);
							}
						}
						System.out.println("Removing Gate: " + x.getGateName() + " " + x.getGateValue());
						itr.remove();
						count++;
						optimizeDuplicate++;
						break;
					}
				
				}
			}//end of looping through prevGate B
		
		}//end of while itr has next
	}while(count!=0);
}
}

