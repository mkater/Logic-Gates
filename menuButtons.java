import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/*
 * This class adds all the buttons to the menu and has their connecting functionality.
 */
public class menuButtons extends JMenuBar{
	
	public static String path;
	public String content = "This will be where you store the content to save";
	
	public static Boolean inserting = false;
	public static Boolean reloadFile = false;
	
	public menuButtons() {
		
		JMenu file = new JMenu("File");
		add(file);
		JMenuItem openFile = new JMenuItem("Open");
		file.add(openFile);
		
		JMenu save = new JMenu("Save");
		add(save);
		JMenuItem binary = new JMenuItem("Binary");
		save.add(binary);
		JMenuItem plain = new JMenuItem("Plain");
		save.add(plain);
		
		
		JMenu insert = new JMenu("Insert New Gate");
		add(insert);
		JMenuItem or = new JMenuItem("OR");
		insert.add(or);
		JMenuItem xor = new JMenuItem("XOR");
		insert.add(xor);
		JMenuItem and = new JMenuItem("AND");
		insert.add(and);
		JMenuItem nand = new JMenuItem("NAND");
		insert.add(nand);
		JMenuItem not = new JMenuItem("NOT");
		insert.add(not);
		JMenuItem input = new JMenuItem("INPUT");
		insert.add(input);
		JMenuItem output = new JMenuItem("OUTPUT");
		insert.add(output);
		
		
		class openAction implements ActionListener{
			public void actionPerformed (ActionEvent e) {

				if(e.getActionCommand() == "Open")
				{
					openButton open = new openButton();
					path = open.getPath();
					System.out.println("playingfield: " + GatePanel.playingField);
					GatePanel.playingField.ReloadFile(path);
					GatePanel.playingField.repaint();
					reloadFile = true;
				}
			}
		}
		
		class saveAction implements ActionListener{
			public void actionPerformed (ActionEvent e) {
				
				if(e.getActionCommand() == "Plain")
				{
						saveButton save = new saveButton("Plain");
				}
				if(e.getActionCommand() == "Binary")
				{
						saveButton save = new saveButton("Binary");
				}
			}
		}
		
		class insertAction implements ActionListener{
			public void actionPerformed (ActionEvent a) {
				if(Panel.getIsEditMode())
				{
					if(a.getActionCommand() == "OR")
					{
						InsertGate gate = new InsertGate("OR");
						gate.setGate();
						inserting = true;
					}
					if(a.getActionCommand() == "XOR")
					{
						InsertGate gate = new InsertGate("XOR");
						gate.setGate();
						inserting = true;
					}
					if(a.getActionCommand() == "AND")
					{
						InsertGate gate = new InsertGate("AND");
						gate.setGate();
						inserting = true;
					}
					if(a.getActionCommand() == "NAND")
					{
						InsertGate gate = new InsertGate("NAND");
						gate.setGate();
						inserting = true;
					}
					if(a.getActionCommand() == "INPUT")
					{
						InsertGate gate = new InsertGate("INPUT");
						gate.setGate();
						inserting = true;
					}
					if(a.getActionCommand() == "OUTPUT")
					{
						InsertGate gate = new InsertGate("OUTPUT");
						gate.setGate();
						inserting = true;
					}
					if(a.getActionCommand() == "NOT")
					{
						InsertGate gate = new InsertGate("NOT");
						gate.setGate();
						inserting = true;
					}
				}
			}
		}

		openFile.addActionListener(new openAction());
		binary.addActionListener(new saveAction());
		plain.addActionListener(new saveAction());
		or.addActionListener(new insertAction());
		not.addActionListener(new insertAction());
		xor.addActionListener(new insertAction());
		and.addActionListener(new insertAction());
		nand.addActionListener(new insertAction());
		input.addActionListener(new insertAction());
		output.addActionListener(new insertAction());
		
	}
	
	public static Boolean isInserting()
	{
		return inserting;
	}
	public static Boolean isReloadFile()
	{
		return reloadFile;
	}
	public static  String pathToFile()
	{
		return path;
	}
}
