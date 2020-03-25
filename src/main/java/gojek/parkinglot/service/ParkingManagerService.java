package gojek.parkinglot.service;

import gojek.parkinglot.model.Vehicle;

public interface ParkingManagerService {
	
	public void createParkingLot(int capacity);

	public void park(Vehicle vehicle);

	public void leave(int slotNumber);

	public void status();
	
	public void printRegNumberForColour(String colour);

	public void printSlotNumbersForColour(String colour);

	public void printSlotNumberForRegNo(String registrationNo);
	
	public void cleanUp();

}
