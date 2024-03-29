package gojek.parkinglot.action;

import java.util.HashMap;
import java.util.Map;

import gojek.parkinglot.model.Car;
import gojek.parkinglot.service.ParkingManagerService;
import gojek.parkinglot.service.ParkingManagerServiceImpl;

public enum Actions {

	CREATE_PARKING_LOT("create_parking_lot") {
		@Override
		public void execute(String input) {
			String[] inputs = input.split(" ");
			int capacity = Integer.parseInt(inputs[1]);
			parkingManager.createParkingLot(capacity);
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 2 && isNumeric(inputs[1]);
		}

		@Override
		public void printUsage() {
			System.out.println("To create parking lot of size n : create_parking_lot {n} ");
			
		}
	}, PARK("park") {
		@Override
		public void execute(String input) {
			String[] inputs = input.split(" ");
			Car car = new Car.CarBuilder().registrationNo(inputs[1]).colour(inputs[2]).build();
			parkingManager.park(car);
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 3;
		}
		
		@Override
		public void printUsage() {
			System.out.println("To park a car  : park {car number} {car clour} ");
			
		}
	}, LEAVE("leave") {
		@Override
		public void execute(String input) {
			String[] inputs = input.split(" ");
			int slotNumber = Integer.parseInt(inputs[1]);
			parkingManager.leave(slotNumber);
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 2 && isNumeric(inputs[1]);
		}
		
		@Override
		public void printUsage() {
			System.out.println("To leave a car : leave {slot number} ");
			
		}
	}, STATUS("status") {
		@Override
		public void execute(String input) {
			parkingManager.status();
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 1;
		}
		
		@Override
		public void printUsage() {
			System.out.println("To print current parking status	: status ");
			
		}
	},
	REG_NUMBER_FOR_CARS_WITH_COLOR("registration_numbers_for_cars_with_colour") {
		@Override
		public void execute(String input) {
			String[] inputs = input.split(" ");
			parkingManager.printRegNumberForColour(inputs[1]);
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 2;
		}
		
		@Override
		public void printUsage() {
			System.out.println("Get registration number for cars with colour : registration_numbers_for_cars_with_colour {car colour} ");
			
		}
	},
	SLOT_NUMBERS_FOR_CARS_WITH_COLOR("slot_numbers_for_cars_with_colour") {
		@Override
		public void execute(String input) {
			String[] inputs = input.split(" ");
			parkingManager.printSlotNumbersForColour(inputs[1]);
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 2;
		}
		
		@Override
		public void printUsage() {
			System.out.println("Get slot number for cars with colour : slot_numbers_for_cars_with_colour {car colour} ");
			
		}
	},
	SLOT_NUMBER_FOR_REG_NUMBER("slot_number_for_registration_number") {
		@Override
		public void execute(String input) {
			String[] inputs = input.split(" ");
			parkingManager.printSlotNumberForRegNo(inputs[1]);
		}

		@Override
		public boolean isValidInput(String input) {
			String[] inputs = input.split(" ");
			return inputs.length == 2;
		}
		
		@Override
		public void printUsage() {
			System.out.println("Get slot number for registration number : slot_number_for_registration_number {registration number} ");
			
		}
	},
	EXIT("exit") {
		@Override
		public void execute(String input) {
			parkingManager.cleanUp();
		}

		@Override
		public boolean isValidInput(String input) {
			return false;
		}
		
		@Override
		public void printUsage() {
			System.out.println("To exit parking application : exit ");
		}

	};
	
	private static ParkingManagerService parkingManager = new ParkingManagerServiceImpl();

	private static final Map<String, Actions> BY_ACTION = new HashMap<>();
    
    static {
        for (Actions e: values()) {
        	BY_ACTION.put(e.getCommand().toUpperCase(), e);
        }
    }
 

	private String command;
	
	public String getCommand() {
		return command;
	}

	private Actions(String command) {
		this.command = command;
	}
	
	public static Actions decode(String input) {
		String[] inputs = input.split(" ");
		return BY_ACTION.get(inputs[0].toUpperCase());
	}
	
	private static boolean isNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
	}
	
	public abstract void execute(String input);
	
	public abstract void printUsage();
	
	public abstract boolean isValidInput(String input);
}
