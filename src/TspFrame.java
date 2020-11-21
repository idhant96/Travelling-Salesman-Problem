import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class TspFrame extends JFrame {

	TspFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		MenuPanel menuPanel = new MenuPanel();
		getContentPane().add(menuPanel);
		
		CanvasPanel canvasPanel = new CanvasPanel();
		getContentPane().add(canvasPanel);
		
		menuPanel.setCanvasPanel(canvasPanel);
	}
	
	public void display() {
		pack();
		setVisible(true);
	}
	
}
