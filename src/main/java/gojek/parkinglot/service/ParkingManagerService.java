package gojek.parkinglot.service;

import gojek.parkinglot.model.Vehicle;

public interface ParkingManagerService {
	
	public void createParkingLot(int capacity);

	public void park(Vehicle vehicle);

	public void leave(int slotNumber);

	public void status();
	
	public void printRegNumberForColor(String color);

	public void printSlotNumbersForColor(String color);

	public void printSlotNumberForRegNo(String registrationNo);
	
	public void cleanUp();

}
