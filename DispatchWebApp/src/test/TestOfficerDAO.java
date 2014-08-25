package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.Officer;

import org.junit.Test;

import dao.OfficerDAO;

public class TestOfficerDAO {

	@Test
	public void test() {
		
		OfficerDAO dao = new OfficerDAO();
		
		if(dao.getOfficer("TE") != null) {
			dao.deleteOfficer("TE");
		}
		
		Officer officer = new Officer();
		officer.setBadgeNumber("TE");
		officer.setFirstName("Test");
		officer.setLastName("Officer");
		officer.setPhoneNumber("123-456-7890");
		officer.setEmail("test@test.com");
		
		assertEquals("Failure adding officer", 1, dao.addOfficer(officer));
		
		Officer o = dao.getOfficer("TE");
		
		assertEquals("Returned incorrect badge number", "TE", o.getBadgeNumber());
		assertEquals("Returned incorrect first name", "Test", o.getFirstName());
		assertEquals("Returned incorrect last name", "Officer", o.getLastName());
		assertEquals("Returned incorrect phone number", "123-456-7890", o.getPhoneNumber());
		assertEquals("Returned incorrect email", "test@test.com", o.getEmail());

		officer.setFirstName("Another");;
		officer.setLastName("Police");
		officer.setPhoneNumber("098-765-4321");
		officer.setEmail("second@test.com");
		
		assertEquals("Failure updating officer", 1, dao.updateOfficer("TE", officer));
		
		o = dao.getOfficer("TE");
		
		assertEquals("Returned incorrect badge number", "TE", o.getBadgeNumber());
		assertEquals("Returned incorrect first name", "Another", o.getFirstName());
		assertEquals("Returned incorrect last name", "Police", o.getLastName());
		assertEquals("Returned incorrect phone number", "098-765-4321", o.getPhoneNumber());
		assertEquals("Returned incorrect email", "second@test.com", o.getEmail());
		
		assertEquals("Failure deleting officer", 1, dao.deleteOfficer("TE"));
		
		assertNull("Officer was not deleted successfully", dao.getOfficer("TE"));
	}

}
