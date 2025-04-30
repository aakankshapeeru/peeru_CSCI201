package peeru_CSCI201_Assignment2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVReader {
	public boolean validateData(List<List<String>> lines) {
		for (List<String> row : lines) {
			if (row.isEmpty() || row.size() != 4) {
				return false;
			}
			String start = row.get(0).strip();
			String name = row.get(1).strip();
			String tickets = row.get(2).strip();
			String price = row.get(3).strip();
			if (start.isEmpty() || name.isEmpty() || tickets.isEmpty() || price.isEmpty()) {
				return false;
			}

		}
		return true;
	}

	public Schedule readCsv(Scanner file_name) throws IOException {

		System.out.println("What is the name of the schedule file?");
		String fname = "";
		if (file_name.hasNextLine()) {
			fname = file_name.nextLine();
		} else {
			System.out.println("No input from user");
			file_name.close();
			return null;
		}
		boolean bad_syntax = false;
		Schedule schedule = null;
		File file = new File(fname);
		while (true) {
			if (!file.exists()) {
				System.out.println("The file " + fname + " could not be found");
			} else {
				Path filePath = Path.of(fname);
				Pattern pattern = Pattern.compile(",");
				List<List<String>> records = Files.lines(filePath).map(line -> pattern.splitAsStream(line).toList())
						.toList();
				if (validateData(records)) {
					List<Task> tasks = Files
							.lines(filePath).map(line -> line.split(",")).map(fields -> new Task(fields[0].strip(),
									fields[1].strip(), fields[2].strip(), fields[3].strip()))
							.collect(Collectors.toList());
					schedule = new Schedule(tasks);
					if (!schedule.validData(records)) {
						System.out.println("The file " + fname + " has not been properly formatted");
						bad_syntax = true;
					} else {
						bad_syntax = false;

					}
				} else {
					bad_syntax = true;
					System.out.println("The file " + fname + " has not been properly formatted");
				}

			}
			if (!bad_syntax && file.exists()) {

				break;
			}
			System.out.println("What is the name of the scheduled file?");
			fname = file_name.nextLine();
			file = new File(fname);
		}

		System.out.println("The file has been properly read");

		return schedule;

	}
}
