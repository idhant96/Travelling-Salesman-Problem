
public class Main {
	
	
	public static void main(String[] args) {
		TspFrame frame = new TspFrame();
		Classroom classroom = new Classroom();
		TspController.getInstance().addClassRoom(classroom);
	}

}
