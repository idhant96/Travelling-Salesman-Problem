import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int getDistance(int city1Index, int city2Index) {
        String cityKey =  Integer.toString(city1Index) + Integer.toString(city2Index);
        int shortestDistance;
        if(shortestDistancesAtoB == null) {
        	shortestDistancesAtoB = new HashMap<>();
        }
        if(shortestDistancesAtoB.containsKey(cityKey)){
            shortestDistance = shortestDistancesAtoB.get(cityKey);
        }else{
            int[] city1Coordinates= coordinates.get(city1Index);
            int[] city2Coordinates= coordinates.get(city2Index);
            shortestDistance = (int) (Math.sqrt(Math.pow((city2Coordinates[0] - city1Coordinates[0]), 2) + Math.pow((city2Coordinates[1] - city1Coordinates[1]), 2)));
            shortestDistancesAtoB.put(cityKey, shortestDistance);
        }
        return shortestDistance;

    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    public int getLength() {
    	return coordinates.size();
    }
}
