import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Classroom implements Runnable {
	private int studentCount = 5;
	private List<Student> students;
	private Professor professor;

	private boolean running;

	public void run() {
		running = true;
		ExecutorService fixedPool = Executors.newFixedThreadPool(studentCount);
		try {
			while (running) {
				students = new ArrayList<>();
				List<Future> futures = new ArrayList<>();
				for (int i = 1; i <= studentCount; i++) {
					Student student = new Student(i, studentCount);
					students.add(student);
					Future f = fixedPool.submit(student);
					futures.add(f);
					
				}
				for (Future f : futures) {
					f.get();
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
				students.clear();
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




