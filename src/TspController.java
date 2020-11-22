import java.io.File;
import java.util.List;
import java.util.Observer;


public class TspController {

	private static TspController controllerInstance;
	private Classroom classroom;

	
	private TspController() {

	}

	/**
	 * Asks TspDataHandler to Load file and store in Data Points
	 * @param file
	 * @param scaleToHeight
	 * @param scaleToWidth
	 */
	public void loadFile(File file, int scaleToHeight, int scaleToWidth) {
		TspDataHandler dataHandler = new TspDataHandler();
		dataHandler.loadDataPointsFromFile(file, scaleToHeight, scaleToWidth);
	}

	/**
	 * Asks TspDataHandler to save the points in DataPoints in the given file
	 * @param filePath
	 */
	public void saveFile(File file) {
		TspDataHandler dataHandler = new TspDataHandler();
		dataHandler.saveFile(file);
	}

	/**
	 * Start runnin classroom thread.
	 */
	public void start() {
		if (classroom == null) {
			classroom = new Classroom();
		}
		Thread t = new Thread(classroom);
		t.start();

	}

	/**
	 * Stop classroom thread.
	 */
	public void stop() {
		if(classroom != null) {
			classroom.stop();
		}
	}

	/**
	 * Clean classroom and blackboard.
	 */
	public void cleanSlate() {
		if(classroom != null) {
			classroom = null;
		}
		BlackBoard.getInstance().cleanSlate();
	}

	/**
	 * Add coorinate to data points.
	 * @param x
	 * @param y
	 */
	public void addCoordinatesToDataPoints(int x, int y) {
		TspDataHandler dataHandler = new TspDataHandler();
		dataHandler.addCoordinatesToDataPoints(x, y);
	}

	/**
	 * Get coordinates from data points
	 * @return
	 */
	public List<int[]> getCoordinatesDataPoints() {
		TspDataHandler dataHandler = new TspDataHandler();
		return dataHandler.getCoordinatesDataPoints();
	}

	/**
	 * Get Tsp Shortest Paths
	 * @return
	 */
	public TspShortestPaths getTspShortestPaths() {
		return BlackBoard.getInstance().getTspShortestPaths();
	}

	/**
	 * Make observer observe blackboard.
	 * @param observer
	 */
	public void observeBlackboard(Observer observer) {
		BlackBoard.getInstance().addObserver(observer);
	}

	/**
	 * Returns TspController instance
	 * @return TspController
	 */
	public static TspController getInstance() {
		if (controllerInstance == null) {
			controllerInstance = new TspController();
		}
		return controllerInstance;
	}
}
