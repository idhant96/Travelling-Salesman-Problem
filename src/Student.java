
public class Student implements Runnable {
	private boolean running = true;
	private int threadNumber, totalThreads;

	Student(int threadNumber, int totalThreads) {
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
		TspPath tspPath;

		NearestNeighbor nearestNeaighbour = new NearestNeighbor();
		int start, end, datapointsSize, div, temp;
		datapointsSize = blackboard.getDatapoints().getLength();
		div = Math.round(datapointsSize / totalThreads);
		if (threadNumber == totalThreads-1) {
//    		System.out.println("this is last thread" + threadNumber);
			end = datapointsSize;
			start = (div * (threadNumber - 1)) + 1;
		} else {
//    		System.out.println("this is not last thread" + threadNumber);
			end = div * threadNumber;
			start = end - div + 1;
		}
		start += 1;
		end -= 1;
		temp = start;
		System.out.println("Thread number " + threadNumber + " start: " + start + " end: " + end);
		while (temp <= end) {
//			System.out.println("Thread number " + threadNumber + " CurrCity: " + temp);
			if (!continueCompute()) {
				break;
			}
			tspPath = blackboard.getTspPathForStartCity(temp);
			if (tspPath == null) {
				tspPath = new TspPath(temp);
				tspPath.addCityToPath(temp);
				blackboard.addTspPathForStartCity(tspPath);
			}
			nearestNeaighbour.calculateNextNearestNeighbor(tspPath);
			temp++;
			try {
				Thread.sleep(100);
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
	}

}
