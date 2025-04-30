package peeru_CSCI201_Assignment2;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;

public class Assignment2 {

	public static void main(String[] args) throws FileNotFoundException {

		Schedule schedule = null;
		ExhibitList exhibits = null;

		Scanner file_name = new Scanner(System.in);

		JSONReader jsonRead = new JSONReader();
		try {
			exhibits = jsonRead.readJson();
		} catch (IOException e) {
			e.printStackTrace();
		}

		CSVReader csvRead = new CSVReader();
		try {
			schedule = csvRead.readCsv(file_name);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int initialBalance = 0;
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("What is the initial balance?");
			try {
				initialBalance = scan.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter a valid balance:");
				scan.nextLine();
			}
		}

		System.out.println("Starting execution of program...");
		System.out.println("Initial Balance: " + initialBalance);
		schedule.setBalance(initialBalance);
		scan.close();

		ExecutorService executor = Executors.newCachedThreadPool();
		
		Map<String, Semaphore> semaphores = new Hashtable<>();
		for (Exhibit exhibit : exhibits.getData()) {
			semaphores.put(exhibit.getName(), new Semaphore(exhibit.getAgents()));
		}

		Map<Integer, List<Task>> sameTimeTrades = new TreeMap<>();
		for (int i = 0; i < schedule.size(); i++) {
			Task task = schedule.get(i);
			int startTime = Integer.parseInt(task.getTradeStart());

			sameTimeTrades.putIfAbsent(startTime, new ArrayList<>());
			sameTimeTrades.get(startTime).add(task);
		}

		for (Map.Entry<Integer, List<Task>> x : sameTimeTrades.entrySet()) {

			int size = x.getValue().size();
			CyclicBarrier barrier = new CyclicBarrier(size);

			for (Task task : x.getValue()) {
				executor.execute(new Trade(schedule, semaphores.get(task.getArtistName()), task, barrier));
			}
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
			Thread.yield();
		}

		if (Trade.getRejectedTrades() == 0) {
			System.out.println("All exhibit ticket trades completed!");
		} else {
			System.out.println("All exhibit ticket trades completed, except " + Trade.getRejectedTrades() + " trades.");
		}

	}

}
