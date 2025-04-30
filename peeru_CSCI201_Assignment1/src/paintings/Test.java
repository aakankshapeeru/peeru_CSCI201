package paintings;

import java.io.File;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.io.FileNotFoundException;

import java.util.Locale;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("What is the name of the exhibit file?");
		Scanner file_name = new Scanner(System.in);
		String fname = "";
		if (file_name.hasNextLine()) {

			fname = file_name.nextLine();

		} else {
			System.out.println("No input from user");
			file_name.close();
			return;
		}
		// reading file name
		boolean badSyntax = false;
		AllPaintings aPaintings = null;
		File file = new File(fname);
		while (true) {
			if (!file.exists()) { // if file can't be found
				System.out.println("The file " + fname + " could not be found.");

			} else {
				Scanner scan = new Scanner(file);
				String temp = "";
				while (scan.hasNext()) {
					temp += scan.nextLine();
				}
				scan.close();

				Gson gson = new Gson();
				try { // file has bad formatting
					aPaintings = gson.fromJson(temp, AllPaintings.class);
					if (!aPaintings.validData()) {
						System.out.println("The file " + fname + " has not been properly formatted");
						badSyntax = true;
					}
					else {
						badSyntax=false;
					}
				} catch (JsonSyntaxException e) {
					badSyntax = true;
					System.out.println("The file " + fname + " has not been properly formatted");
				}

			}
			if (!badSyntax && file.exists()) {
				break;
			}
			System.out.println("What is the name of the exhibit file?");
			fname = file_name.nextLine();
			file = new File(fname);

		}

		System.out.println("The file has been properly read");
		System.out.println();
		Menu();

		Scanner user_input = new Scanner(System.in);

		while (true) { // loop until user quits

			String number = user_input.nextLine();

			switch (number) {
			case "1":
				if(aPaintings.empty()) {
					System.out.println("No exhibits left our repository!");
					System.out.println();
					Menu();
					break;
				}
				aPaintings.displayAll();
				Menu();
				break;

			case "2": // displays details of the exhibit asked by user
				if(aPaintings.empty()) {
					System.out.println("No exhibits left our repository!");
					System.out.println();
					Menu();
					break;
				}
				System.out.println("What is the exhibit you would like to search for?");
				String name = user_input.nextLine();
				boolean flag = aPaintings.searchExhibit(name);
				while (flag == false) {
					System.out.println("This exhibit doesn't exist in our collection, try again");
					name = user_input.nextLine();
					flag = aPaintings.searchExhibit(name);

				}
				Menu();
				break;

			case "3":// displays all the exhibits in the museum requested by user
				if(aPaintings.empty()) {
					System.out.println("No exhibits left our repository!");
					System.out.println();
					Menu();
					break;
				}
				System.out.println("What museum would you like to search for?");
				String name1 = user_input.nextLine();
				boolean me = aPaintings.museumExists(name1);
				while (!me) {
					System.out.println("This museum doesn't exist in our collection, try again");
					name1 = user_input.nextLine();
					me = aPaintings.museumExists(name1);
				}
				aPaintings.allExhibits(name1);
				Menu();
				break;

			case "4": // add an exhibit
				String name2 = "";
				String exhibit = "";
				String localDate = "";
				String museum = ""; // check for input validity of name, date, exhibit and museum
				System.out.println("What is the name of the artist you would like to add?");

				name2 = user_input.nextLine();
				while (name2.trim().equals("")) {
					System.out.println("You cannot enter an empty object");
					name2 = user_input.nextLine();
				}
				System.out.println("What is the name of the exhibit for " + name2 + " ?");
				exhibit = user_input.nextLine();
				while (exhibit.trim().equals("")) {
					System.out.println("You cannot enter an empty object");
					exhibit = user_input.nextLine();
				}
				System.out.println("What is the date of " + name2 + "'s exhibit?");
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
						.withResolverStyle(ResolverStyle.STRICT);
				DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);
				localDate = user_input.nextLine();
				while (assertFalse(validator.isValid(localDate))) {
					System.out.println("Invalid Date!");
					localDate = user_input.nextLine();
				}

				System.out.println("What is the museum where " + name2 + " has an exhibit?");
				museum = user_input.nextLine();
				while (museum.trim().equals("")) {
					System.out.println("You cannot enter an empty object");
					museum = user_input.nextLine();
				}
				if (aPaintings.addExhibit(name2, exhibit, localDate, museum)) {
					System.out.println("There is now a new entry for: ");
					System.out.println(name2 + ", " + exhibit + ", on " + localDate + ", held at " + museum);
					System.out.println();
				} else {
					Menu();
					break;
				}
				Menu();
				break;

			case "5": // remove an exhibit

				if (aPaintings.empty()) {
					System.out.println("You cannot remove from any empty list!");
					System.out.println();
					Menu();
					break;
				}
				System.out.println("Which exhibit would you like to remove?");
				aPaintings.displayNames();
				String num = user_input.nextLine();

				while (!num.matches("[0-9]+") || Integer.parseInt(num) <= 0
						|| Integer.parseInt(num) > aPaintings.numberOfExhibits()) {
					System.out.println("Not a valid option");
					num = user_input.nextLine();
				}

				String removed = aPaintings.removeExhibit(Integer.parseInt(num));
				while (removed.equals("")) {

					num = user_input.nextLine();
					removed = aPaintings.removeExhibit(Integer.parseInt(num));
				}
				System.out.println(removed + " exhibit is now removed");
				System.out.println();
				Menu();
				break;

			case "6":// sort exhibits by artist's name
				if(aPaintings.empty()) {
					System.out.println("No exhibits left our repository!");
					System.out.println();
					Menu();
					break;
				}
				System.out.println("1) A to Z");
				System.out.println("2) Z to A");
				String sort = "";
			
				while (!sort.equals("1") && !sort.equals("2")) {
					sort = user_input.nextLine();
					if (sort.equals("1")) {
						aPaintings.sortAtoZ();

						System.out.println("Your exhibits are now sorted from A to Z.");
						System.out.println();
					} else if (sort.equals("2")) {
						aPaintings.sortZtoA();

						System.out.println("Your exhibits are now sorted from Z to A.");
						System.out.println();
					} else {
						System.out.print("That is not a valid option");
					}
				}
				Menu();
				break;

			case "7": // to quit
				System.out.println("1)Yes");
				System.out.println("2)No");
				System.out.println("Would you like to save your edits?");
				String choice = user_input.nextLine();
				if (choice.equals("1")) {
					Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
					String temp1 = gson1.toJson(aPaintings);
					FileWriter fw;
					// to save the changes made so far in the json file
					try {
						fw = new FileWriter(fname);
						fw.write(temp1);
						fw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				System.out.println("Thank you for using my program!");
				user_input.close();

				return;

			default:
				System.out.println("That is not a valid option");

			}

		}

	}

	private static boolean assertFalse(boolean valid) {

		return !valid;
	}

	private static void Menu() {
		System.out.println("1)Display all exhibits");
		System.out.println("2)Search for an exhibit (by exhibit name)");
		System.out.println("3)Search for all exhibits at a museum");
		System.out.println("4)Add a new exhibit");
		System.out.println("5)Remove an exhibit");
		System.out.println("6)Sort exhibits");
		System.out.println("7)Exit");
		System.out.println("What would you like to do?");
	}

}
