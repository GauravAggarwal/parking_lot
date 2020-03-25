package gojek.parkinglot.processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import gojek.parkinglot.action.Actions;

public class CommandLineProcessor implements Processor {

	@Override
	public void process() {
		printUsage();
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));) {

			String input;
			while (true) {
				input = bufferReader.readLine();
				if (input != null) {
					input = input.trim();
					Actions action = Actions.decode(input);
					if (action != null) {
						if (action.equals(Actions.EXIT)) {
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
		for (Actions action : Actions.values()) {
			action.printUsage();
		}
		System.out.println("\n--------------------------------------- \n ");
	}

}
