package helper;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;



public class LoggerHelper {
  private static PatternLayout layout = null;
  private static ConsoleAppender console = null;
  private static RollingFileAppender rolling = null;
  private static Logger rootLogger = null;
  private static Logger logger = null;
  private static final String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{3}:%L - [%M] %m%n";
  private static boolean flag = false;
  
  private static void initLogger(){
	  layout = new PatternLayout(pattern);
	  
	  console = new ConsoleAppender(layout);
	  console.setName("stdout");
	  console.setTarget("System.out");
	  console.setThreshold(Level.INFO);
	  console.activateOptions();
	  
	  try {
		rolling = new RollingFileAppender(layout,"log/selenium.log");
		  rolling.setName("RFILE");
		  rolling.setThreshold(Level.WARN);
		  rolling.setMaxFileSize("10MB");
		  rolling.setMaxBackupIndex(100);
		  rolling.activateOptions();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	  rootLogger = Logger.getRootLogger();
	  rootLogger.addAppender(console);
	  rootLogger.addAppender(rolling);
	  
  }
  
  public static Logger getLogger(@SuppressWarnings("rawtypes") Class aClass){
	  if(!flag){
		  initLogger();
		  flag = true;
	  }
	  logger = Logger.getLogger(aClass);
	  return  logger;

  }
}
