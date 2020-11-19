import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighbor {
	
	
	public void calculateNextNearestNeighbor(TspPath tspPath) {
		DataPoints dataPoints = BlackBoard.getInstance().getDatapoints();
		List<Integer> path = tspPath.getPath();
		Set<Integer> visited = new HashSet<>(path);
		int lastVisited = path.get(path.size()-1);

		int minDistance = Integer.MAX_VALUE;
		int minCity = Integer.MAX_VALUE;
		for(int i = 0 ; i < dataPoints.getLength(); i++) {
			if(visited.contains(i)) {
				continue;
			}
			int distance = dataPoints.getDistance(lastVisited, i);
			if(distance < minDistance ) {
				minDistance = distance;
				minCity = i;
			}
		}
		tspPath.addCityToPath(minCity);
		tspPath.addDistanceToTotalDistance(minDistance);
	}
}
