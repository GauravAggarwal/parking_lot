package gojek.parkinglot.service;

import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

import gojek.parkinglot.model.Vehicle;

public class ParkingManagerServiceImpl implements ParkingManagerService {
	
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
		Integer slot = emptySlots.poll();
		if(slot!=null) {
			parkingSpace.put(slot, vehicle);
			System.out.println("Allocated slot number: " + slot);
		} else {
			System.out.println("Sorry, parking lot is full");
		}
	}

	public void leave(int slotNumber) {
		parkingSpace.remove(slotNumber);
		emptySlots.add(slotNumber);
		System.out.println("Slot number " + slotNumber + " is free ");
	}

	public void status() {
		if(parkingSpace.isEmpty()) {
			System.out.println("parking lot is empty  ");
		} else {
			System.out.println("Slot No.\tRegistration No.\tColor");
			for (Entry<Integer, Vehicle> entry : parkingSpace.entrySet()) {
				Vehicle vehicle = entry.getValue();
				System.out.println(entry.getKey() + "\t\t" + vehicle.getRegistrationNo() + "\t\t" + vehicle.getColor());
			}
		}

	}

	public void printRegNumberForColor(String color) {
		StringBuilder buffer = new StringBuilder("");
		for (Vehicle vehicle : parkingSpace.values()) {
			if (color.equalsIgnoreCase(vehicle.getColor())) {
				buffer.append(vehicle.getRegistrationNo()).append(", ");
			}
		}
		if (buffer.length()!=0) {
			buffer.replace(buffer.length()-2, buffer.length(), "");
			System.out.println(buffer.toString());
		} else {
			System.out.println("No cars for this color : " + color);
		}

	}

	public void printSlotNumbersForColor(String color) {
		StringBuilder buffer = new StringBuilder("");
		for (Entry<Integer, Vehicle> entry : parkingSpace.entrySet()) {
			Vehicle vehicle = entry.getValue();
			if (color.equalsIgnoreCase(vehicle.getColor())) {
				buffer.append(entry.getKey()).append(", ");
			}
		}
		if (buffer.length()!=0) {
			buffer.replace(buffer.length()-2, buffer.length(), "");
			System.out.println(buffer.toString());
		} else {
			System.out.println("No cars for this color : " + color);
		}

	}

	public void printSlotNumberForRegNo(String registrationNo) {
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
