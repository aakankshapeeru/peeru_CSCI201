package peeru_CSCI201_Assignment2;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Trade implements Runnable {

	private static int rejectedTrades = 0;
	private static long startTime = 0;
	private Schedule schedule;
	private Semaphore semaphore;
	private Task task;
	private CyclicBarrier barrier;

	public Trade(Schedule schedule, Semaphore semaphore, Task task, CyclicBarrier barrier) {

		this.schedule = schedule;
		this.semaphore = semaphore;
		this.task = task;
		this.barrier = barrier;

		if (startTime == 0)
			startTime = System.currentTimeMillis();

	}

	public static int getRejectedTrades() {
		return rejectedTrades;
	}

	private void buyTransaction() {
		try {
			System.out.println(Utility.getTime(startTime) + " Starting purchase of " + task.getnOfTickets()
					+ " exhibit tickets of " + task.getArtistName());

			int amount = Integer.parseInt(task.getTicketPrice()) * Integer.parseInt(task.getnOfTickets());

			synchronized (schedule) {
				if (amount > schedule.getBalance()) {
					System.out.println("Transaction failed: Insufficient balance. Unsuccessful purchase of "
							+ task.getnOfTickets() + " exhibit tickets of " + task.getArtistName());
					rejectedTrades++;
					return;
				}
				schedule.setBalance(schedule.getBalance() - amount);
			}

			Thread.sleep(2000);

			System.out.println(Utility.getTime(startTime) + " Finished purchase of " + task.getnOfTickets()
					+ " exhibit tickets of " + task.getArtistName());
			System.out.println("Current Balance after trade: " + schedule.getBalance());

		} catch (InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
	}

	private void saleTransaction() {
		try {
			System.out.println(Utility.getTime(startTime) + " Starting sale of "
					+ Math.abs(Integer.parseInt(task.getnOfTickets())) + " exhibit tickets of " + task.getArtistName());

			Thread.sleep(3000);

			int amount = Integer.parseInt(task.getTicketPrice()) * Math.abs(Integer.parseInt(task.getnOfTickets()));

			synchronized (schedule) {
				schedule.setBalance(schedule.getBalance() + amount);
			}

			System.out.println(Utility.getTime(startTime) + " Finished sale of "
					+ Math.abs(Integer.parseInt(task.getnOfTickets())) + " exhibit tickets of " + task.getArtistName());
			System.out.println("Current Balance after trade: " + schedule.getBalance());

		} catch (InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
	}

	@Override
	public void run() {
		try {

			barrier.await();
			long delay = Integer.parseInt(task.getTradeStart()) * 1000;
			Thread.sleep(delay);
			semaphore.acquire();

			if (Integer.parseInt(task.getnOfTickets()) > 0) {
				buyTransaction();
			} else {
				saleTransaction();
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			semaphore.release();

		}
	}
}
