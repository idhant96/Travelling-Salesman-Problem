import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CanvasMouseListener implements MouseListener{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		CanvasPanel canvasPanel = (CanvasPanel)e.getSource();
		if(canvasPanel.inOutputState()) {
			return;
		}
		int x = e.getX();
		int y = e.getY();
		canvasPanel.setToInputState();
		TspController.getInstance().addCoordinatesToDataPoints(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
