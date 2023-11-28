public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread is running...");
    }

    public static void main(String[] args) {
        // Create an instance of the Runnable interface
        MyRunnable runnable = new MyRunnable();

        // Create a Thread object and pass the Runnable instance to its constructor
        Thread thread = new Thread(runnable);

        // Start the thread
        thread.start();
    }
}
