package fi.teras.threads;

import org.apache.log4j.Logger;

/**
 * A single thread task
 * @author Teras
 *
 */
public abstract class ThreadTask extends GenericThreadTask implements Runnable {
	protected Thread thread;
	protected final static Logger logger = Logger.getLogger(ThreadTask.class);
	
	public ThreadTask() {
		super();
		this.thread = new Thread();
		this.setName(this.getDefaultName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ThreadTask(String name) {
		super();
		this.setName(name);
	}
	
	/**
	 * Declaring the default name of the task
	 * @return
	 */
	protected abstract String getDefaultName();
	
	/**
	 * This method contains the functionality of the task instance
	 */
	protected abstract void runTaskContents();
	
	/**
	 * Run the task
	 */
	public void run() {
		this.setCurrentStatus(Status.RUNNING);
		this.runTaskContents();
		this.setCurrentStatus(Status.FINISHED);
		this.increaseRunCount();
	}
}