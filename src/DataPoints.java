import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * An element of Blackboard which stores the data points for the TSP to be computed on.
 *
 */
public class DataPoints {
    List<int[]> coordinates;
    Map<String, Integer> shortestDistancesAtoB;
    
    DataPoints(){
    	coordinates = new ArrayList<int[]>();
    	shortestDistancesAtoB = new HashMap<String, Integer>();
    }

    public void addCityCoordinates(int[] coordinate) {
        coordinates.add(coordinate);
    }
/**
 * calculates distance between two nodes.
 * @param city1Index
 * @param city2Index
 * 
 */
    public int getDistance(int city1Index, int city2Index) {
            int[] city1Coordinates= coordinates.get(city1Index);
            int[] city2Coordinates= coordinates.get(city2Index);
            int shortestDistance = (int) (Math.sqrt(Math.pow((city2Coordinates[0] - city1Coordinates[0]), 2) + Math.pow((city2Coordinates[1] - city1Coordinates[1]), 2)));
        return shortestDistance;

    }

    /**
     * 
     * @return coordinates
     */
    public List<int[]> getCoordinates() {
        return coordinates;
    }

    /**
     * Number of coordinates in DataPoints
     * @return
     */
    public int getLength() {
    	return coordinates.size();
    }
}
