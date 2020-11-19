import java.util.ArrayList;

public class Classroom {
	private int studentCount=5;
	private ArrayList<Student> students;
	private Professor professor;

	public void start() {
		if(students == null) {
			students = new ArrayList<>();
		}
		if (students.isEmpty()) {
			students = new ArrayList<Student>();
			Student student;
			for (int i=1;i<=studentCount; i++) {
				student = new Student(i, studentCount);
				students.add(student);
				Thread studentThread = new Thread(student);
				studentThread.start();
			}
		}
		if (professor == null) {
			professor = new Professor();
		}
		Thread professorThread = new Thread(professor);
		professorThread.start(); 
		
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
	
	public void cleanSlate() {
		stop();
	}
	
	
	
	
}


 