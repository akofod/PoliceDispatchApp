package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Vehicle;

public class VehicleDAO extends BaseDAO {

	public int addVehicle(Vehicle vehicle) {
		
		String sql = "INSERT INTO upd_dispatch.vehicle (license_number,"
                + "owner, year, make, model, color) VALUES"
                + "(?, ?, ?, ?, ?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, vehicle.getLicenseNumber());
			ps.setInt(2, vehicle.getOwner());
			ps.setString(3, vehicle.getYear());
			ps.setString(4, vehicle.getMake());
			ps.setString(5, vehicle.getModel());
			ps.setString(6, vehicle.getColor());
			
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
	
	public Vehicle getVehicle(String licenseNumber) {
		
		Vehicle vehicle = null;
		String sql = "SELECT * FROM upd_dispatch.vehicle WHERE license_number = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, licenseNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	vehicle = new Vehicle();
            	vehicle.setLicenseNumber(rs.getString("license_number"));
            	vehicle.setOwner(rs.getInt("owner"));
            	vehicle.setYear(rs.getString("year"));
            	vehicle.setMake(rs.getString("make"));
            	vehicle.setModel(rs.getString("model"));
            	vehicle.setColor(rs.getString("color"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return vehicle;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateVehicle(String licenseNumber, Vehicle vehicle) {
		
		String sql = "UPDATE upd_dispatch.vehicle SET owner = ?, year = ?,"
				+ " make = ?, model = ?, color = ? WHERE license_number = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, vehicle.getOwner());
			ps.setString(2, vehicle.getYear());
			ps.setString(3, vehicle.getMake());
			ps.setString(4, vehicle.getModel());
			ps.setString(5, vehicle.getColor());
			ps.setString(6, licenseNumber);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteVehicle(String licenseNumber) {
		
		String sql = "DELETE FROM upd_dispatch.vehicle WHERE license_number = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, licenseNumber);

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
