package ch10.sec07;

public class ThreadDemoExceptional {
	public static void main(String[] args) throws InterruptedException {
		
		Runnable exceptionalTask = () -> {
            System.out.print("\nExceptional: ");
            for (int i = 1; i <= 100; i++) {
                System.out.print(i + " ");
                if (i == 12) throw new IllegalStateException();
            }            
        };
        Thread thread = new Thread(exceptionalTask);
        thread.start();
        thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Yikes!"));
        thread.join();
		
	}

}
