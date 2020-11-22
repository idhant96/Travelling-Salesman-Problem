import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * A singleton class to maintain all the data/datastructures used in this applicaton.
 *
 */
public class BlackBoard extends Observable {
    private DataPoints datapoints;
    private TspShortestPaths tspShortestPaths;
    private Map<Integer, TspPath> startCityToTspPath;
    private static BlackBoard blackboardInstance;

    private BlackBoard() {
    }

    /**
     * Adds coordinates to data points and notifies blackboard observers.
     * @param coordinate
     */
    public void addCoordinatesToDataPoints(int[] coordinate) {
    	if(datapoints == null) {
    		datapoints = new DataPoints();
    	}
        datapoints.addCityCoordinates(coordinate);
        update();
    }

    /**
     * Returns data points
     * @return
     */
    public DataPoints getDatapoints() {
        return datapoints;
    }

    /**
     * returns TspShortestPAths
     * @return
     */
    public TspShortestPaths getTspShortestPaths() {
    	if(tspShortestPaths == null) {
    		tspShortestPaths = new TspShortestPaths();
    	}
        return tspShortestPaths;
    }

    /**
     * Cleans all data in the blackboard.
     */
    public void cleanSlate() {
        datapoints = null;
        tspShortestPaths = null;
        startCityToTspPath = null;

    }

    /**
     * Returns TspPath object for startCity
     * @param startCity
     * @return
     */
    public TspPath getTspPathForStartCity(int startCity) {
        if (startCityToTspPath == null) {
            startCityToTspPath = new HashMap<>();
        }
        if (startCityToTspPath.containsKey(startCity)) {
            return startCityToTspPath.get(startCity);
        } else {
            return null;
        }

    }

    /**
     * Adds tsp path for city
     * @param tspPath
     */
    public void addTspPathForStartCity( TspPath tspPath) {
        if (startCityToTspPath == null) {
            startCityToTspPath = new HashMap<>();
        }
        startCityToTspPath.put(tspPath.getStartCity(), tspPath);
    }


    /**
     * Notifies its observers about change in its data.
     */
    public void update() {
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the start city to tsp path map
     * @return
     */
    public Map<Integer, TspPath> getStartCityToTsppath() {
        return startCityToTspPath;
    }

    /**
     * returns tspShortestPath object
     * @return
     */
    public TspShortestPaths getTspShortestPath() {
        if (tspShortestPaths == null) {
            tspShortestPaths = new TspShortestPaths();
        }
        return tspShortestPaths;
    }

    /**
     * Sets shortest tsp paths and notifies all blackboard observers
     * @param firstShortestPath
     * @param secondShortestPath
     * @param thirdShortestPath
     */
    public void setShortestTspPaths(TspPath firstShortestPath, TspPath secondShortestPath, TspPath thirdShortestPath) {
        TspShortestPaths tspShortestPaths = getTspShortestPath();
        tspShortestPaths.setFirstShortestPath(firstShortestPath);
        tspShortestPaths.setSecondShortestPath(secondShortestPath);
        tspShortestPaths.setThirdShortestPath(thirdShortestPath);
        update();
    }
    
    public static BlackBoard getInstance() {
        if (blackboardInstance == null) {
            blackboardInstance = new BlackBoard();
        }
        return blackboardInstance;
    }


}
