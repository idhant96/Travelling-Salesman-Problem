import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class SaveActionListener  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify file to save");   
		 
		int userSelection = fileChooser.showSaveDialog((MenuPanel)e.getSource());
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    TspController.getInstance().saveFile(fileToSave);
		}
		
	}

}
