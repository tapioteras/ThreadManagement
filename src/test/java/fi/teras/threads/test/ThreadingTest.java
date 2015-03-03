package fi.teras.threads.test;

import org.junit.Assert;
import org.junit.Test;

import fi.teras.threads.ThreadHandler;
import fi.teras.threads.ThreadHelper;
import fi.teras.threads.ThreadProvider;
import fi.thread.task.implementation.CoffeeTask;
import fi.thread.task.implementation.InfoTask;

public class ThreadingTest {
	
	/**
	 * A simple test which runs random amount of tasks
	 */
	@Test
	public void TestThreadHandling() {
		try {
			ThreadProvider p = new ThreadHandler();
			
			// Adding random task objects to task repository:
			for (int i = ThreadHelper.getRandomNumber(1, 20); i > 0; i--) {
				if (i % 2 == 0) {
					p.addTask(new InfoTask());
				} else {
					p.addTask(new CoffeeTask());
				}
			}
			
			p.runAll();
			Assert.assertTrue(true);	
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
