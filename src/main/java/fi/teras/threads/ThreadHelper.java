package fi.teras.threads;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Helper methods related on thread management context
 * @author Teras
 *
 */
public class ThreadHelper {
	final static Logger logger = Logger.getLogger(ThreadHelper.class);
	
	/**
	 * Generates a random number by range
	 * @param min Minimum number of the range
	 * @param max Maximum number of the range
	 * @return
	 */
	public static int getRandomNumber(int min, int max) {
		Random rn = new Random();
		int range = max - min + 1;
		int random =  rn.nextInt(range) + min;
		ThreadHelper.logger.info("Generated random number " + random);
		return random;
	}
}