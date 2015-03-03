package fi.thread.task.implementation;

import fi.teras.threads.ThreadHelper;
import fi.teras.threads.ThreadTask;

public class CoffeeTask extends ThreadTask {
	private int minLitres = 5;
	private int maxLitres = 10;
	
	@Override
	protected String getDefaultName() {				
		return "unnamed coffee drinking task";
	}

	@Override
	protected void runTaskContents() {
		int coffeeLitresAmount = ThreadHelper.getRandomNumber(minLitres, maxLitres);
		
		for (int i = coffeeLitresAmount; i > 0; i--) {
			ThreadTask.logger.info( this + " Drinking coffee... Litres left " + i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				ThreadTask.logger.error(e);
			}
		}
	}
}
