import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * Runnable class for 3 shorted paths after every nearest neighbour in each thread is calculated.
 *
 */
public class Professor implements Runnable {

	/**
	 * Invokes TspPath sort  and stores three shortest paths.
	 */
	@Override
	public void run() {
		Map<Integer, TspPath> startCityToTspPath = BlackBoard.getInstance().getStartCityToTsppath();
		List<TspPath> paths = new ArrayList<>();
		for(Integer startCity : startCityToTspPath.keySet()) {
			TspPath cloned = deepClone(startCityToTspPath.get(startCity));
			paths.add(cloned);
		}
		Collections.sort(paths);
		if(paths.size() >= 3) {
			BlackBoard.getInstance().setShortestTspPaths(paths.get(0), paths.get(1), paths.get(2));	
		}
	}

	/**
	 * CLones the TspPath map from Blackboard to avoid 
	 * @param tspPath
	 * @return
	 */
	public static TspPath deepClone(TspPath tspPath) {
		TspPath clone = new TspPath(tspPath.getStartCity());
		clone.setTotalDistance(tspPath.getTotalDistance());
		List<Integer> path = new ArrayList<>(tspPath.getPath());
		for(int city : path) {
			clone.addCityToPath(city);
		}
		return clone;
	}
}
