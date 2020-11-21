
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

    private boolean continueCompute() {
        return this.running == true;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        BlackBoard blackboard = BlackBoard.getInstance();
        TspPath tspPath;

        NearestNeighbor nearestNeighbour = new NearestNeighbor();
        int start, end, datapointsSize, div, temp;
        datapointsSize = blackboard.getDatapoints().getLength();
        System.out.println("dp size : " + datapointsSize);
        div = Math.round(datapointsSize / totalThreads);
        if (threadNumber == totalThreads) {
//    		System.out.println("this is last thread" + threadNumber);
            end = datapointsSize;
            start = (div * (threadNumber - 1)) + 1;
        } else {
//    		System.out.println("this is not last thread" + threadNumber);
            end = div * threadNumber;
            start = end - div + 1;
        }
        start = start - 1;
        end = end - 1;
        System.out.println("Student thread number: " + this.threadNumber);
        System.out.println("start city index: " + start);
        System.out.println("end city index: " + end);
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
            System.out.println("Thread running is " + threadNumber);
            lastTspPath = tspPath;
            if (tspPath.getPath().size() > datapointsSize) {
                continue;
            }
            nearestNeighbour.calculateNextNearestNeighbor(tspPath);
            temp++;

        }
//        try {
//            Thread.sleep(100);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (lastTspPath == null || lastTspPath.getPath().size() > datapointsSize) {
            System.out.println("Setting running to false for " + threadNumber);
            for (temp = start; temp <= end; temp++) {
                System.out.println(blackboard.getTspPathForStartCity(temp));
            }
            running = false;
        }

    }

}
