import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class OpenActionListener  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		CanvasPanel canvasPanel = (CanvasPanel) e.getSource();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(canvasPanel);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    int height = canvasPanel.getHeight();
			int width = canvasPanel.getWidth();
			TspController.getInstance().loadFile(selectedFile, height, width);
		}
	}

}