package gojek.parkinglot.model;

/**
 * @author GA
 *
 */
public class Car extends Vehicle {

	public Car(CarBuilder carBuilder) {
		super(carBuilder.registrationNo, carBuilder.colour, 4);
	}

	public static class CarBuilder {

		private String registrationNo = null;
		private String colour = null;

		public CarBuilder() {
			super();
		}

		public CarBuilder registrationNo(String registrationNo) {
			this.registrationNo = registrationNo;
			return this;
		}

		public CarBuilder colour(String colour) {
			this.colour = colour;
			return this;
		}

		public Car build() {
			return new Car(this);
		}

	}

}
