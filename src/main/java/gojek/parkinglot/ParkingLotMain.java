package gojek.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import gojek.parkinglot.action.Action;

public class ParkingLotMain {

	public static void main(String[] args) {

		switch (args.length) {
		case 0:
			commandLineReader();
			break;
		case 1:
			fileReader(new File(args[0]));
			break;
		default:
			System.out.println("Invalid input. Usage Style: java -jar <jar_file_path> <input_file_path>");
		}
	}

	static void fileReader(File file) {
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(file))) {
			String input = null;
			int lineNo = 1;
			while ((input = bufferReader.readLine()) != null) {
				input = input.trim();
				Action action = Action.decode(input);
				if (action != null) {
					if (action.equals(Action.EXIT)) {
						break;
					} else if (!action.isValidInput(input)) {
						System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + input);
					} else {
						action.execute(input);
					}
				}
				lineNo++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void commandLineReader() {
		printUsage();
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));) {

			String input;
			while (true) {
				input = bufferReader.readLine();
				if (input != null) {
					input = input.trim();
					Action action = Action.decode(input);
					if (action != null) {
						if (action.equals(Action.EXIT)) {
							break;
						} else if (!action.isValidInput(input)) {
							System.out.println("Type command in correct format as given below ");
							action.printUsage();
						} else {
							action.execute(input);
						}
					} else {
						printUsage();
					}
				} else {
					printUsage();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printUsage() {
		System.out.println("Enter one of the following commands : \n ");
		for (Action action : Action.values()) {
			action.printUsage();
		}
		System.out.println("\n--------------------------------------- \n ");
	}
}
