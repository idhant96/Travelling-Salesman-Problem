import java.util.ArrayList;
import java.util.List;

public class Classroom implements Runnable {
	private int studentCount = 5;
	private List<Student> students;
	private Professor professor;

	private boolean running;

	public void run() {
		running = true;
		try {
			while (running) {
				System.out.println("---------------------");
				students = new ArrayList<>();
				List<Thread> threads = new ArrayList<>();
				for (int i = 1; i <= studentCount; i++) {
					Student student = new Student(i, studentCount);
					students.add(student);
					Thread studentThread = new Thread(student);
					threads.add(studentThread);
					studentThread.start();
				}
				for (Thread t : threads) {
					t.join();
				}
				System.out.println("Going to start prof");
				professor = new Professor();
				Thread professorThread = new Thread(professor);
				professorThread.start(); 
				professorThread.join();
				boolean allCompleted = true;
				for(Student student : students) {
					if(student.isRunning()) {
						allCompleted = false;
					}
				}
				if(allCompleted) {
					running = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//		if(students == null) {
//			students = new ArrayList<>();
//		}
//		if (students.isEmpty()) {
//			students = new ArrayList<Student>();
//			Student student;
//			for (int i=1;i<=studentCount; i++) {
//				student = new Student(i, studentCount);
//				students.add(student);
//				Thread studentThread = new Thread(student);
//				studentThread.start();
//			}
//		}
//		if (professor == null) {
//			professor = new Professor();
//		}
//		Thread professorThread = new Thread(professor);
//		professorThread.start(); 
//
//	}

	public void stop() {
		Thread tempThread;
		Student student;
		if (!students.isEmpty()) {
			for (int i = 0; i < studentCount; i++) {
				student = students.get(i);
				student.stopCompute();
			}
		}
		students.clear();
		professor.stopCompute();
		professor = null;
	}

	public void cleanSlate() {
		stop();
	}

}
