package model;

public class TrafficStop {

	private int trafficStopId;
	private int vehicle;
	private int operator;
	private int callRecordId;
	
	public int getTrafficStopId() {
		return trafficStopId;
	}
	public void setTrafficStopId(int trafficStopId) {
		this.trafficStopId = trafficStopId;
	}
	public int getVehicle() {
		return vehicle;
	}
	public void setVehicle(int vehicle) {
		this.vehicle = vehicle;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getCallRecordId() {
		return callRecordId;
	}
	public void setCallRecordId(int callRecordId) {
		this.callRecordId = callRecordId;
	}
}
