import java.io.File;
import java.util.List;
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
        dataHandler.loadDataPointsFromFile(file, scaleToHeight, scaleToWidth);
    }

    public void saveFile(File filePath) {
        TspDataHandler dataHandler = new TspDataHandler();
        dataHandler.saveFile(filePath);
    }

    public void start() {
    	if(classroom == null) {
    		classroom = new Classroom();
    	}
        Thread t = new Thread(classroom);
        t.start();
        
    }

    public void stop() {
        classroom.stop();
    }

    public void cleanSlate() {
    	classroom = new Classroom();
        BlackBoard.getInstance().cleanSlate();
    }

    public void addCoordinatesToDataPoints(int x, int y) {
        TspDataHandler dataHandler = new TspDataHandler();
        dataHandler.addCoordinatesToDataPoints(x, y);
    }

    public List<int[]> getCoordinatesDataPoints() {
        TspDataHandler dataHandler = new TspDataHandler();
        return dataHandler.getCoordinatesDataPoints();
    }

    public TspShortestPaths getTspShortestPaths() {
        return BlackBoard.getInstance().getTspShortestPaths();
    }

    public void observeBlackboard(Observer observer) {
        BlackBoard.getInstance().addObserver(observer);
    }

    public static TspController getInstance() {
        if (controllerInstance == null) {
            controllerInstance = new TspController();
        }
        return controllerInstance;
    }
}
