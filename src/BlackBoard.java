import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class BlackBoard extends Observable {
    private DataPoints datapoints;
    private TspShortestPaths tspShortestPaths;
    private Map<Integer, TspPath> startCityToTsppath;
    private static BlackBoard blackboardInstance;
    private boolean tspComputing = false;
    
    private BlackBoard() {
    }

    public void addCoordinatesToDataPoints(List<int[]> coordinatesData) {

    }

    public DataPoints getDatapoints() {
        return null;

    }

    public TspShortestPaths getTspShortestPaths() {
    	return tspShortestPaths;
    }

    public void cleanSlate() {

    }

    public TspPath getTspPathForStartCity() {
        return null;
    }

    public List<TspPath> getTspPaths() {
        return null;

    }

    public void update() {
    	setChanged();
    	notifyObservers();
    }
    
    public Map<Integer, TspPath> getStartCityToTsppath() {
    	return startCityToTsppath;
    }
    
    public TspShortestPaths getTspShortestPath() {
    	if(tspShortestPaths == null) {
    		tspShortestPaths = new TspShortestPaths();
    	}
    	return tspShortestPaths;
    }
    
    public boolean isTspComputing() {
    	return tspComputing;
    }
    public void setTspComputing(boolean isTspComputing) {
    	tspComputing = isTspComputing;
    }
    
    public void setShortestTspPaths(TspPath firstShortestPath, TspPath secondShortestPath, TspPath thirdShortestPath) {
    	TspShortestPaths tspShortestPaths =  getTspShortestPath();
    	tspShortestPaths.setFirstShortestPath(firstShortestPath);
    	tspShortestPaths.setSecondShortestPath(secondShortestPath);
    	tspShortestPaths.setThirdShortestPath(thirdShortestPath);
    	update();
    }

    public static BlackBoard getInstance() {
    	if(blackboardInstance == null) {
    		blackboardInstance = new BlackBoard();
    	}
    	return blackboardInstance;
    }


}
