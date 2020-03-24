package gojek.parkinglot.model;

/**
 * @author GA
 *
 */
public class Car extends Vehicle {

	public Car(CarBuilder carBuilder) {
		super(carBuilder.registrationNo, carBuilder.color, 4);
	}

	public static class CarBuilder {

		private String registrationNo = null;
		private String color = null;

		public CarBuilder() {
			super();
		}

		public CarBuilder registrationNo(String registrationNo) {
			this.registrationNo = registrationNo;
			return this;
		}

		public CarBuilder color(String color) {
			this.color = color;
			return this;
		}

		public Car build() {
			return new Car(this);
		}

	}

}
