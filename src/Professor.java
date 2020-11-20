import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Professor implements Runnable {

	private boolean running = true;

	public void stopCompute() {
		running = false;
	}

	@Override
	public void run() {
		System.out.println("Proff running");
		Map<Integer, TspPath> startCityToTspPath = BlackBoard.getInstance().getStartCityToTsppath();
		List<TspPath> paths = new ArrayList<>();
		for(Integer startCity : startCityToTspPath.keySet()) {
			TspPath cloned = deepClone(startCityToTspPath.get(startCity));
			paths.add(cloned);
		}
		//System.out.println(paths.size());
		Collections.sort(paths);
		//System.out.println(paths);
		if(paths.size() >= 3) {
			//System.out.println("Notifying UI");
			BlackBoard.getInstance().setShortestTspPaths(paths.get(0), paths.get(1), paths.get(2));	
		}
	}

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
