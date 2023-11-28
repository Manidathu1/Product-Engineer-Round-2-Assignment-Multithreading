import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPerformanceTest {

    public static void main(String[] args) throws InterruptedException {
        final int NUM_THREADS = 1000; // Adjust this number to test different thread counts

        long startTime = System.currentTimeMillis();
        // Create a fixed thread pool with NUM_THREADS
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        // Submit tasks to the executor service
        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.submit(() -> {
                // Perform some task here
                // Simulate some work by sleeping for a random amount of time
                long sleepTime = (long) (Math.random() * 100);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shut down the executor service and wait for all threads to finish
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Number of threads: " + NUM_THREADS);
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}
