package fi.teras.threads;

import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * Generic thread task which has own name and id
 * @author Teras
 */
public class GenericThreadTask {
	final static Logger logger = Logger.getLogger(GenericThreadTask.class);
	protected UUID id;
	protected String name;
	
	private Status currentStatus;
	
	public void increaseRunCount() {
		this.runCount = this.runCount++;
	}
	
	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * Available statuses of the task
	 * @author Teras
	 *
	 */
	public enum Status {
		IDLE, RUNNING, FINISHED
	}
	
	/**
	 * number of task usage
	 */
	private int runCount = 0;
	
	public int getRunCount() {
		return runCount;
	}

	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

	public GenericThreadTask() {
		// Generating random id to task instance:
		this.id = UUID.randomUUID();
		this.setCurrentStatus(Status.IDLE);
		GenericThreadTask.logger.info("Created random UUID: \"" + this.getId() + "\"");
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String status = this.getCurrentStatus().toString();
		return this.getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
}
