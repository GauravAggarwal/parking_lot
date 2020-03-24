package gojek.parkinglot.model;

/**
 * @author GA
 *
 */
public abstract class Vehicle {
	
	private String registrationNo = null;
	private String color = null;
	private int numberOfTires;
	
	public Vehicle(String registrationNo, String color, int numberOfTires) {
		super();
		this.registrationNo = registrationNo;
		this.color = color;
		this.numberOfTires = numberOfTires;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumberOfTires() {
		return numberOfTires;
	}

	public void setNumberOfTires(int numberOfTires) {
		this.numberOfTires = numberOfTires;
	}
	
}
