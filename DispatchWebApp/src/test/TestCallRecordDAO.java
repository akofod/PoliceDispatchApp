package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.CallRecord;

import org.junit.Test;

import dao.CallRecordDAO;

public class TestCallRecordDAO {

	@Test
	public void test() {
		
		CallRecordDAO dao = new CallRecordDAO();
		
		CallRecord call = new CallRecord();
		call.setMannerReceived(1);
		call.setCallDate("2014-08-25");
		call.setCallShift(3);
		call.setTimeReceived("22:14");
		call.setTimeDispatched("22:15");
		call.setTimeArrived("22:19");
		call.setTimeCleared("23:14");
		call.setComplaintType(16);
		call.setCaller(1);
		call.setLocation(1);
		call.setDispatcher("C");
		call.setResult(3);
		call.setIncidentNumber("14-2344-08");
		call.setIsTrafficStop(0);
		call.setIsIncident(1);
		
		assertEquals("Failure adding call", 1, dao.addCallRecord(call));
		
		CallRecord cr = dao.getCallRecordByDateTime("2014-08-25", "22:14");
		
		assertEquals("Returned incorrect manner received", 1, cr.getMannerReceived());
		assertEquals("Returned incorrect call date", "2014-08-25", cr.getCallDate());
		assertEquals("Returned incorrect call shift", 3, cr.getCallShift());
		assertEquals("Returned incorrect time received", "22:14:00", cr.getTimeReceived());
		assertEquals("Returned incorrect time dispatched", "22:15:00", cr.getTimeDispatched());
		assertEquals("Returned incorrect time arrived", "22:19:00", cr.getTimeArrived());
		assertEquals("Returned incorrect time cleared", "23:14:00", cr.getTimeCleared());
		assertEquals("Returned incorrect complaint type ", 16, cr.getComplaintType());
		assertEquals("Returned incorrect caller", 1, cr.getCaller());
		assertEquals("Returned incorrect location", 1, cr.getLocation());
		assertEquals("Returned incorrect dispatcher", "C", cr.getDispatcher());
		assertEquals("Returned incorrect result", 3, cr.getResult());
		assertEquals("Returned incorrect incident number", "14-2344-08", cr.getIncidentNumber());
		assertEquals("Returned incorrect isTrafficStop", 0, cr.getIsTrafficStop());
		assertEquals("Returned incorrect isIncident", 1, cr.getIsIncident());
		
		call.setMannerReceived(2);
		call.setCallDate("2014-08-24");
		call.setCallShift(2);
		call.setTimeReceived("08:14");
		call.setTimeDispatched("08:15");
		call.setTimeArrived("08:19");
		call.setTimeCleared("09:14");
		call.setComplaintType(36);
		call.setDispatcher("D3");
		call.setResult(1);
		call.setIncidentNumber("14-2345-08");
		
		assertEquals("Failure updating call record", 1, dao.updateCallRecord(cr.getCallRecordId(), call));
		
		cr = dao.getCallRecord(cr.getCallRecordId());
		
		assertEquals("Returned incorrect manner received", 2, cr.getMannerReceived());
		assertEquals("Returned incorrect call date", "2014-08-24", cr.getCallDate());
		assertEquals("Returned incorrect call shift", 2, cr.getCallShift());
		assertEquals("Returned incorrect time received", "08:14:00", cr.getTimeReceived());
		assertEquals("Returned incorrect time dispatched", "08:15:00", cr.getTimeDispatched());
		assertEquals("Returned incorrect time arrived", "08:19:00", cr.getTimeArrived());
		assertEquals("Returned incorrect time cleared", "09:14:00", cr.getTimeCleared());
		assertEquals("Returned incorrect complaint type ", 36, cr.getComplaintType());
		assertEquals("Returned incorrect caller", 1, cr.getCaller());
		assertEquals("Returned incorrect location", 1, cr.getLocation());
		assertEquals("Returned incorrect dispatcher", "D3", cr.getDispatcher());
		assertEquals("Returned incorrect result", 1, cr.getResult());
		assertEquals("Returned incorrect incident number", "14-2345-08", cr.getIncidentNumber());
		assertEquals("Returned incorrect isTrafficStop", 0, cr.getIsTrafficStop());
		assertEquals("Returned incorrect isIncident", 1, cr.getIsIncident());
		
		assertEquals("Failure deleting call record", 1, dao.deleteCallRecord(cr.getCallRecordId()));
		
		assertNull("Call record was not deleted successfully", dao.getCallRecord(cr.getCallRecordId()));
	}

}
