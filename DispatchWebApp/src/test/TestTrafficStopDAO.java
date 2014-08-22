package test;

import static org.junit.Assert.*;
import model.TrafficStop;
import model.Vehicle;

import org.junit.Test;

import dao.TrafficStopDAO;
import dao.VehicleDAO;

public class TestTrafficStopDAO {

	@Test
	public void test() {
		TrafficStopDAO dao = new TrafficStopDAO();
		
		TrafficStop ts = new TrafficStop();
		ts.setVehicle("ABC1235");
		ts.setOperator(1);
		ts.setCallRecordId(1);
		
		assertEquals("Failure adding traffic stop", 1, dao.addTrafficStop(ts));
		
		TrafficStop stop = dao.getTrafficStop(1);
		
		assertEquals("Returned incorrect vehicle id", "ABC1235", stop.getVehicle());
		assertEquals("Returned incorrect operator id", 1, stop.getOperator());
		assertEquals("Returned incorrect call record id", 1, stop.getCallRecordId());
		
		ts.setVehicle("ABC1236");
		ts.setOperator(2);
		
		assertEquals("Failure updating traffic stop", 1, dao.updateVehicle(1, ts));
		
		stop = dao.getTrafficStop(1);
		
		assertEquals("Returned incorrect vehicle id", "ABC1236", stop.getVehicle());
		assertEquals("Returned incorrect operator id", 2, stop.getOperator());
		assertEquals("Returned incorrect call record id", 1, stop.getCallRecordId());
		
		assertEquals("Failure deleting vehicle", 1, dao.deleteVehicle(1));
		
		assertNull("Dispatcher was not deleted successfully", dao.getTrafficStop(1));
	}
}
