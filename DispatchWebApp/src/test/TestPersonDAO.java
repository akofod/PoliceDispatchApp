package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.Person;

import org.junit.Test;

import dao.PersonDAO;

public class TestPersonDAO {

	@Test
	public void test() {
		
		PersonDAO dao = new PersonDAO();
		
		Person person = new Person();
		person.setFirstName("Test");
		person.setLastName("Person");
		person.setPhoneNumber("123-456-7890");
		
		assertEquals("Failure adding person", 1, dao.addPerson(person));
		
		Person p = dao.getPersonByName("Test", "Person");
		
		assertEquals("Returned incorrect first name", "Test", p.getFirstName());
		assertEquals("Returned incorrect last name", "Person", p.getLastName());
		assertEquals("Returned incorrect phone number", "123-456-7890", p.getPhoneNumber());
		
		person.setFirstName("Another");;
		person.setLastName("Subject");
		person.setPhoneNumber("098-765-4321");
		
		assertEquals("Failure updating person", 1, dao.updatePerson(p.getPersonId(), person));
		
		p = dao.getPerson(p.getPersonId());
		
		assertEquals("Returned incorrect first name", "Another", p.getFirstName());
		assertEquals("Returned incorrect last name", "Subject", p.getLastName());
		assertEquals("Returned incorrect year", "098-765-4321", p.getPhoneNumber());
		
		assertEquals("Failure deleting person", 1, dao.deletePerson(p.getPersonId()));
		
		assertNull("Person was not deleted successfully", dao.getPerson(p.getPersonId()));
	}

}
