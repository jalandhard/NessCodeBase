package json.practice;

public class Cars {
	
	private String carName;
	private String carVarient;
	private String regNumber;
	private String Color;
	private Parts parts;
	
	
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarVarient() {
		return carVarient;
	}
	public void setCarVarient(String carVarient) {
		this.carVarient = carVarient;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public Parts getParts() {
		return parts;
	}
	public void setParts(Parts parts) {
		this.parts = parts;
	}

}
