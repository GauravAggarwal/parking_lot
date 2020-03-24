package gojek.parkinglot.service;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

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
		List<String> regNumbers = new ArrayList<>();
		for (Vehicle vehicle : parkingSpace.values()) {
			if (color.equalsIgnoreCase(vehicle.getColor())) {
				regNumbers.add(vehicle.getRegistrationNo());
			}
		}
		if (!regNumbers.isEmpty()) {
			System.out.println(StringUtils.join(regNumbers, ", "));
		} else {
			System.out.println("No cars for this color : " + color);
		}

	}

	public void printSlotNumbersForColor(String color) {
		List<Integer> slots = new ArrayList<>();
		for (Entry<Integer, Vehicle> entry : parkingSpace.entrySet()) {
			Vehicle vehicle = entry.getValue();
			if (color.equalsIgnoreCase(vehicle.getColor())) {
				slots.add(entry.getKey());
			}
		}
		if (!slots.isEmpty()) {
			System.out.println(StringUtils.join(slots, ", "));
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
