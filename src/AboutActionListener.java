import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class AboutActionListener implements MenuListener {

	/**
	 * Displays authors when about menu is selected.
	 * @param e
	 */
	@Override
	public void menuSelected(MenuEvent e) {
		JDialog d = new JDialog(getJFrame(e), "Authors");
		d.setModal(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Akhilesh Krishnan (akrish84"));
		panel.add(new JLabel("Rahul Suresh (rsures13)"));
		panel.add(new JLabel("Idhant Haldankar (ihaldank)"));
		d.add(panel);
		d.setPreferredSize(new Dimension(400, 200));
		d.pack();
		d.setLocationRelativeTo(getJFrame(e));
		d.setVisible(true);
	}
	
	/**
	 * Gets the JFrame from source of event.
	 * @param e
	 * @return
	 */
	public JFrame getJFrame(MenuEvent e){
		JMenu popupMenu = (JMenu) e.getSource(); 
		JMenuBar bar = (JMenuBar)popupMenu.getParent();
		MenuPanel menuPanel = (MenuPanel) bar.getParent();
		return (JFrame) SwingUtilities.getWindowAncestor(menuPanel);
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
}
