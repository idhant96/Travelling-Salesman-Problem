import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RunActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuPanel menuPanel = getSourcePanel(e);
		menuPanel.canvasPanel.setToOuputState();
		TspController.getInstance().start();
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
