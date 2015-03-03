package fi.teras.threads;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Thread provider which can manage dynamic amount of thrad tasks
 * @author Teras
 */
public interface ThreadProvider {
	
	/**
	 * Add new task to task repository
	 * @param task
	 */
	void addTask(ThreadTask task);
	
	/**
	 * Get all the tasks
	 * @return
	 */
	ArrayList<ThreadTask> getTasks();
	
	/**
	 * Delete some task from repository
	 * @param taskId
	 */
	void deleteTask(UUID taskId);
	
	/**
	 * Run all the loaded threads
	 */
	void runAll();
	
	/**
	 * Gets significant task from repository
	 * @param id
	 * @return
	 */
	ThreadTask getTask(String id);
}