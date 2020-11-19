import java.util.ArrayList;

public class Classroom {
	private int studentCount=5;
//	private ArrayList<Thread> studentThreads; 
	private ArrayList<Student> students;
	private Professor professor;
//	private Thread professorThread;
	
//	private void prepareThreads() {
//		if (studentThreads == null) {
//			studentThreads = new ArrayList<Thread>();
//			students = new ArrayList<Student>();
//			Student student;
//			for (int i=1;i<=studentCount; i++) {
//				student = new Student(i, studentCount);
//				students.add(student);
//				studentThreads.add(new Thread(student));
//			}
//		}
//		if (professorThread == null) {
//			professor = new Professor();
//			professorThread = new Thread(professor);
//		} 
//	}
	
	
	public void start() {
//		prepareThreads();
		Thread tempThread;
		if (students.isEmpty()) {
//			studentThreads = new ArrayList<Thread>();
			students = new ArrayList<Student>();
			Student student;
			for (int i=1;i<=studentCount; i++) {
				student = new Student(i, studentCount);
				students.add(student);
				tempThread = new Thread(student);
				tempThread.start();
//				studentThreads.add(new Thread(student));
			}
		}
		if (professor == null) {
			professor = new Professor();
			tempThread = new Thread(professor);
			tempThread.start();
		} 
		
//		Student student;
//		for (int i=0;i<studentCount; i++) {
//			tempThread = studentThreads.get(i);
//			student = students.get(i);
//			student.startCompute();
//			tempThread.start();
//		}
//		professor.startCompute();
//		professorThread.start();
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
		if (!students.isEmpty()) {
			for (int i=0;i<studentCount; i++) {
				student = students.get(i);
				student.stopCompute();
//				student = null;
			}
		}
		
		students.clear();
//		professor.stopCompute();
//		professor = null;
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
////		try {
////            Thread.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////		classroom.start(); // works 
//		
//	}
	
}


 