package fi.teras.threads;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class ThreadHandler implements ThreadProvider {
	final static Logger logger = Logger.getLogger(ThreadHandler.class);
	
	/**
	 * Thread pool executor instance which is responsible of executing the tasks in the taskRepository
	 */
	private ExecutorService executor;
	
	/**
	 * Repository of the loaded tasks. One or all the tasks can be executed by calling methods runTask() or runAll()
	 */
	private ArrayList<ThreadTask> taskRepository;
	
	/**
	 * Milliseconds to sleep between task executing
	 */
	private final long sleepBetweenThreads = 500;
	
	public ThreadHandler() {
		this.executor  = Executors.newCachedThreadPool();
		this.taskRepository = new ArrayList<ThreadTask>();
	}
	
	public void addTask(ThreadTask task) {
		this.taskRepository.add(task);
		ThreadHandler.logger.info("Task added to the repository (currently task count " + this.taskRepository.size() + ": " + task);
	}

	public void deleteTask(UUID taskId) {
		if (this.taskRepository.size() == 0) {
			ThreadHandler.logger.warn("Thead repository have not threads to remove");
		}
		else {
			for (int i = 0; i < this.taskRepository.size(); i++) {
				ThreadTask task = this.taskRepository.get(i);
				if (task.getId().equals(taskId)) {
					this.taskRepository.remove(i);
					ThreadHandler.logger.info("thread id " + taskId + " removed");
					break;
				}
			}
		}
	}
	
	/**
	 * Run all the tasks
	 */
	public void runAll() {
		ThreadHandler.logger.info("Running all " + this.taskRepository.size() + " tasks...");
		this.runTask(new Object());
	}
	
	/**
	 * Run single task
	 * @param id
	 */
	public void runTask(UUID id) {
		this.runTask(id);
	}
	
	/**
	 * Run single task
	 * @param id if type is UUID, trying to find significant task, otherwise run all the tasks on the repo
	 */
	protected void runTask(Object id) {
		UUID taskIdToFind = null;
		
		if (id instanceof UUID) {
			taskIdToFind = (UUID)id;
		}
		
		for (ThreadTask task : this.taskRepository) {
			if (taskIdToFind == null || taskIdToFind.equals(task.getId())) {
				this.executor.execute(task);
				try {
					Thread.sleep(this.sleepBetweenThreads);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		executor.shutdown();
		while (!executor.isTerminated()) {
			// waiting for all threads executing...
		}
		
		ThreadHandler.logger.info("all threads finished");
	}

	public ArrayList<ThreadTask> getTasks() {
		return this.taskRepository;
	}

	public ThreadTask getTask(String id) {
		UUID idToSearch = UUID.fromString(id);
		for (ThreadTask task : this.getTasks()) {
			if (task.getId().equals(idToSearch)) {
				return task;
			}
		}
		return null;
	}
}