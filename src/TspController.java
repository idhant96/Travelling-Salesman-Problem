
public class TspController {

	private static TspController controllerInstance;
	private Classroom classroom;
	
	private TspController() {
		
	}
	
	public void addClassRoom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public void loadFile(String filePath, int scaleToHeight, int scaleToWidth) {
		TspDataHandler dataHandler = new TspDataHandler();
		dataHandler.loadDataPointsFromFile(filePath);
		//dataHandler.loadDataPointsFromFile(filePath, scaleToHeight, scaleToWidth);
	}
	
	public void saveFile(String filePath) {
		TspDataHandler dataHandler = new TspDataHandler();
		// dataHandler.saveFile(filePath);
	}
	
	public void start() {
		classroom.start();
	}
	
	public void stop() {
		classroom.stop();
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
	public static TspController getInstance() {
		if(controllerInstance == null) {
			controllerInstance = new TspController();
		}
		return controllerInstance;
	}
}
