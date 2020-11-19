import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TspController.getInstance().cleanSlate();
	}

}
