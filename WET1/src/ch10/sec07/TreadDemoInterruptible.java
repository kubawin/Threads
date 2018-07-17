package ch10.sec07;

import java.util.Random;

public class TreadDemoInterruptible {

	public static Random rdn = new Random();
	public static boolean flag;

	public static void main(String[] args) throws InterruptedException {
		Runnable interruptibleTask = () -> {
			System.out.print("\nInterruptible: ");
			try {
				for (int i = 1; i <= 100; i++) {
					System.out.print(i + " ");
					Thread.sleep(20);
					if (i == rdn.nextInt(9) + 1) {
						flag = true;
						throw new IllegalStateException();
					};
				}
			} catch (InterruptedException ex) {
				System.out.println("\nInterrupted!");
			}
		};

		Thread thread = new Thread(interruptibleTask);
		thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Yikes!"));
		thread.start();
		Thread.sleep(1000);

		if (rdn.nextBoolean()) {
			thread.interrupt();
		} else if (flag) {
			thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Yikes!"));
		} else {
			thread.join();
			System.out.println("\nJoined thread.");
		}

	}

}
