import java.util.HashMap;
import java.util.Map;

public class Student implements Runnable{
	    private boolean running = false;
	    private int threadNumber, totalThreads;


	    Student(int threadNumber, int totalThreads){
	    	this.threadNumber = threadNumber;
	    	this.totalThreads = totalThreads;
	    }
	    
	    public void stopCompute() {
	        this.running = false;
	    }
	    public void startCompute() {
	        this.running = true;
	    }

	    private synchronized boolean continueCompute() {
	        return this.running == true;
	    }
	@Override
	public void run() {
		BlackBoard blackboard = BlackBoard.getInstance();
		boolean exitFlag=false;
		TspPath tspPath;
		NearestNeighbor nearestNeaighbour = new NearestNeighbor();
		int start, end, datapointsSize, div, temp, traversed=0;
    	datapointsSize = blackboard.getDatapoints().getLength();
    	div = Math.round(datapointsSize/totalThreads);
    	if (threadNumber == totalThreads) {
//    		System.out.println("this is last thread" + threadNumber);
    		end = datapointsSize;
    		start = (div * (threadNumber-1)) + 1;
    	}
    	else {
//    		System.out.println("this is not last thread" + threadNumber);
	    	end = div * threadNumber;
	    	start = end - div + 1;
    	}
		temp = start;
		
		while (traversed < datapointsSize) {
			temp = start;
			while (temp <= end) {
				if (!continueCompute()) {
					exitFlag= true;
					break;
				}
				tspPath = blackboard.getTspPathForStartCity(start);
				if (tspPath != null) {
					nearestNeaighbour.calculateNextNearestNeighbor(tspPath);
				}
				else {
					tspPath = new TspPath(temp);
					blackboard.addTspPathForStartCity(tspPath);
				}
				
				temp++;
			}
			if (exitFlag) {
				break;
			}
		}
	}

}
