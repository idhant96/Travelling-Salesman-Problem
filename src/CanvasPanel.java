import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 6981842796760492347L;
	private static final int VISITED_CITY_PAINT_DIAMETER= 5;
	private static final Color FIRST_PATH_COLOR = Color.RED;
	private static final Color SECOND_PATH_COLOR = Color.PINK;
	private static final Color THIRD_PATH_COLOR = Color.GRAY;
	private static final Color POINTS_COLOR = Color.BLUE;
	private static final int FIRST_PATH_LINE_THICKNESS = 5;
	private static final int SECOND_PATH_LINE_THICKNESS = 10;
	private static final int THIRD_PATH_LINE_THICKNESS = 15;
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

	/**
	 * Fetches information from the blackboard and paints on the canvas panel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(currentState !=  State.INIT_STATE) {
			List<int[]> coordinates = TspController.getInstance().getCoordinatesDataPoints();
			if(coordinates == null) {
				return;
			}
			if(currentState == State.INPUT_STATE) {
				for(int[] coordinate : coordinates) {
					g.setColor(POINTS_COLOR);
					g.fillOval(coordinate[0], coordinate[1], VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
				}
			} else if (currentState == State.OUTPUT_STATE){
				for(int[] coordinate : coordinates) {
					g.setColor(POINTS_COLOR);
					g.fillOval(coordinate[0], coordinate[1], VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
				}
				TspShortestPaths tspShortestPaths = TspController.getInstance().getTspShortestPaths();
				drawLine(g, tspShortestPaths.getThirdShortestPath(), coordinates, THIRD_PATH_LINE_THICKNESS,THIRD_PATH_COLOR);
				drawLine(g, tspShortestPaths.getSecondShortestPath(), coordinates, SECOND_PATH_LINE_THICKNESS, SECOND_PATH_COLOR);
				drawLine(g, tspShortestPaths.getFirstShortestPath(), coordinates, FIRST_PATH_LINE_THICKNESS,  FIRST_PATH_COLOR);

			}
		}
	}
	
	/**
	 * Draws a lines connecting paths for the given tspPath
	 * @param g
	 * @param tspPath
	 * @param coordinates
	 * @param stroke
	 * @param lineColor
	 */
	private void drawLine(Graphics g, TspPath tspPath, List<int[]> coordinates, int stroke, Color lineColor) {
		List<Integer> cities = tspPath.getPath();
		for(int i = 1; i < cities.size(); i++) {
			int city1 = cities.get(i-1);
			int city2 = cities.get(i);
			
			int x1 = coordinates.get(city1)[0];
			int y1 = coordinates.get(city1)[1];
			
			int x2 = coordinates.get(city2)[0];
			int y2 = coordinates.get(city2)[1];
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(stroke));
			g2.setColor(lineColor);
			g2.drawLine(x1 + (VISITED_CITY_PAINT_DIAMETER / 2), y1 + (VISITED_CITY_PAINT_DIAMETER / 2),
					x2 + (VISITED_CITY_PAINT_DIAMETER / 2), y2 + (VISITED_CITY_PAINT_DIAMETER / 2));

			g2.setColor(POINTS_COLOR);
			g2.fillOval(x1, y1, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);

			g2.setColor(POINTS_COLOR);
			g2.fillOval(x2, y2, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
		}
	}
	
	/**
	 * Sets state to input state
	 */
	public void setToInputState() {
		currentState = State.INPUT_STATE;
	}
	
	/**
	 * Sets state to output state
	 */
	public void setToOuputState() {
		currentState = State.OUTPUT_STATE;
	}

	/**
	 * Repaints the canvas pane
	 */
	public void clean() {
		repaint();
	}

	/**
	 * checks if current state 
	 * @return boolean
	 */
	public boolean inOutputState() {
		return currentState == State.OUTPUT_STATE;
	}

	/**
	 * State of the canvas panel
	 * INIT_STATE - initialized state, before accepting input
	 * INPUT_STATE - Read inputs from file or add points on the canvas panel
	 * OUTPUT_STATE - Paints top 3 paths on the canvas panel
	 */
	enum State {
		INIT_STATE,
		INPUT_STATE,
		OUTPUT_STATE
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}


