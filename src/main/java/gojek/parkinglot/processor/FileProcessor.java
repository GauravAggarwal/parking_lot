package gojek.parkinglot.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import gojek.parkinglot.action.Actions;

public class FileProcessor implements Processor {
	
	private File file;
	
	public FileProcessor(File file) {
		super();
		this.file = file;
	}

	@Override
	public void process() {

		try (BufferedReader bufferReader = new BufferedReader(new FileReader(file))) {
			String input = null;
			int lineNo = 1;
			while ((input = bufferReader.readLine()) != null) {
				input = input.trim();
				Actions action = Actions.decode(input);
				if (action != null) {
					if (action.equals(Actions.EXIT)) {
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

}
