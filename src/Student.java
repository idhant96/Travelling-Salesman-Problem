import java.util.HashMap;
import java.util.Map;

public class Student implements Runnable{
	    private boolean running = false;
//	    private BlackBoard blackboard;
//	    private Map<Integer, TspPath> path;
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
		int start, end, datapointsSize, div;
		Map<Integer, TspPath> path;
		BlackBoard blackboard;
		blackboard = BlackBoard.getInstance();
//    	datapointsSize = blackboard.getDatapointsSize();
		datapointsSize = 100;
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
//		int temp = start;
//		TspPath tspPath;
//		path = blackboard.startCityToTsppath;
//		/**
//		 * will mod once blackboard ready
//		 */
//		while (continueCompute()) {
//			tspPath = blackboard.getTspPathForStartCity();
//			if (tspPath == null) {
//				
//			}
//			while (start <= ) {
////				System.out.println(start); 
////				start +=1;
//			}
////			break;
//		}
	}

}
