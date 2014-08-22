package model;

public class TrafficStop {

	private int trafficStopId;
	private String vehicle; //Index from vehicle table
	private int operator; //Index from person table
	private int callRecordId; //Index from call_record table
	
	public int getTrafficStopId() {
		return trafficStopId;
	}
	public void setTrafficStopId(int trafficStopId) {
		this.trafficStopId = trafficStopId;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String string) {
		this.vehicle = string;
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
