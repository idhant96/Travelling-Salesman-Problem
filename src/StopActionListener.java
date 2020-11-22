import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopActionListener  implements ActionListener {

	/**
	 * Action to be performed when stop button in menu is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		TspController.getInstance().stop();
	}

}
