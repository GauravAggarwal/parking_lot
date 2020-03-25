package gojek.parkinglot.processor;

import java.io.File;

public class ProcessorCreator {
	
	public Processor createProcessor(String[] args) {
		switch (args.length) {
		case 0:
			return new CommandLineProcessor();
		case 1:
			return new FileProcessor(new File(args[0]));

		default:
			return new DefaultProcessor();
		}
	}
}
