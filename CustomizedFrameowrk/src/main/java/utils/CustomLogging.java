package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogging {
	
	// XML config where we add format of logs - Timestamp or any other info
	// Start Test and End Test logs
	// Custom Wrapper of logs
	
	static {
		String log4jpath = System.getProperty("user.dir") + "/src/main/resources/log4j.xml";
		System.setProperty("logoutputpath", System.getProperty("user.dir"));
		System.setProperty("log4j.configurationFile", log4jpath);
	}
	
	public static Logger logger = LogManager.getLogger(CustomLogging.class.getName());

	public static void startTestCase(String sTestCaseName) {
		logger.info("*********************");
		logger.info("Test Case with name : " + sTestCaseName + " Started!!!!!");
		logger.info("*********************");
	}
	
	public static void endTestCase(String sTestCaseName) {
		logger.info("*********************");
		logger.info("Test Case with name : " + sTestCaseName + " Ended!!!!!");
		logger.info("*********************");
	}
	
	public void info(String message) {
		logger.info(message);
	}
}
