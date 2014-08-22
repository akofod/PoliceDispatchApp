package model;

public class Complaint {
	private int complaintId;
	private String complaint;
	private String code;
	private int numberOccurences;
	
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNumberOccurences() {
		return numberOccurences;
	}
	public void setNumberOccurences(int numberOccurences) {
		this.numberOccurences = numberOccurences;
	}
}
