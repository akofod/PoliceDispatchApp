package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Location;

public class LocationDAO extends BaseDAO {
	
	public int addLocation(Location location) {
		
		String sql = "INSERT INTO upd_dispatch.location (address) VALUES (?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, location.getAddress());
			
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
	
	public Location getLocation(int locationId) {
		
		Location location = null;
		String sql = "SELECT * FROM upd_dispatch.location WHERE location_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, locationId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	location = new Location();
            	location.setLocationId(rs.getInt("location_id"));;
            	location.setAddress(rs.getString("address"));
            	location.setNumberCalls(rs.getInt("number_calls"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return location;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Location getLocationByAddress(String address) {
		
		Location location = null;
		String sql = "SELECT * FROM upd_dispatch.location WHERE address = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, address);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	location = new Location();
            	location.setLocationId(rs.getInt("location_id"));;
            	location.setAddress(rs.getString("address"));
            	location.setNumberCalls(rs.getInt("number_calls"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return location;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateLocation(int locationId, Location location) {
		
		String sql = "UPDATE upd_dispatch.location SET address = ?, number_calls = ?"
				+ " WHERE location_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, location.getAddress());
			ps.setInt(2, location.getNumberCalls());
			ps.setInt(3, locationId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteLocation(int locationId) {
		
		String sql = "DELETE FROM upd_dispatch.location WHERE location_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, locationId);

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
