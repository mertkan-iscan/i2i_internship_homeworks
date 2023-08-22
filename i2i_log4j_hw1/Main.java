public class Main {

    public static void main(String[] args) throws InterruptedException {
        myTimerLoggings rateLimitedLogger = new myTimerLoggings();

        // Simulate a task that generates log messages
        for (int i = 0; i < 36000; i++) {
            rateLimitedLogger.logDebug("This is second message number " + (i + 1));
            rateLimitedLogger.logError("This is hour message number  " + (i + 1));
            rateLimitedLogger.logInfo("This is minute message number " + (i + 1));
            Thread.sleep(10);  // Simulate some work by sleeping for 1 second
        }
    }
}
