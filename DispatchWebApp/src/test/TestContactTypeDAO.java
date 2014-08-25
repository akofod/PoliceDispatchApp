package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.ContactType;

import org.junit.Test;

import dao.ContactTypeDAO;

public class TestContactTypeDAO {

	@Test
	public void test() {
		
		ContactTypeDAO dao = new ContactTypeDAO();
		
		ContactType type = new ContactType();
		type.setType("Test Type");
		
		assertEquals("Failure adding contact type", 1, dao.addContactType(type));
		
		ContactType t = dao.getContactTypeByName("Test Type");
		
		assertEquals("Returned incorrect type", "Test Type", t.getType());
		
		type.setContactTypeId(t.getContactTypeId());
		type.setType("Another Type");
		
		assertEquals("Failure updating contact type", 1, dao.updateContactType(type.getContactTypeId(), type));
		
		t = dao.getContactType(type.getContactTypeId());
		
		assertEquals("Returned incorrect contact type", "Another Type", t.getType());
		
		assertEquals("Failure deleting contact type", 1, dao.deleteContactType(t.getContactTypeId()));
		
		assertNull("Contact type was not deleted successfully", dao.getContactType(t.getContactTypeId()));
	}

}
