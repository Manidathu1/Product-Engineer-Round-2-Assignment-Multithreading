public class MultithreadingExample {

    static final int ARRAY_SIZE = 100000000; // Adjust the array size as needed

    public static void main(String[] args) {
        int[] array = new int[ARRAY_SIZE];
        fillArray(array);

        // Single-threaded calculation
        long startTimeSingle = System.currentTimeMillis();
        int sumSingle = calculateSumSingleThreaded(array);
        long endTimeSingle = System.currentTimeMillis();
        System.out.println("Single-threaded sum: " + sumSingle);
        System.out.println("Single-threaded time: " + (endTimeSingle - startTimeSingle) + " ms");

        // Multi-threaded calculation
        long startTimeMulti = System.currentTimeMillis();
        int sumMulti = calculateSumMultiThreaded(array);
        long endTimeMulti = System.currentTimeMillis();
        System.out.println("Multi-threaded sum: " + sumMulti);
        System.out.println("Multi-threaded time: " + (endTimeMulti - startTimeMulti) + " ms");
    }

    static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    static int calculateSumSingleThreaded(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    static int calculateSumMultiThreaded(int[] array) {
        final int numThreads = Runtime.getRuntime().availableProcessors();
        final int segmentSize = ARRAY_SIZE / numThreads;

        int[] partialSums = new int[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                int start = threadIndex * segmentSize;
                int end = (threadIndex == numThreads - 1) ? ARRAY_SIZE : start + segmentSize;
                partialSums[threadIndex] = calculatePartialSum(array, start, end);
            });
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalSum = 0;
        for (int partialSum : partialSums) {
            totalSum += partialSum;
        }

        return totalSum;
    }

    static int calculatePartialSum(int[] array, int start, int end) {
        int partialSum = 0;
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
        return partialSum;
    }
}