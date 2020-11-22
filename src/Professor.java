import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Professor implements Runnable {
	@Override
	public void run() {
		Map<Integer, TspPath> startCityToTspPath = BlackBoard.getInstance().getStartCityToTsppath();
		List<TspPath> paths = new ArrayList<>();
		for(Integer startCity : startCityToTspPath.keySet()) {
			paths.add(startCityToTspPath.get(startCity));
		}
		Collections.sort(paths);
		if(paths.size() >= 3) {
			BlackBoard.getInstance().setShortestTspPaths(paths.get(0), paths.get(1), paths.get(2));	
		}
	}
}
