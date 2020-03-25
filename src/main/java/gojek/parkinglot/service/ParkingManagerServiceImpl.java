package gojek.parkinglot.service;

import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

import gojek.parkinglot.model.Vehicle;

public class ParkingManagerServiceImpl implements ParkingManagerService {
	
	private static final String PARKING_LOT_ERROR = "Sorry, you need to create a parking lot first, run create_parking_lot {n}";
	private SortedMap<Integer, Vehicle> parkingSpace;
	private PriorityQueue<Integer> emptySlots; 

	public void createParkingLot(int capacity) {
		parkingSpace = new TreeMap<>();
		emptySlots = new PriorityQueue<>(capacity); 
		initializeEmptySlots(capacity);
		System.out.println("Created a parking lot with " + capacity + " slots  ");

	}

	private void initializeEmptySlots(int capacity) {
		for (int i = 1; i <= capacity; i++) {
			emptySlots.add(i);
		}
	}

	public void park(Vehicle vehicle) {
		if (parkingSpace == null) {
			System.out.println(PARKING_LOT_ERROR);
			return;
		}
		Integer slot = emptySlots.poll();
		if (slot != null) {
			parkingSpace.put(slot, vehicle);
			System.out.println("Allocated slot number: " + slot);
		} else {
			System.out.println("Sorry, parking lot is full");
		}
	}

	public void leave(int slotNumber) {
		if (parkingSpace == null) {
			System.out.println(PARKING_LOT_ERROR);
			return;
		}
		parkingSpace.remove(slotNumber);
		emptySlots.add(slotNumber);
		System.out.println("Slot number " + slotNumber + " is free ");
	}

	public void status() {
		if (parkingSpace == null) {
			System.out.println(PARKING_LOT_ERROR);
			return;
		}
		if(parkingSpace.isEmpty()) {
			System.out.println("parking lot is empty  ");
		} else {
			System.out.println("Slot No.\tRegistration No.\tColour");
			for (Entry<Integer, Vehicle> entry : parkingSpace.entrySet()) {
				Vehicle vehicle = entry.getValue();
				System.out.println(entry.getKey() + "\t\t" + vehicle.getRegistrationNo() + "\t\t" + vehicle.getColour());
			}
		}

	}

	public void printRegNumberForColour(String colour) {
		if (parkingSpace == null) {
			System.out.println(PARKING_LOT_ERROR);
			return;
		}
		StringBuilder buffer = new StringBuilder("");
		for (Vehicle vehicle : parkingSpace.values()) {
			if (colour.equalsIgnoreCase(vehicle.getColour())) {
				buffer.append(vehicle.getRegistrationNo()).append(", ");
			}
		}
		if (buffer.length()!=0) {
			buffer.replace(buffer.length()-2, buffer.length(), "");
			System.out.println(buffer.toString());
		} else {
			System.out.println("No cars for this colour : " + colour);
		}

	}

	public void printSlotNumbersForColour(String colour) {
		if (parkingSpace == null) {
			System.out.println(PARKING_LOT_ERROR);
			return;
		}
		StringBuilder buffer = new StringBuilder("");
		for (Entry<Integer, Vehicle> entry : parkingSpace.entrySet()) {
			Vehicle vehicle = entry.getValue();
			if (colour.equalsIgnoreCase(vehicle.getColour())) {
				buffer.append(entry.getKey()).append(", ");
			}
		}
		if (buffer.length()!=0) {
			buffer.replace(buffer.length()-2, buffer.length(), "");
			System.out.println(buffer.toString());
		} else {
			System.out.println("No cars for this colour : " + colour);
		}

	}

	public void printSlotNumberForRegNo(String registrationNo) {
		if (parkingSpace == null) {
			System.out.println(PARKING_LOT_ERROR);
			return;
		}
		Integer slot = null;
		for (Entry<Integer, Vehicle> entry : parkingSpace.entrySet()) {
			Vehicle vehicle = entry.getValue();
			if (registrationNo.equalsIgnoreCase(vehicle.getRegistrationNo())) {
				slot = entry.getKey();
				break;
			}
		}
		if (slot!=null) {
			System.out.println(slot);
		} else {
			System.out.println("Not found ");
		}

	}
	
	public void cleanUp() {
		parkingSpace = null;
		emptySlots = null;
	}

}
