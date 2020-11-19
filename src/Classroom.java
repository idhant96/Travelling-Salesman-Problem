import java.util.ArrayList;

public class Classroom {
	private int studentCount=5;
	private ArrayList<Thread> studentThreads;
	private ArrayList<Student> students;
	private Professor professor;
	
	private void prepareThreads() {
		if (studentThreads == null) {
			studentThreads = new ArrayList<Thread>();
			students = new ArrayList<Student>();
			Student student;
			for (int i=0;i<studentCount; i++) {
				student = new Student();
				students.add(student);
				studentThreads.add(new Thread(student));
			}
		}
	}
	
	
	public void start() {
		prepareThreads();
		Thread tempThread;
		Student student;
		for (int i=0;i<studentCount; i++) {
			tempThread = studentThreads.get(i);
			student = students.get(i);
			student.startCompute();
			tempThread.start();
		}
		if (professor == null) {
			professor = new Professor();
		}
		Thread professorThread = new Thread(professor);
		professorThread.run();
	}
	
//	public void pause() {
//		Thread tempThread;
//		Student student;
//		for (int i=0;i<studentCount; i++) {
//			tempThread = studentThreads.get(i);
//			student = students.get(i);
//			student.stopCompute();
//		}
//		professor.stopCompute();
//	}
	
	public void stop() {
		Thread tempThread;
		Student student;
		for (int i=0;i<studentCount; i++) {
			tempThread = studentThreads.get(i);
			student = students.get(i);
			student.stopCompute();
			student = null;
		}
		studentThreads = null;
		professor.stopCompute();
		professor = null;
	}
	
	
	
//	public static void main(String arp[]) {
//		Classroom classroom = new Classroom();
//		classroom.start();
//		try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//		classroom.stop();
//		
//		try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//		classroom.start(); // works 
//		
//	}
	
}


 