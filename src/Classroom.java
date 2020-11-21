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

	public void stop() {
		Student student;
		if (!students.isEmpty()) {
			for (int i = 0; i < studentCount; i++) {
				student = students.get(i);
				student.stopCompute();
			}
		}
	}

	public void cleanSlate() {
		stop();
		students = null;
		professor = null;
	}

}
