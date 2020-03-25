package gojek.parkinglot.model;

/**
 * @author GA
 *
 */
public abstract class Vehicle {
	
	private String registrationNo = null;
	private String colour = null;
	private int numberOfTires;
	
	public Vehicle(String registrationNo, String colour, int numberOfTires) {
		super();
		this.registrationNo = registrationNo;
		this.colour = colour;
		this.numberOfTires = numberOfTires;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getNumberOfTires() {
		return numberOfTires;
	}

	public void setNumberOfTires(int numberOfTires) {
		this.numberOfTires = numberOfTires;
	}
	
}
