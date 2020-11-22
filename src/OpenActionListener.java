import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class OpenActionListener  implements ActionListener {

	/**
	 * Action to be performed when open button in menu is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		MenuPanel menuPanel = getSourcePanel(e);
		if(menuPanel == null) {
			return;
		}
		CanvasPanel canvasPanel = menuPanel.getCanvasPanel();
		if(canvasPanel == null) {
			return;
		}
		int result = fileChooser.showOpenDialog(menuPanel);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
			canvasPanel.setToInputState();
			int height = canvasPanel.getHeight() - 10;
			int width = canvasPanel.getWidth() - 20;
			TspController.getInstance().cleanSlate();
			TspController.getInstance().loadFile(selectedFile, height, width);
		}
	}
	

	/**
	 * Returns the SourcePanel for event source.
	 * @param e
	 * @return
	 */
	
	public MenuPanel getSourcePanel(ActionEvent e){
		JMenuItem menuItem = (JMenuItem) e.getSource(); 
		JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent(); 
		Component invoker = popupMenu.getInvoker();
		JMenuBar bar = (JMenuBar)invoker.getParent();
		MenuPanel menuPanel = (MenuPanel) bar.getParent();
		return menuPanel;
	}

}