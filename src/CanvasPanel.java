import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 6981842796760492347L;
	private boolean draw;
	private State currentState;
	
	public CanvasPanel() {
		super();
		setOpaque(true);
		setBackground(Color.WHITE);
		addMouseListener(new CanvasMouseListener());
		setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		revalidate();
		currentState = State.INPUT_STATE;
		draw = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(draw) {
			if(currentState==State.INPUT_STATE) {
				// Get DataPoints From blackboard.
				// Get coordinates from Datapoints.
				// iterate coordinates
				// plot coordinates
				// for(int[] coordinate in coordinates) {
				//		g.setColor(VISITED_CITY_PAINT_COLOR);
				//		g.fillOval(coordinate[0], coordinate[1], VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
			} else {
				// Get TspShortestPath.
				// Clone TspShortestPath.
				// pathIdx = 0
				// for(TspPath path : TspShortestPath):
				//   color = "red"
				//   switch(pathIdx++):
				//	    case 1: red
				//		case 2: blue
				//		case 3: green
				//   List<int> cities:path.cities;
				//   for i = 1 to cities.length
//					    x1 = cities[i-1][0]
//					    y1 = cities[i-1][1]
//					    x2 = cities[i][0]
//					    y2 = cities[i][1]
//						g.setColor(color);
//						g.drawLine(x1 + (VISITED_CITY_PAINT_DIAMETER / 2), y1 + (VISITED_CITY_PAINT_DIAMETER / 2),
//								x2 + (VISITED_CITY_PAINT_DIAMETER / 2), y2 + (VISITED_CITY_PAINT_DIAMETER / 2));
//		
//						g.setColor(VISITED_CITY_PAINT_COLOR);
//						g.fillOval(x1, y1, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
//		
//						g.setColor(VISITED_CITY_PAINT_COLOR);
//						g.fillOval(x2, y2, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
				   
			}
		}
		draw = false;
	}

	public void setToInputState() {
		currentState = State.INPUT_STATE;
	}
	
	public void setToOutputState() {
		currentState = State.OUTPUT_STATE;
	}
	
	
	enum State {
		INPUT_STATE,
		OUTPUT_STATE
	}


	@Override
	public void update(Observable o, Object arg) {
		draw = true;
		repaint();
	}
	
}


