import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class BlackBoard extends Observable {
    DataPoints datapoints;
    TspShortestPaths tspShortestPaths;
    Map<Integer, TspPath> startToTsppath = new HashMap<Integer, TspPath>();

    private BlackBoard() {

    }

    public BlackBoard getBlackBoardInstance() {
        return null;
    }

    public void addCoordinatesToDataPoints() {

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


}
