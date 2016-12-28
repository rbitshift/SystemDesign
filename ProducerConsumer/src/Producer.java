import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Producer implements Runnable {
	private List<Task> taskQueue;
	private int maxCapacity;
	private AtomicInteger count;
	
	public Producer(List<Task> taskQueue, int maxCapacity) {
		this.taskQueue = taskQueue;
		this.maxCapacity = maxCapacity;
		this.count = new AtomicInteger();
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(taskQueue) {
				try {
					while(taskQueue.size() == maxCapacity) {
						System.out.println(String.format("Queue is full for size: %d, %s going to wait..", maxCapacity, Thread.currentThread().getName()));
						
						//go to wait will consumer can consume tasks
						taskQueue.wait();
					}
					
					Thread.sleep(100);
					taskQueue.add(new Task(count.incrementAndGet()));
					System.out.println(String.format("%s produced task with value %d", Thread.currentThread().getName(), count.get()));
					
					//notify consumers that new task is added
					taskQueue.notifyAll();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}	
	}
}