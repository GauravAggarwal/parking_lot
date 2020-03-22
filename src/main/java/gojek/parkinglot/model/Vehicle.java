package gojek.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author GA
 *
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public abstract class Vehicle {
	
	private String registrationNo = null;
	private String color = null;
	private int numberOfTires;

}
