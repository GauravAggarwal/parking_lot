package gojek.parkinglot;

import gojek.parkinglot.processor.ProcessorCreator;

public class ParkingLotMain {

	public static void main(String[] args) {
		
		new ProcessorCreator().createProcessor(args).process();
	}

}
