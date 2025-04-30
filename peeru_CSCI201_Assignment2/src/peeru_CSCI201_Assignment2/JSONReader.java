package peeru_CSCI201_Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JSONReader {
  public ExhibitList readJson() throws FileNotFoundException {
	System.out.println("What is the name of the exhibits file?");
	Scanner file_scan = new Scanner(System.in);
	String fname = "";
	if (file_scan.hasNextLine()) {

		fname = file_scan.nextLine();

	} else {
		System.out.println("No input from user");
		file_scan.close();
		return null;
	}
	
	boolean badSyntax = false;
	ExhibitList aPaintings = null;
	File file = new File(fname);
	while (true) {
		if (!file.exists()) { 
			System.out.println("The file " + fname + " could not be found.");

		} else {
			Scanner scan = new Scanner(file);
			String temp = "";
			while (scan.hasNext()) {
				temp += scan.nextLine();
			}
			scan.close();

			Gson gson = new Gson();
			try { 
				aPaintings = gson.fromJson(temp, ExhibitList.class);
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
		fname = file_scan.nextLine();
		file = new File(fname);

	}

	System.out.println("The file has been properly read");
	

	return aPaintings;
  }
}
