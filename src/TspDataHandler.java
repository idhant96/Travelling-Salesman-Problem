import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TspDataHandler {

    public void loadDataPointsFromFile(File fileObj, int screenHeight, int screenWidth) {
        String rawTspData = readDataFromPath(fileObj);
        List<int[]> processedTspData = processSymmetricData(rawTspData);
        List<int[]> scaledTspData = normalizeCoordinates(processedTspData, screenHeight, screenWidth);

        for (int[] coordinate : scaledTspData) {
            addCoordinatesToDataPoints(coordinate[0], coordinate[1]);
        }
    }


    public void saveFile(File file) {
        DataPoints dataPoints = BlackBoard.getInstance().getDatapoints();
        if(dataPoints == null) {
        	dataPoints = new DataPoints();
        }
        String fileContent = formatFileSaveContent(dataPoints.coordinates);
        System.out.println("file content is " + fileContent);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fileContent);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String formatFileSaveContent(List<int[]> coordinates){
        String content = "";
        int index = 0;

        for (int[] coordinate : coordinates) {
            index += 1;
            content += Integer.toString(index) + " " + Integer.toString(coordinate[0]) + " " + Integer.toString(coordinate[1]) + "\n";
        }
        return content;
    }


    public List<int[]> normalizeCoordinates(List<int[]> processedTspData, int screenHeight, int screenWidth) {
        int minX = Integer.MAX_VALUE;
        int maxX = -1;
        int minY = Integer.MAX_VALUE;
        int maxY = -1;
        int xRange;
        int yRange;
        float xScaleFactor;
        float yScaleFactor;
        List<int[]> scaledTspInput = new ArrayList<int[]>();

        for (int[] coordinate : processedTspData) {
            if (coordinate[0] > maxX) {
                maxX = coordinate[0];
            }
            if (coordinate[0] < minX) {
                minX = coordinate[0];
            }
        }

        for (int[] coordinate : processedTspData) {
            if (coordinate[1] > maxY) {
                maxY = coordinate[1];
            }
            if (coordinate[1] < minY) {
                minY = coordinate[1];
            }
        }

        xRange = maxX - minX;
        yRange = maxY - minY;
        xScaleFactor = (float)screenWidth/xRange;
        yScaleFactor = (float)screenHeight/yRange;
//        System.out.println("xscale factor: "+xScaleFactor);
//        System.out.println("y scale factor: "+yScaleFactor);
//        System.out.println("x range : "+xRange);
//        System.out.println("yRange: "+yRange);


        for (int[] coordinate : processedTspData) {
            int[] scaledCoordinate = new int[2];
            scaledCoordinate[0] = (int) Math.ceil ((coordinate[0] - minX) * xScaleFactor);
            scaledCoordinate[1] = (int) Math.ceil ((coordinate[1] - minY) * yScaleFactor);
            scaledTspInput.add(scaledCoordinate);
        }

        return scaledTspInput;

    }

    public String readDataFromPath(File fileObj) {
        // This the module to read the data from the file specified in the path.
        String data = "";
        String dimension = "";
        String currentLine = "";
        String[] tempSplitArray;
        boolean dataStart = false;
        try {
            File myObj = fileObj;
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                currentLine = myReader.nextLine();
                // The string "xxx" is used as an delimiter for the symmetrical
                // Data

                if (dataStart) {
                    data += currentLine + "xxx";
                }
                // We use the header information to extract the dimension from
                // the file
                // We also use the header information to identify the start of
                // the actual data
                if (currentLine.contains("NODE_COORD_SECTION") || currentLine.contains("1 ")) {
                    dataStart = true;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // The data is passed on to the Data Processing Module in the form of a
        // String for processing.
        return data;

    }


//    public String readDataFromPath(String path) {
//        // This the module to read the data from the file specified in the path.
//        String data = "";
//        String dimension = "";
//        String currentLine = "";
//        String[] tempSplitArray;
//        boolean dataStart = false;
//        try {
//            File myObj = new File(path);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                currentLine = myReader.nextLine();
//                // The string "xxx" is used as an delimiter for the symmetrical
//                // Data
//
//                if (dataStart) {
//                    data += currentLine + "xxx";
//                }
//                // We use the header information to extract the dimension from
//                // the file
//                // We also use the header information to identify the start of
//                // the actual data
//                if (currentLine.contains("NODE_COORD_SECTION") || currentLine.contains("1 ")) {
//                    dataStart = true;
//                }
//
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        // The data is passed on to the Data Processing Module in the form of a
//        // String for processing.
//        return data;
//
//    }

    public List<int[]> processSymmetricData(String data) {
        String[] splitData = data.split("xxx");
        String temp = "";
        List<int[]> coordinates = new ArrayList<int[]>();

        String[] currentCoordinate;
        for (int i = 0; i < splitData.length; i++) {
            int[] coordinateCurrent = new int[2];
            if (!splitData[i].contains("EOF")) {
                currentCoordinate = splitData[i].split(" ");
                coordinateCurrent[0] = (int) Float.parseFloat(currentCoordinate[1]);
                coordinateCurrent[1] = (int) Float.parseFloat(currentCoordinate[2]);
                coordinates.add(coordinateCurrent);
            }
        }
        return coordinates;

    }

    public void addCoordinatesToDataPoints(int xCoordinate, int yCoordinate) {
//    	System.out.println("Adding coordinate points " + xCoordinate + " " + yCoordinate);
        int[] coordinate = new int[]{xCoordinate, yCoordinate};
        BlackBoard.getInstance().addCoordinatesToDataPoints(coordinate);
    }

    public List<int[]> getCoordinatesDataPoints() {
    	DataPoints dataPoints = BlackBoard.getInstance().getDatapoints();
    	if(dataPoints == null) {
    		return null;
    	}
        return dataPoints.coordinates;
    }


}
