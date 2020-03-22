package gojek.parkinglot.model;

import lombok.Builder;

/**
 * @author GA
 *
 */
public class Car extends Vehicle {
	

	@Builder
	public Car(String registrationNo, String color) {
		super(registrationNo, color, 4);
	}

}
