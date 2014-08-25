package model;

import java.util.ArrayList;

public class CallRecord {
	private int callRecordId;
	private int mannerReceived;
	private String callDate; //Needs to be in format 'YYYY-MM-DD'
	private int callShift;
	private String timeReceived; //Needs to be in format 'HH:MM'
	private String timeDispatched; //Needs to be in format 'HH:MM'
	private String timeArrived; //Needs to be in format 'HH:MM'
	private String timeCleared; //Needs to be in format 'HH:MM'
	private int complaintType;
	private int caller;
	private int location;
	private String dispatcher;
	private int result;
	private String incidentNumber;
	private int isTrafficStop;
	private int isIncident;
	
	private ArrayList<String> notes;
	private ArrayList<String> officers;
	
	public int getCallRecordId() {
		return callRecordId;
	}
	public void setCallRecordId(int callRecordId) {
		this.callRecordId = callRecordId;
	}
	public int getMannerReceived() {
		return mannerReceived;
	}
	public void setMannerReceived(int mannerReceived) {
		this.mannerReceived = mannerReceived;
	}
	public String getCallDate() {
		return callDate;
	}
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	public int getCallShift() {
		return callShift;
	}
	public void setCallShift(int callShift) {
		this.callShift = callShift;
	}
	public String getTimeReceived() {
		return timeReceived;
	}
	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}
	public String getTimeDispatched() {
		return timeDispatched;
	}
	public void setTimeDispatched(String timeDispatched) {
		this.timeDispatched = timeDispatched;
	}
	public String getTimeArrived() {
		return timeArrived;
	}
	public void setTimeArrived(String timeArrived) {
		this.timeArrived = timeArrived;
	}
	public String getTimeCleared() {
		return timeCleared;
	}
	public void setTimeCleared(String timeCleared) {
		this.timeCleared = timeCleared;
	}
	public int getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(int complaintType) {
		this.complaintType = complaintType;
	}
	public int getCaller() {
		return caller;
	}
	public void setCaller(int caller) {
		this.caller = caller;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getDispatcher() {
		return dispatcher;
	}
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getIncidentNumber() {
		return incidentNumber;
	}
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}
	public ArrayList<String> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}
	public ArrayList<String> getOfficers() {
		return officers;
	}
	public void setOfficers(ArrayList<String> officers) {
		this.officers = officers;
	}
	public int getIsIncident() {
		return isIncident;
	}
	public void setIsIncident(int isIncident) {
		this.isIncident = isIncident;
	}
	public int getIsTrafficStop() {
		return isTrafficStop;
	}
	public void setIsTrafficStop(int isTrafficStop) {
		this.isTrafficStop = isTrafficStop;
	}
}
