import java.util.ArrayList;
import java.util.List;

public class TspPath implements Comparable<TspPath>{
	private List<Integer> path;
	private int totalDistance;
	private int startCity;
	
	TspPath(int startCity){
		this.startCity = startCity;
		this.path = new ArrayList<Integer>();;
		this.totalDistance = 0;
	}
	
	public List<Integer> getPath() {
		return path;
	}
	
	public void addCityToPath(int point) {
		this.path.add(point);
	}

	public void setTotalDistance(int totalDistance){
		this.totalDistance = totalDistance;
	}
	
	public void addDistanceToTotalDistance(int increment) {
		this.totalDistance += increment;
	}
	
	public int getTotalDistance() {
		return totalDistance;
	}
	
	public int getStartCity() {
		return startCity;
	}

	@Override
	public int compareTo(TspPath o) {
		return Integer.compare(this.totalDistance, o.totalDistance);
	}
	
	
	public String toString() {
		return "TotalDistance: " + totalDistance + " ["+ path.size() + "] " + path.toString();
	}

}
