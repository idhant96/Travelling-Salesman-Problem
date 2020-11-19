import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TspDataHandler {

    public void loadDataPointsFromFile(String filePath) {
        String rawTspData = readDataFromPath(filePath);
        List<int[]> processedTspData = processSymmetricData(rawTspData);
        BlackBoard.getBlackBoardInstance().datapoints.coordinates = processedTspData;

    }


    public String readDataFromPath(String path) {
        // This the module to read the data from the file specified in the path.
        String data = "";
        String dimension = "";
        String currentLine = "";
        String[] tempSplitArray;
        boolean dataStart = false;
        try {
            File myObj = new File(path);
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

    public List<int[]> processSymmetricData(String data) {
        String[] splitData = data.split("xxx");
        String temp = "";
        int dimension = Integer.parseInt(splitData[1]);
        List<int[]> coordinates = new ArrayList<int[]>();

        String[] currentCoordinate;
        for (int i = 2; i < splitData.length; i++) {
            int[] coordinateCurrent = new int[2];
            if (!splitData[i].contains("EOF")) {
                currentCoordinate = splitData[i].split(" ");
                coordinateCurrent[0] = Integer.parseInt(currentCoordinate[1]);
                coordinateCurrent[1] = Integer.parseInt(currentCoordinate[2]);
                coordinates.add(coordinateCurrent);
            }
        }
        return coordinates;

    }

    public void addCoordinatesToDataPoints() {

    }

    public void normalizeCoordinates() {

    }
}
