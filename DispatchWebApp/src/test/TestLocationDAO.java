package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.Location;

import org.junit.Test;

import dao.LocationDAO;

public class TestLocationDAO {

	@Test
	public void test() {
		
		LocationDAO dao = new LocationDAO();
		
		Location location = new Location();
		location.setAddress("123 Main St.");
		
		assertEquals("Failure adding location", 1, dao.addLocation(location));
		
		Location l = dao.getLocationByAddress("123 Main St.");
		
		assertEquals("Returned incorrect address", "123 Main St.", l.getAddress());
		assertEquals("Returned incorrect number of calls", 0, l.getNumberCalls());
		
		location.setAddress("54321 North St.");
		location.setNumberCalls(2);
		
		assertEquals("Failure updating location", 1, dao.updateLocation(l.getLocationId(), location));
		
		l = dao.getLocation(l.getLocationId());
		
		assertEquals("Returned incorrect address", "54321 North St.", l.getAddress());
		assertEquals("Returned incorrect number of calls", 2, l.getNumberCalls());
		
		assertEquals("Failure deleting location", 1, dao.deleteLocation(l.getLocationId()));
		
		assertNull("Location was not deleted successfully", dao.getLocation(l.getLocationId()));
	}

}
