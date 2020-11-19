import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 6981842796760492347L;
	private static final int VISITED_CITY_PAINT_DIAMETER= 5;
	private static final Color FIRST_PATH_COLOR = Color.RED;
	private static final Color SECOND_PATH_COLOR = Color.PINK;
	private static final Color THIRD_PATH_COLOR = Color.ORANGE;
	private static final Color POINTS_COLOR = Color.BLUE;
	private State currentState;;
	
	public CanvasPanel() {
		super();
		setOpaque(true);
		setBackground(Color.WHITE);
		addMouseListener(new CanvasMouseListener());
		setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		revalidate();
		TspController.getInstance().observeBlackboard(this);
		currentState = State.INIT_STATE;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(currentState ==  State.INPUT_STATE) {
			List<int[]> coordinates = TspController.getInstance().getCoordinatesDataPoints();
			System.out.println("State is " + currentState);
			if(currentState == State.INPUT_STATE) {
				for(int[] coordinate : coordinates) {
					g.setColor(POINTS_COLOR);
					g.fillOval(coordinate[0], coordinate[1], VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
				}
			} else if (currentState == State.OUTPUT_STATE){
				System.out.println("OutputState");
				TspShortestPaths tspShortestPaths = TspController.getInstance().getTspShortestPaths();
				drawLine(g, tspShortestPaths.getFirstShortestPath(), coordinates, FIRST_PATH_COLOR);
				drawLine(g, tspShortestPaths.getSecondShortestPath(), coordinates, SECOND_PATH_COLOR);
				drawLine(g, tspShortestPaths.getThirdShortestPath(), coordinates, THIRD_PATH_COLOR);
			}
		}
	}
	
	private void drawLine(Graphics g, TspPath tspPath, List<int[]> coordinates, Color lineColor) {
		List<Integer> cities = tspPath.getPath();
		for(int i = 1; i < cities.size(); i++) {
			int city1 = cities.get(i-1);
			int city2 = cities.get(i);
			
			int x1 = coordinates.get(city1)[0];
			int y1 = coordinates.get(city1)[1];
			
			int x2 = coordinates.get(city2)[0];
			int y2 = coordinates.get(city2)[1];
			
			g.setColor(lineColor);
			g.drawLine(x1 + (VISITED_CITY_PAINT_DIAMETER / 2), y1 + (VISITED_CITY_PAINT_DIAMETER / 2),
					x2 + (VISITED_CITY_PAINT_DIAMETER / 2), y2 + (VISITED_CITY_PAINT_DIAMETER / 2));

			g.setColor(POINTS_COLOR);
			g.fillOval(x1, y1, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);

			g.setColor(POINTS_COLOR);
			g.fillOval(x2, y2, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
		}
	}
	
	public void setToInputState() {
		currentState = State.INPUT_STATE;
	}
	
	public void setToOuputState() {
		currentState = State.OUTPUT_STATE;
	}
	
	enum State {
		INIT_STATE,
		INPUT_STATE,
		OUTPUT_STATE
	}


	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update");
		repaint();
	}
	
}


