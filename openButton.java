import javax.swing.JFileChooser;
/*
 * This class finds the correct file to open when trying to open a file.
 */
public class openButton {
	
	String path;
	
	public openButton() 
	{	
		JFileChooser fileChooser =  new JFileChooser();
		fileChooser.showOpenDialog(null);
		path = fileChooser.getSelectedFile().getAbsolutePath();
	}

	public String getPath()
	{
		return path;
	}
}


