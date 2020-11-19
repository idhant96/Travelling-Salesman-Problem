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

    public void addCoordinatesToDataPoints(int[] coordinate) {
        datapoints.addCityCoordinates(coordinate);
    }

    public DataPoints getDatapoints() {
        return datapoints;
    }

    public void getTspShortestPaths() {

    }

    public void cleanSlate() {
        datapoints = null;
        tspShortestPaths = null;


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
        if (blackboardInstance == null) {
            blackboardInstance = new BlackBoard();
        }
        return blackboardInstance;
    }

}
