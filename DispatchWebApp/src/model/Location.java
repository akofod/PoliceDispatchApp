package model;

public class Location {
	private int locationId;
	private String address;
	private int numberCalls;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNumberCalls() {
		return numberCalls;
	}
	public void setNumberCalls(int numberCalls) {
		this.numberCalls = numberCalls;
	}
}
