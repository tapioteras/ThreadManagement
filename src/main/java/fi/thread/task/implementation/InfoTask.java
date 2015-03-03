package fi.thread.task.implementation;

import fi.teras.threads.ThreadTask;

public class InfoTask extends ThreadTask {
	private final long waitSeconds = 2;
	
	public InfoTask() {
		super();
	}
	
	public InfoTask(String name) {
		super(name);
	}
	
	@Override
	public void runTaskContents() {
		try {
			ThreadTask.logger.info("Info task is waiting " + this.getWaitSeconds() + " seconds...");
			Thread.sleep((this.getWaitSeconds()*1000));
		} catch (InterruptedException e) {
			ThreadTask.logger.error(e);
		} catch (Exception e) {
			ThreadTask.logger.error(e);
		}
	}

	public long getWaitSeconds() {
		return waitSeconds;
	}

	@Override
	protected String getDefaultName() {
		return "unnamed infotask";
	}
}
