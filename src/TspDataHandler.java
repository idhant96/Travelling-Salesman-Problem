import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TspDataHandler {

	/**
	 * stores the coordinates present in the input file.
	 * @param fileObj
	 * @param screenHeight
	 * @param screenWidth
	 */
	public void loadDataPointsFromFile(File fileObj, int screenHeight, int screenWidth) {
		String rawTspData = "";
		try {
			rawTspData = readDataFromPath(fileObj);
		} catch(Exception e) {
			System.out.println("Error while reading file: " + fileObj);
			System.out.println("Kindly check if file is in TSP Format");
		}
		List<int[]> processedTspData = processSymmetricData(rawTspData);
		List<int[]> scaledTspData = normalizeCoordinates(processedTspData, screenHeight, screenWidth);

		for (int[] coordinate : scaledTspData) {
			addCoordinatesToDataPoints(coordinate[0], coordinate[1]);
		}
	}
/**
 * saves the coordinates in a file.
 * @param file
 */
	public void saveFile(File file) {
		DataPoints dataPoints = BlackBoard.getInstance().getDatapoints();
		if (dataPoints == null) {
			dataPoints = new DataPoints();
		}
		String fileContent = formatFileSaveContent(dataPoints.coordinates);
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
	
/**
 * formats for saving in file.
 * @param coordinates
 * @return
 */
	public String formatFileSaveContent(List<int[]> coordinates) {
		String content = "";
		int index = 0;

		for (int[] coordinate : coordinates) {
			index += 1;
			content += Integer.toString(index) + " " + Integer.toString(coordinate[0]) + " "
					+ Integer.toString(coordinate[1]) + "\n";
		}
		return content;
	}

	/**
	 * normalizes the input coordinates to coordinates which can be fit into the UI panel.
	 * @param processedTspData
	 * @param screenHeight
	 * @param screenWidth
	 * @return
	 */
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
		xScaleFactor = (float) screenWidth / xRange;
		yScaleFactor = (float) screenHeight / yRange;

		for (int[] coordinate : processedTspData) {
			int[] scaledCoordinate = new int[2];
			scaledCoordinate[0] = (int) Math.ceil((coordinate[0] - minX) * xScaleFactor);
			scaledCoordinate[1] = (int) Math.ceil((coordinate[1] - minY) * yScaleFactor);
			scaledTspInput.add(scaledCoordinate);
		}

		return scaledTspInput;

	}


	/**
	 * Reads data from given file and returns as String
	 * @param fileObj
	 * @return data from file as String
	 */
	public String readDataFromPath(File fileObj) {
		String data = "";
		String currentLine = "";
		boolean dataStart = false;
		try {
			File myObj = fileObj;
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				currentLine = myReader.nextLine();
				if (dataStart) {
					data += currentLine + "xxx";
				}
				if (currentLine.contains("NODE_COORD_SECTION") || currentLine.contains("1 ")) {
					dataStart = true;
				}

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;

	}

	/**
	 * Store the read data into coordinates datapoints.
	 * @param data
	 * @return
	 */
	public List<int[]> processSymmetricData(String data) {
		String[] splitData = data.split("xxx");
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

	/**
	 * Adds the coordinates to datapoints inside Blackboard.
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public void addCoordinatesToDataPoints(int xCoordinate, int yCoordinate) {
		int[] coordinate = new int[] { xCoordinate, yCoordinate };
		BlackBoard.getInstance().addCoordinatesToDataPoints(coordinate);
	}

	/**
	 * Fetch datapoints from Blackboard.
	 * @return
	 */
	public List<int[]> getCoordinatesDataPoints() {
		DataPoints dataPoints = BlackBoard.getInstance().getDatapoints();
		if (dataPoints == null) {
			return null;
		}
		return dataPoints.coordinates;
	}

}
