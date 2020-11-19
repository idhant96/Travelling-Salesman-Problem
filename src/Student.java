

public class Student implements Runnable{
	    private boolean running = true;
	    private int threadNumber, totalThreads;


	    Student(int threadNumber, int totalThreads){
	    	this.threadNumber = threadNumber;
	    	this.totalThreads = totalThreads;
	    }
	    
	    public void stopCompute() {
	        this.running = false;
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
				System.out.println("Thread number " + threadNumber + "city " + temp);
				if (!continueCompute()) {
					exitFlag= true;
					break;
				}
				tspPath = blackboard.getTspPathForStartCity(start);
				if(tspPath == null) {
					tspPath = new TspPath(temp);
					tspPath.addCityToPath(temp);
					blackboard.addTspPathForStartCity(tspPath);
				}
				nearestNeaighbour.calculateNextNearestNeighbor(tspPath);
				System.out.println(tspPath.getPath());
				temp++;
				
//				try {
//					Thread.sleep(1000);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
			
			}
			blackboard.setStudentRunStatus(threadNumber, false);
			while (continueCompute()  && !blackboard.getRunStatusForStudent(threadNumber)){
//				System.out.println("Student going to sleep for " + threadNumber);
//				try {
//					Thread.sleep(5000);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
			}
			if (exitFlag) {
				break;
			}
			traversed++;
		}
	}

}
