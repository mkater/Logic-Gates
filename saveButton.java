import java.io.*;
/*
 * This class saves the file both in plain text and in binary.  It is quite similar to the reverse of reading/opening the file.  It takes all the values of ints and strings and combines
 * them into the appropriate gate line, and then converts this into one string line.  It does a similar thing in binary, where it converts string to binary for saving with different
 * numerical representations of the different kinds of gates.
 */
public class saveButton {
	
	int gateID, prevGate1, prevGate2;
	String gateName, gateIDString, prevGate1String, prevGate2String, contents = "";
	
	public saveButton(String in_type) 
	{
			if (in_type == "Plain")
			{
				try {
					FileOutputStream file = new FileOutputStream("outputfile.txt", false);	
					PrintWriter pw = new PrintWriter(file);

					for (int i = 0; i < GatePanel.allGates.size(); i++)
					{
						gateID = GatePanel.allGates.get(i).getGateValue();
						gateIDString = Integer.toString(gateID);
						
						if (GatePanel.allGates.get(i).getGateName() != null)
						{
							gateName = GatePanel.allGates.get(i).getGateName();
						}
						if (GatePanel.allGates.get(i).getPreviousGateA() != null)
						{
							prevGate1 = GatePanel.allGates.get(i).getPreviousGateA().getGateValue();
							prevGate1String = Integer.toString(prevGate1);
						}
						else
						{
							prevGate1String = "";
						}
						if (GatePanel.allGates.get(i).getPreviousGateB() != null)
						{
							prevGate2 = GatePanel.allGates.get(i).getPreviousGateB().getGateValue();
							prevGate2String = Integer.toString(prevGate2);
						}
						else
						{
							prevGate2String = "";
							prevGate2 = 0;
						}
						
						if(gateName.equals("true") || gateName.equals("false") || gateName.equals("input"))
						{
							contents =  gateIDString + " " + gateName;
						}
						else if(gateName.equals("output") || gateName.equals("not"))
						{
							contents =  gateIDString + " " + gateName + " " + prevGate1String;
						}
						else
						{
							contents =  gateIDString + " " + gateName + " " + prevGate1String + " " + prevGate2String;
						}
						pw.println(contents);
					}
					pw.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			if (in_type == "Binary")
			{
				try {
					FileOutputStream file = new FileOutputStream("outputfile2.bin", false);
					PrintWriter pw = new PrintWriter(file);
					
					for (int i = 0; i < GatePanel.allGates.size(); i++)
					{
						gateID = GatePanel.allGates.get(i).getGateValue();
						gateIDString = Integer.toString(gateID);
						
						if (GatePanel.allGates.get(i).getGateName() != null)
						{
							gateName = GatePanel.allGates.get(i).getGateName();
						}
						if (GatePanel.allGates.get(i).getPreviousGateA() != null)
						{
							prevGate1 = GatePanel.allGates.get(i).getPreviousGateA().getGateValue();
							prevGate1String = Integer.toString(prevGate1);
						}
						else
						{
							prevGate1String = "";
						}
						if (GatePanel.allGates.get(i).getPreviousGateB() != null)
						{
							prevGate2 = GatePanel.allGates.get(i).getPreviousGateB().getGateValue();
							prevGate2String = Integer.toString(prevGate2);
						}
						else
						{
							prevGate2String = "";
							prevGate2 = 0;
						}
						
						String writer = toBinary(gateID, gateName, prevGate1, prevGate2);
						pw.write(writer);	
					}
					pw.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}		
	}
	public String toBinary(int gateID_in, String gateName_in, int prevGate1_in, int prevGate2_in)
	{
		String gateID_out, gateName_out, prevGate1_out, prevGate2_out, contents_to_binary;

		gateName_out = "";
		contents_to_binary = "";
		gateID_out = Integer.toBinaryString(gateID_in);		
		prevGate1_out = Integer.toBinaryString(prevGate1_in);
		prevGate2_out = Integer.toBinaryString(prevGate2_in);
		
		int length_gateID = gateID_out.length();
		int length_prevGate1 = prevGate1_out.length();
		int length_prevGate2 = prevGate2_out.length();
		
		StringBuilder str = new StringBuilder("");
		
		if(length_gateID < 16)
		{
			for (int i = length_gateID; i < 15; i++)
			{
				str.insert(0, '0');
			}
			gateID_out = str.toString() + gateID_out;
			str.replace(0, str.length() -1, "");
		}
		if(prevGate1_in != 0 && length_prevGate1 < 16)
		{
			for (int i = length_prevGate1; i < 15; i++)
			{
				str.insert(0, '0');
			}
			prevGate1_out = str.toString() + prevGate1_out;
			str.replace(0, str.length() -1, "");
		}
		else
		{
			prevGate1_out = "0000000000000000";
		}
		if(prevGate2_in != 0 && length_prevGate2 < 16)
		{
			for (int i = length_prevGate2; i < 15; i++)
			{
				str.insert(0, '0');
			}
			prevGate2_out = str.toString() + prevGate2_out;
			str.replace(0, str.length() -1, "");
		}
		else
		{
			prevGate2_out = "0000000000000000";
		}
		if (gateName_in == "input")
		{
			gateName_out = "00000001";
		}
		else if(gateName_in == "output")
		{
			gateName_out = "00000010";
		}
		else if(gateName_in == "and")
		{
			gateName_out = "00000011";
		}
		else if(gateName_in == "nand") 
		{
			gateName_out = "00000100";
		}
		else if (gateName_in == "or")
		{
			gateName_out = "00000101";
		}
		else if(gateName_in == "xor")
		{
			gateName_out = "00000110";
		}
		else if (gateName_in == "not")
		{
			gateName_out = "00000111";
		}
		else if (gateName_in == "true")
		{
			gateName_out = "000001000";
		}
		else if (gateName_in == "false")
		{
			gateName_out = "000001001";
		}
				
		return contents_to_binary = gateID_out + " " + gateName_out + " " + prevGate1_out + " " + prevGate2_out + "\n";
	}
}
