import java.io.File;
import java.util.Observer;

public class TspController {

	private static TspController controllerInstance;
	private Classroom classroom;
	
	private TspController() {
		
	}
	
	public void addClassRoom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public void loadFile(File file, int scaleToHeight, int scaleToWidth) {
		TspDataHandler dataHandler = new TspDataHandler();
		// dataHandler.loadDataPointsFromFile(file);
		//dataHandler.loadDataPointsFromFile(filePath, scaleToHeight, scaleToWidth);
	}
	
	public void saveFile(File filePath) {
		TspDataHandler dataHandler = new TspDataHandler();
		// dataHandler.saveFile(filePath);
	}
	
	public void start() {
		classroom.start();
	}
	
	public void stop() {
		classroom.stop();
	}
	
	public void cleanSlate() {
		
	}

	public void addCoordinatesToDataPoints(int x, int y) {
		TspDataHandler dataHandler = new TspDataHandler();
		//dataHandler.addCoordinatesToDataPoints(x, y);
	}
	
	public boolean isTspComputing() {
		return BlackBoard.getInstance().isTspComputing();
	}
	
	public DataPoints getDataPoints() {
		return BlackBoard.getInstance().getDatapoints();
	}
	
	public TspShortestPaths getTspShortestPaths() {
		return BlackBoard.getInstance().getTspShortestPaths();
	}
	
	public void observeBlackboard(Observer observer ) {
		BlackBoard.getInstance().addObserver(observer);
	}
	
	public static TspController getInstance() {
		if(controllerInstance == null) {
			controllerInstance = new TspController();
		}
		return controllerInstance;
	}
}
