package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.TrafficStop;

public class TrafficStopDAO extends BaseDAO {
	
	public int addTrafficStop(TrafficStop trafficStop) {
		
		String sql = "INSERT INTO upd_dispatch.traffic_stop"
                + "(vehicle, operator, call_record_id) VALUES"
                + "(?, ?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, trafficStop.getVehicle());
			ps.setInt(2, trafficStop.getOperator());
			ps.setInt(3, trafficStop.getCallRecordId());
			
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
	
	public TrafficStop getTrafficStop(int callRecordId) {
		
		TrafficStop ts = null;
		String sql = "SELECT * FROM upd_dispatch.traffic_stop WHERE call_record_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, callRecordId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	ts = new TrafficStop();
            	ts.setTrafficStopId(rs.getInt("traffic_stop_id"));
            	ts.setOperator(rs.getInt("operator"));
            	ts.setVehicle(rs.getString("vehicle"));
            	ts.setCallRecordId(rs.getInt("call_record_id"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return ts;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateVehicle(int callRecordId, TrafficStop ts) {
		
		String sql = "UPDATE upd_dispatch.traffic_stop SET vehicle = ?, operator = ? WHERE call_record_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ts.getVehicle());
			ps.setInt(2, ts.getOperator());
			ps.setInt(3, callRecordId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteVehicle(int callRecordId) {
		
		String sql = "DELETE FROM upd_dispatch.traffic_stop WHERE call_record_id = ?";

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
