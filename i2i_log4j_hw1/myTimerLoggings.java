import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class myTimerLoggings {
    private static final Logger logger = LogManager.getLogger(myTimerLoggings.class);

    private long lastLoggedTimeSecond = 0;
    private long lastLoggedTimeMinute = 0;
    private long lastLoggedTimeHour = 0;

    public synchronized void logDebug(String message) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastLoggedTimeSecond >= 1000) {
            logger.debug(message);
            lastLoggedTimeSecond = currentTime;
        }
    }

    public synchronized void logInfo(String message) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastLoggedTimeMinute >= 60000) {
            logger.info(message);
            lastLoggedTimeMinute = currentTime;
        }
    }

    public synchronized void logError(String message) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastLoggedTimeHour >= 3600000) {
            logger.error(message);
            lastLoggedTimeHour = currentTime;
        }
    }
}