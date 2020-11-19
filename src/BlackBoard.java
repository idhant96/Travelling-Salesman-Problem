import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class BlackBoard extends Observable {
    private DataPoints datapoints;
    private TspShortestPaths tspShortestPaths;
    private Map<Integer, TspPath> startCityToTsppath;
    private static BlackBoard blackboardInstance;
    
    private BlackBoard() {
    }

    public void addCoordinatesToDataPoints(List<int[]> coordinatesData) {

    }

    public DataPoints getDatapoints() {
        return null;

    }

    public void getTspShortestPaths() {

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

    }

    public static BlackBoard getInstance() {
    	if(blackboardInstance == null) {
    		blackboardInstance = new BlackBoard();
    	}
    	return blackboardInstance;
    }

}
