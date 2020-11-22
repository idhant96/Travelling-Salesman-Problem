import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class NewActionListener implements ActionListener {

	/**
	 * Action to be performed when new button in menu is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		TspController.getInstance().cleanSlate();
		MenuPanel menuPanel = getSourcePanel(e);
		menuPanel.getCanvasPanel().setToInputState();
		menuPanel.getCanvasPanel().clean();
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
