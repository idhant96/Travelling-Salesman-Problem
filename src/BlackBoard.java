import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class BlackBoard extends Observable {
    private DataPoints datapoints;
    private TspShortestPaths tspShortestPaths;
    private Map<Integer, TspPath> startCityToTspPath;
    private static BlackBoard blackboardInstance;
    private Map<Integer, Boolean> studentRunStatus;

    private BlackBoard() {
    }

    public void addCoordinatesToDataPoints(int[] coordinate) {
    	if(datapoints == null) {
    		datapoints = new DataPoints();
    	}
        datapoints.addCityCoordinates(coordinate);
        update();
    }

    public DataPoints getDatapoints() {
        return datapoints;
    }

    public TspShortestPaths getTspShortestPaths() {
        return tspShortestPaths;
    }

    public void cleanSlate() {
        datapoints = null;
        tspShortestPaths = null;

    }

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

    public void addTspPathForStartCity( TspPath tspPathObj) {
        if (startCityToTspPath == null) {
            startCityToTspPath = new HashMap<>();
        }
        startCityToTspPath.put(tspPathObj.getStartCity(), tspPathObj);
    }

    public List<TspPath> getTspPaths() {
        return null;

    }


    public void update() {
        setChanged();
        notifyObservers();
    }

    public Map<Integer, TspPath> getStartCityToTsppath() {
        return startCityToTspPath;
    }

    public TspShortestPaths getTspShortestPath() {
        if (tspShortestPaths == null) {
            tspShortestPaths = new TspShortestPaths();
        }
        return tspShortestPaths;
    }

    public void setShortestTspPaths(TspPath firstShortestPath, TspPath secondShortestPath, TspPath thirdShortestPath) {
        TspShortestPaths tspShortestPaths = getTspShortestPath();
        tspShortestPaths.setFirstShortestPath(firstShortestPath);
        tspShortestPaths.setSecondShortestPath(secondShortestPath);
        tspShortestPaths.setThirdShortestPath(thirdShortestPath);
        update();
    }

    public boolean getRunStatusForStudent(int studentNumber) {
    	if(studentRunStatus == null) {
    		studentRunStatus = new HashMap<>();
    	}
    	if(studentRunStatus.containsKey(studentNumber)){
    		return studentRunStatus.get(studentNumber);
    	} else {
    		studentRunStatus.put(studentNumber, true);
    		return true;
    	}
    }
    
    public boolean canProfessorRun() {
    	for(Integer studentNumber : studentRunStatus.keySet()) {
    		if(studentRunStatus.get(studentNumber)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public void setStudentRunStatus(int studentNumber, boolean status) {
    	studentRunStatus.put(studentNumber, status);
    }
    
    public void setAllStudentStatusToTrue() {
    	for(Integer studentNumber : studentRunStatus.keySet()) {
    		studentRunStatus.put(studentNumber, true);
    	}
    }
    
    public static BlackBoard getInstance() {
        if (blackboardInstance == null) {
            blackboardInstance = new BlackBoard();
        }
        return blackboardInstance;
    }


}
