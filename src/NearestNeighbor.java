import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighbor {
	
	
	public void calculateNextNearestNeighbor(TspPath tspPath) {
		
		DataPoints dataPoints = BlackBoard.getInstance().getDatapoints();
		Set<Integer> visited = new HashSet<>(tspPath.getPath());
		int lastVisited = tspPath.getPath().get(tspPath.getPath().size()-1);
		
		int minDistance = Integer.MAX_VALUE;
		int minCity = Integer.MAX_VALUE;
		for(int i = 0 ; i < dataPoints.coordinates.length; i++) {
			if(visited.contains(i)) {
				continue;
			}
		}
		
		
	}

}
