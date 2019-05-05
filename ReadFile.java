import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * This class reads the file from text or binary and converts it into the gates that get displayed.  For text mode it reads in the string and parses that into an int for the Gate ID, then a string
 * name.  Based on the string name, it knows whether the gate is complete or if there is more information to read.  If more then it reads the first previousGate ID, finds the matching gate in the
 *  arrayList of gates and creates a gate if all required info is present.  If not, it reads in the last value, which is previousGate 2, and uses that to create the rest of the gates.  It keeps reading 
 *  until the file is complete.  Binary is similar to text except we are assigning binary byte IDs to each of the gates.  Two bytes is used for each of the ints as they can be as large as 400.
 */
public class ReadFile
{
	void readtheFile(String fileName, ArrayList<Gate> gate) throws FileNotFoundException
	{
		gate.clear();
		Scanner fScn = new Scanner(new File(fileName));	//creates Scanner to read the file
	    String data;
	    String newFile;
	    
	   
	    if(fileName.contains("bin")) 
	    {
	    	BinaryToString(fileName);
	    	fScn = new Scanner(new File("noBinary.txt"));	    
	    }
	   
	    while( fScn.hasNextLine() )	//while its scanning something
	    {
	    	data = fScn.nextLine();	//data is the line of the file
	
	         String[] token = data.split(" ");	//creates a string area of the token. split is a regex that splits the array by spaces for example "and 1 2" will be a string array of "and" "1" and "2".
	         int gateID = Integer.parseInt(token[0]);//token[0] the first part of array will be assigned to int gateID
	         String gateName= token[1];//string name is in token[1]
	         
	         if(gateName.equals("input"))//if that name is input
	         {
	      	   Input inp1 = new Input(gate);//create new Input
	      	   inp1.setGateValue(gateID);//assign its value the gateID
	      	   gate.add(inp1);//add to the ArrayList of Gates
	         }
	         else if(gateName.equals("true"))
	         {
	        	TrueGate tg = new TrueGate(); 
	        	tg.setGateValue(gateID);
	        	gate.add(tg);
	         }
	         else if(gateName.equals("false"))
	         {
	        	 FalseGate fg = new FalseGate();
	        	 fg.setGateValue(gateID);
	        	 gate.add(fg);
	         }
	         if(token.length > 2)	//checks if the array is longer than 2, will be true for everything but input which already has been dealt with.
	         {
		         int prevID1 = Integer.parseInt(token[2]);//Previous gate ID is assigned the value of array token[2].
		         Gate previousGate1 = null;//creating the placeholder gate
		         
		         for(int i = 0; i <gate.size(); i++)//looping through ArrayList of gate
		         {
		      	   if(prevID1 == gate.get(i).getGateValue())	//if ID = the ID of the Gate
		      	   {
		      		   previousGate1 = gate.get(i); //Previous gate gets assigned the value of the matching gate of ArrayList
		      	   }
		         }
		         
		         if(gateName.equals("output"))//if string = output
		         {
		      	   Output out = new Output(previousGate1, gate); //same as input for creating new Gate
		      	   out.setGateValue(gateID);
		      	   gate.add(out);
		      	   previousGate1.addGate(out);
		         }
		         
		         else if(gateName.equals("not"))	//same as output.  Note these two gates take only one previous gate value.
		         {
		      	   Not not = new Not(previousGate1, gate);
		      	   not.setGateValue(gateID);
		      	   gate.add(not);
		      	   previousGate1.addGate(not);
		         }
		         
		         if(token.length > 3)	//now we check if array is 4 which is when there are two previous gate values ie the rest of the gates.
		         {
	         
			         int prevID2 = Integer.parseInt(token[3]);	//previousgateid 2 is assigned the value of token[3]
			         Gate previousGate2 = null;//creating Gate to hold matching Gate value.
			         
			         for(int i = 0; i <gate.size(); i++)
			         {
			      	   if(prevID2 == gate.get(i).getGateValue())//same as for previousGate 1
			      	   {
			      		   previousGate2 = gate.get(i);
			      	   }
			         }
			         if(gateName.equals("and"))//now we check the rest of the gates
			         {
			      	   AndGate and = new AndGate(previousGate1, previousGate2, gate);
			      	   and.setGateValue(gateID);
			      	   gate.add(and);
			      	  previousGate1.addGate(and);
			      	   previousGate2.addGate(and);
			         }
			         else if(gateName.equals("xor"))
			         {
			      	   Xor xor = new Xor(previousGate1, previousGate2, gate);
			      	   xor.setGateValue(gateID);
			      	   gate.add(xor);
			      	   previousGate1.addGate(xor);
			      	   previousGate2.addGate(xor);
			         }
			         else if(gateName.equals("nand"))
			         {
			      	   Nand nand = new Nand(previousGate1, previousGate2, gate);
			      	   nand.setGateValue(gateID);
			      	   gate.add(nand);
			      	  previousGate1.addGate(nand);
			      	  previousGate2.addGate(nand);
			         }
			         else
			         {
			      	   Or or = new Or(previousGate1, previousGate2, gate);
			      	   or.setGateValue(gateID);
			      	   gate.add(or);
			      	  previousGate1.addGate(or);
			      	  previousGate2.addGate(or);
			         }
		         }
	         }
	    }//end of fileReading
	    fScn.close();	//close the file	
	}
	public void BinaryToString(String fileName) 
	{
		try {
			Scanner fScn = new Scanner(new File(fileName));		
			FileOutputStream file = new FileOutputStream("noBinary.txt", false);	
			PrintWriter pw = new PrintWriter(file);
			
		    String data, gateName = " ", contents = "", gateIDString, prevGate1String = "", prevGate2String = ""; 
		    int gateID, prevGate1, prevGate2;
		    
		    while( fScn.hasNextLine() )
		    {
		    	data = fScn.nextLine();
		    	
		    	String[] token = data.split(" ");
		    	gateID = Integer.parseInt(token[0], 2);
		    	gateIDString = Integer.toString(gateID);
		    		    	
		    	if(token[1].contains("00000001"))
		    	{
		    		System.out.println("token: " + token[1]);
		    		gateName = "input";
		    	}
		    	else if(token[1].contains("00000010"))
		    	{
		    		gateName = "output";
		    	}
		    	else if(token[1].contains("00000011"))
		    	{
		    		gateName = "and";
		    	}
		    	else if(token[1].contains("00000100"))
		    	{
		    		gateName = "nand";
		    	}
		    	else if(token[1].contains("00000101"))
		    	{
		    		gateName = "or";
		    	}
		    	else if(token[1].contains("00000110"))
		    	{
		    		gateName = "xor";
		    	}
		    	else if(token[1].contains("00000111"))
		    	{
		    		gateName = "not";
		    	}
		    	else if(token[1].contains("000001000"))
		    	{
		    		gateName = "true";
		    	}
		    	else if(token[1].contains("000001001"))
		    	{
		    		gateName = "false";
		    	}
		    	
		    	prevGate1 = Integer.parseInt(token[2], 2);
		    	prevGate2 = Integer.parseInt(token[3], 2);
		    	
		    	if(prevGate1 == 0)
		    	{
		    		prevGate1String = "";
		    	}
		    	if(prevGate2 == 0)
		    	{
		    		prevGate2String = "";
		    	}
		    	else
		    	{
		    		prevGate1String = Integer.toString(prevGate1);
		    		prevGate2String = Integer.toString(prevGate2);
		    	}
		    	
		    	contents = gateIDString + " " + gateName + " " + prevGate1String + " " + prevGate2String + "\n"; 
		    	pw.write(contents);
		    	pw.flush();
		    }
		    pw.close();
	    }
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
