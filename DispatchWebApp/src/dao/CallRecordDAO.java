package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.CallRecord;

public class CallRecordDAO extends BaseDAO {
	
	public int addCallRecord(CallRecord call) {
		
		String sql = "INSERT INTO upd_dispatch.call_record (manner_received, call_date, "
				+ "call_shift, time_received, time_dispatched, time_arrived, time_cleared, "
				+ "complaint_type, caller, location, dispatcher, result, "
				+ "incident_number, traffic_stop, incident) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, call.getMannerReceived());
			ps.setString(2, call.getCallDate());
			ps.setInt(3, call.getCallShift());
			ps.setString(4, call.getTimeReceived());
			ps.setString(5, call.getTimeDispatched());
			ps.setString(6, call.getTimeArrived());
			ps.setString(7, call.getTimeCleared());
			ps.setInt(8, call.getComplaintType());
			ps.setInt(9, call.getCaller());
			ps.setInt(10, call.getLocation());
			ps.setString(11, call.getDispatcher());
			ps.setInt(12, call.getResult());
			ps.setString(13, call.getIncidentNumber());
			ps.setInt(14, call.getIsTrafficStop());
			ps.setInt(15, call.getIsIncident());
			
			ps.executeUpdate();
			ps.close();
			con.close();
			return SUCCESS;
		}
		catch(Exception e) {
			System.out.println(e);
            return FAILURE;
		}
	}
	
	public CallRecord getCallRecord(int callRecordId) {
		
		CallRecord call = null;
		String sql = "SELECT * FROM upd_dispatch.call_record WHERE call_record_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, callRecordId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	call = new CallRecord();
            	call.setCallRecordId(rs.getInt("call_record_id"));
            	call.setMannerReceived(rs.getInt("manner_received"));
            	call.setCallDate(rs.getDate("call_date").toString());
            	call.setCallShift(rs.getInt("call_shift"));
            	call.setTimeReceived(rs.getTime("time_received").toString());
            	call.setTimeDispatched(rs.getTime("time_dispatched").toString());
            	call.setTimeArrived(rs.getTime("time_arrived").toString());
            	call.setTimeCleared(rs.getTime("time_cleared").toString());
            	call.setComplaintType(rs.getInt("complaint_type"));
            	call.setCaller(rs.getInt("caller"));
            	call.setLocation(rs.getInt("location"));
            	call.setDispatcher(rs.getString("dispatcher"));
            	call.setResult(rs.getInt("result"));
            	call.setIncidentNumber(rs.getString("incident_number"));
            	call.setIsTrafficStop(rs.getInt("traffic_stop"));
            	call.setIsIncident(rs.getInt("incident"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return call;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public CallRecord getCallRecordByDateTime(String date, String timeReceived) {
		
		CallRecord call = null;
		String sql = "SELECT * FROM upd_dispatch.call_record WHERE call_date = ? AND time_received = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, timeReceived);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	call = new CallRecord();
            	call.setCallRecordId(rs.getInt("call_record_id"));
            	call.setMannerReceived(rs.getInt("manner_received"));
            	call.setCallDate(rs.getDate("call_date").toString());
            	call.setCallShift(rs.getInt("call_shift"));
            	call.setTimeReceived(rs.getTime("time_received").toString());
            	call.setTimeDispatched(rs.getTime("time_dispatched").toString());
            	call.setTimeArrived(rs.getTime("time_arrived").toString());
            	call.setTimeCleared(rs.getTime("time_cleared").toString());
            	call.setComplaintType(rs.getInt("complaint_type"));
            	call.setCaller(rs.getInt("caller"));
            	call.setLocation(rs.getInt("location"));
            	call.setDispatcher(rs.getString("dispatcher"));
            	call.setResult(rs.getInt("result"));
            	call.setIncidentNumber(rs.getString("incident_number"));
            	call.setIsTrafficStop(rs.getInt("traffic_stop"));
            	call.setIsIncident(rs.getInt("incident"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return call;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateCallRecord(int callRecordId, CallRecord call) {
		
		String sql = "UPDATE upd_dispatch.call_record SET manner_received = ?, call_date = ?, "
				+ "call_shift = ?, time_received = ?, time_dispatched = ?, time_arrived = ?, time_cleared = ?, "
				+ "complaint_type = ?, caller = ?, location = ?, dispatcher = ?, result = ?, "
				+ "incident_number = ?, traffic_stop = ?, incident = ? WHERE call_record_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, call.getMannerReceived());
			ps.setString(2, call.getCallDate());
			ps.setInt(3, call.getCallShift());
			ps.setString(4, call.getTimeReceived());
			ps.setString(5, call.getTimeDispatched());
			ps.setString(6, call.getTimeArrived());
			ps.setString(7, call.getTimeCleared());
			ps.setInt(8, call.getComplaintType());
			ps.setInt(9, call.getCaller());
			ps.setInt(10, call.getLocation());
			ps.setString(11, call.getDispatcher());
			ps.setInt(12, call.getResult());
			ps.setString(13, call.getIncidentNumber());
			ps.setInt(14, call.getIsTrafficStop());
			ps.setInt(15, call.getIsIncident());
			ps.setInt(16, callRecordId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteCallRecord(int callRecordId) {
		
		String sql = "DELETE FROM upd_dispatch.call_record WHERE call_record_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, callRecordId);

	        ps.execute();
	        ps.close();
	        con.close();
	        
	        return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
}
