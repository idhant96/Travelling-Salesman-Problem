import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author idhant96
 * classrom runs as a thread. Invokes student and professor threads.
 */
public class Classroom implements Runnable {
	private int studentCount = 5;
	private List<Student> students;
	private Professor professor;

	
	/**
	 * manages students and professor threads.
	 */
	public void run() {
		boolean running = true;
		ExecutorService fixedPool = Executors.newFixedThreadPool(studentCount);
		try {
			while (running) {
				students = new ArrayList<>();
				List<Future<?>> futures = new ArrayList<>();
				for (int i = 1; i <= studentCount; i++) {
					Student student = new Student(i, studentCount);
					students.add(student);
					Future<?> f = fixedPool.submit(student);
					futures.add(f);
					
				}
				for (Future<?> f : futures) {
					f.get();
				}
				professor = new Professor();
				Thread professorThread = new Thread(professor);
				professorThread.start(); 
				professorThread.join();
				boolean allStudentsCompleted = true;
				for(Student student : students) {
					if(student.continueCompute()) {
						allStudentsCompleted = false;
					}
				}
				students.clear();
				if(allStudentsCompleted) {
					running = false;
				}
			}
		} catch (Exception e) {
			System.out.println("Classroom faced an issue.");
		}
	}

	/**
	 * stops all threads in classroom when stop is clicked.
	 */
	public void stop() {
		Student student;
		if (!students.isEmpty()) {
			for (int i = 0; i < studentCount; i++) {
				student = students.get(i);
				student.stopCompute();
			}
		}
	}

	/**
	 * stops all threads in classroom when new is clicked.
	 */
	public void cleanSlate() {
		stop();
		students = null;
		professor = null;
	}
}




