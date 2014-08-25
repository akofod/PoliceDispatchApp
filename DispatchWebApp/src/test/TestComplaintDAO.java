package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.Complaint;

import org.junit.Test;

import dao.ComplaintDAO;

public class TestComplaintDAO {

	@Test
	public void test() {
		
		ComplaintDAO dao = new ComplaintDAO();
		
		Complaint complaint = new Complaint();
		complaint.setComplaint("Run");
		complaint.setCode("975");
		
		assertEquals("Failure adding complaint", 1, dao.addComplaint(complaint));
		
		Complaint c = dao.getComplaintByCode("975");
		
		assertEquals("Returned incorrect complaint", "Run", c.getComplaint());
		assertEquals("Returned incorrect code", "975", c.getCode());
		assertEquals("Returned incorrect number of occurences", 0, c.getNumberOccurences());
		
		complaint.setComplaintId(c.getComplaintId());
		complaint.setComplaint("Run Faster");
		complaint.setCode("734");
		complaint.setNumberOccurences(2);
		
		assertEquals("Failure updating complaint", 1, dao.updateComplaint(complaint.getComplaintId(), complaint));
		
		c = dao.getComplaint(complaint.getComplaintId());
		
		assertEquals("Returned incorrect complaint", "Run Faster", c.getComplaint());
		assertEquals("Returned incorrect code", "734", c.getCode());
		assertEquals("Returned incorrect number of occurences", 2, c.getNumberOccurences());
		
		c = dao.getComplaintByName("Run Faster");
		
		assertEquals("Returned incorrect complaint", "Run Faster", c.getComplaint());
		assertEquals("Returned incorrect code", "734", c.getCode());
		assertEquals("Returned incorrect number of occurences", 2, c.getNumberOccurences());
		
		assertEquals("Failure deleting complaint", 1, dao.deleteComplaint(c.getComplaintId()));
		
		assertNull("Complaint was not deleted successfully", dao.getComplaint(c.getComplaintId()));
	}

}
