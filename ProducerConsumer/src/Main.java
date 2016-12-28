import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		List<Task> taskQueue = new ArrayList<Task>();
		Thread producer1 = new Thread(new Producer(taskQueue, 10), "Producer-1");
		Thread producer2 = new Thread(new Producer(taskQueue, 10), "Producer-2");
		
		Thread consumer1 = new Thread(new Consumer(taskQueue), "Consumer-1");
		Thread consumer2 = new Thread(new Consumer(taskQueue), "Consumer-2");
		Thread consumer3 = new Thread(new Consumer(taskQueue), "Consumer-3");
		
		
		producer1.start();
		producer2.start();
		
		consumer1.start();
		consumer2.start();
		consumer3.start();
	}

}