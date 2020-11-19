import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class StopActionListener  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TspController.getInstance().stop();
		JPanel menuPanel = (MenuPanel)e.getSource();
		Container parent = menuPanel.getParent();
		Component[] children = parent.getComponents();
		for(int i = 0 ; i < children.length; i++) {
			Component child = children[i];
			if(child instanceof CanvasPanel) {
				child = (CanvasPanel) child;
				
			}
		}
		
	}

}
