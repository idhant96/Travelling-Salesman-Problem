
/**
 * @author idhant96
 * Student calculates next nearest neighbor and ends the thread.
 */
public class Student implements Runnable {
    private boolean running = true;
    private int threadNumber, totalThreads;

    Student(int threadNumber, int totalThreads) {
        this.threadNumber = threadNumber;
        this.totalThreads = totalThreads;
    }

    /**
     * Sets running status of student to false
     */
    public void stopCompute() {
        this.running = false;
    }

    /**
     * Sets running status of student to true
     */
    public boolean continueCompute() {
        return running;
    }

    /**
     * Calls calculateNextNearestNeighbor function to get next node.
     */
    @Override
    public void run() {
        BlackBoard blackboard = BlackBoard.getInstance();
        TspPath tspPath;
        NearestNeighbor nearestNeighbour = new NearestNeighbor();
        int start, end, datapointsSize, div, temp;
        datapointsSize = blackboard.getDatapoints().getLength();
        div = Math.round(datapointsSize / totalThreads);
        if (threadNumber == totalThreads) {
            end = datapointsSize;
            start = (div * (threadNumber - 1)) + 1;
        } else {
            end = div * threadNumber;
            start = end - div + 1;
        }
        start = start - 1;
        end = end - 1;
        temp = start;
        TspPath lastTspPath = null;
        while (temp <= end) {
            if (!continueCompute()) {
                break;
            }
            tspPath = blackboard.getTspPathForStartCity(temp);
            if (tspPath == null) {
                tspPath = new TspPath(temp);
                tspPath.addCityToPath(temp);
                blackboard.addTspPathForStartCity(tspPath);
            }
            lastTspPath = tspPath;
            if (tspPath.getPath().size() > datapointsSize) {
                continue;
            }
            nearestNeighbour.calculateNextNearestNeighbor(tspPath);
            temp++;

        }
        if (lastTspPath == null || lastTspPath.getPath().size() > datapointsSize) {
            running = false;
        }
    }

}
