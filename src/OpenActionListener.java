import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

public class OpenActionListener  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		MenuPanel menuPanel = getSourcePanel(e);
		if(menuPanel == null) {
			System.out.println("Null Menu Panel");
			return;
		}
		CanvasPanel canvasPanel = menuPanel.getCanvasPanel();
		if(canvasPanel == null) {
			System.out.println("Null canvas Panel");
			return;
		}
		int result = fileChooser.showOpenDialog(menuPanel);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    
			canvasPanel.setToInputState();
			int height = canvasPanel.getHeight();
			int width = canvasPanel.getWidth();
			TspController.getInstance().loadFile(selectedFile, height, width);
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