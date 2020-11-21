import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class SaveActionListener  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify file to save");   
		int userSelection = fileChooser.showSaveDialog(getSourcePanel(e));
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    TspController.getInstance().saveFile(fileToSave);
		}
		
	}
	
	public MenuPanel getSourcePanel(ActionEvent e){
		JMenuItem menuItem = (JMenuItem) e.getSource(); 
		JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent(); 
		Component invoker = popupMenu.getInvoker();
		JMenuBar bar = (JMenuBar)invoker.getParent();
		MenuPanel menuPanel = (MenuPanel) bar.getParent();
		return menuPanel;
	}

}
