import java.util.ArrayList;

public class Classroom {
	private int studentCount=5;
	private ArrayList<Student> students;
	private Professor professor;

	public void start() {
		Thread tempThread;
		if (students.isEmpty()) {
			students = new ArrayList<Student>();
			Student student;
			for (int i=1;i<=studentCount; i++) {
				student = new Student(i, studentCount);
				students.add(student);
				tempThread = new Thread(student);
				tempThread.start();
			}
		}
		if (professor == null) {
			professor = new Professor();
		}
		tempThread = new Thread(professor);
		tempThread.run(); 
		
	}
	

	
	public void stop() {
		Thread tempThread;
		Student student;
		if (!students.isEmpty()) {
			for (int i=0;i<studentCount; i++) {
				student = students.get(i);
				student.stopCompute();			}
		}
		
		students.clear();
		professor.stopCompute();
		professor = null;
	}
	
	
	
	
}


 