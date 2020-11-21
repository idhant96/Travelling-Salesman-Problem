import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopActionListener  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TspController.getInstance().stop();
	}

}
