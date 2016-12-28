import java.util.List;


public class Consumer implements Runnable {
	private List<Task> taskQueue;
	
	public Consumer(List<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(taskQueue) {
				try {
					while(taskQueue.size() == 0) {
						System.out.println(String.format("Queue is empty, %s going to wait..", Thread.currentThread().getName()));
						taskQueue.wait();
					}
					
					Thread.sleep(100);
					Task currentTask = taskQueue.remove(0);
					System.out.println(String.format("%s consumed task with value %d", Thread.currentThread().getName(), currentTask.getId()));
					
					//update the producer that task is consumed
					taskQueue.notifyAll();
					
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}

			}
		}
	}
}