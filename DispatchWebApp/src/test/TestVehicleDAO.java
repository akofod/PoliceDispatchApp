package test;

import static org.junit.Assert.*;
import model.Dispatcher;
import model.Vehicle;

import org.junit.Test;

import dao.DispatcherDAO;
import dao.VehicleDAO;

public class TestVehicleDAO {

	@Test
	public void test() {

		VehicleDAO dao = new VehicleDAO();
		
		if(dao.getVehicle("ABC1234") != null) {
			dao.deleteVehicle("ABC1234");
		}
		
		Vehicle vehicle = new Vehicle();
		vehicle.setLicenseNumber("ABC1234");
		vehicle.setOwner(1);
		vehicle.setYear("2014");
		vehicle.setMake("Ford");
		vehicle.setModel("Ranger");
		vehicle.setColor("Red");
		
		assertEquals("Failure adding vehicle", 1, dao.addVehicle(vehicle));
		
		Vehicle v = dao.getVehicle("ABC1234");
		
		assertEquals("Returned incorrect license number", "ABC1234", v.getLicenseNumber());
		assertEquals("Returned incorrect owner id", 1, v.getOwner());
		assertEquals("Returned incorrect year", "2014", v.getYear());
		assertEquals("Returned incorrect make", "Ford", v.getMake());
		assertEquals("Returned incorrect model ", "Ranger", v.getModel());
		assertEquals("Returned incorrect color", "Red", v.getColor());
		
		vehicle.setOwner(2);
		vehicle.setYear("2012");
		vehicle.setMake("Chevrolet");
		vehicle.setModel("Impala");
		vehicle.setColor("Gold");
		
		assertEquals("Failure updating vehicle", 1, dao.updateVehicle("ABC1234", vehicle));
		
		v = dao.getVehicle("ABC1234");
		
		assertEquals("Returned incorrect license number", "ABC1234", v.getLicenseNumber());
		assertEquals("Returned incorrect owner id", 2, v.getOwner());
		assertEquals("Returned incorrect year", "2012", v.getYear());
		assertEquals("Returned incorrect make", "Chevrolet", v.getMake());
		assertEquals("Returned incorrect model ", "Impala", v.getModel());
		assertEquals("Returned incorrect color", "Gold", v.getColor());
		
		assertEquals("Failure deleting vehicle", 1, dao.deleteVehicle("ABC1234"));
		
		assertNull("Vehicle was not deleted successfully", dao.getVehicle("ABC1234"));
	}

}
