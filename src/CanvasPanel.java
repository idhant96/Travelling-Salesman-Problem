import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 6981842796760492347L;
	private boolean draw;
	private static final int VISITED_CITY_PAINT_DIAMETER= 5;
	private static final Color FIRST_PATH_COLOR = Color.RED;
	private static final Color SECOND_PATH_COLOR = Color.PINK;
	private static final Color THIRD_PATH_COLOR = Color.ORANGE;
	private static final Color POINTS_COLOR = Color.BLUE;
	
	public CanvasPanel() {
		super();
		setOpaque(true);
		setBackground(Color.WHITE);
		addMouseListener(new CanvasMouseListener());
		setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		revalidate();
		draw = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(draw) {
			DataPoints dataPoints = TspController.getInstance().getDataPoints(); 
			if(!TspController.getInstance().isTspComputing()) {
				for(int[] coordinate : dataPoints.coordinates) {
					g.setColor(POINTS_COLOR);
					g.fillOval(coordinate[0], coordinate[1], VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
				}
			} else {
				TspShortestPaths tspShortestPaths = TspController.getInstance().getTspShortestPaths();
				drawLine(g, tspShortestPaths.getFirstShortestPath(), dataPoints, FIRST_PATH_COLOR);
				drawLine(g, tspShortestPaths.getSecondShortestPath(), dataPoints, SECOND_PATH_COLOR);
				drawLine(g, tspShortestPaths.getThirdShortestPath(), dataPoints, THIRD_PATH_COLOR);

			}
		}
		draw = false;
	}
	
	private void drawLine(Graphics g, TspPath tspPath, DataPoints dataPoints, Color lineColor) {
		List<Integer> cities = tspPath.getPath();
		for(int i = 1; i < cities.size(); i++) {
			int city1 = cities.get(i-1);
			int city2 = cities.get(i);
			
			int x1 = dataPoints.coordinates.get(city1)[0];
			int y1 = dataPoints.coordinates.get(city1)[1];
			
			int x2 = dataPoints.coordinates.get(city2)[0];
			int y2 = dataPoints.coordinates.get(city2)[1];
			
			g.setColor(lineColor);
			g.drawLine(x1 + (VISITED_CITY_PAINT_DIAMETER / 2), y1 + (VISITED_CITY_PAINT_DIAMETER / 2),
					x2 + (VISITED_CITY_PAINT_DIAMETER / 2), y2 + (VISITED_CITY_PAINT_DIAMETER / 2));

			g.setColor(POINTS_COLOR);
			g.fillOval(x1, y1, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);

			g.setColor(POINTS_COLOR);
			g.fillOval(x2, y2, VISITED_CITY_PAINT_DIAMETER, VISITED_CITY_PAINT_DIAMETER);
		}
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


