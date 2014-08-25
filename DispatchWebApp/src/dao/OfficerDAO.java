package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Officer;

public class OfficerDAO extends BaseDAO {
	
	public int addOfficer(Officer officer) {
		
		String sql = "INSERT INTO upd_dispatch.officer (badge_number, first_name,"
                + "last_name, phone_number, email) VALUES (?, ?, ?, ?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, officer.getBadgeNumber());			
			ps.setString(2, officer.getFirstName());
			ps.setString(3, officer.getLastName());
			ps.setString(4, officer.getPhoneNumber());
			ps.setString(5, officer.getEmail());			
			
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
	
	public Officer getOfficer(String badgeNumber) {
		
		Officer officer = null;
		String sql = "SELECT * FROM upd_dispatch.officer WHERE badge_number = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, badgeNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	officer = new Officer();
            	officer.setBadgeNumber(rs.getString("badge_number"));;
            	officer.setFirstName(rs.getString("first_name"));
            	officer.setLastName(rs.getString("last_name"));
            	officer.setPhoneNumber(rs.getString("phone_number"));
            	officer.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return officer;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateOfficer(String badgeNumber, Officer officer) {
		
		String sql = "UPDATE upd_dispatch.officer SET first_name = ?, last_name = ?,"
				+ " phone_number = ?, email = ? WHERE badge_number = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, officer.getFirstName());
			ps.setString(2, officer.getLastName());
			ps.setString(3, officer.getPhoneNumber());
			ps.setString(4, officer.getEmail());
			ps.setString(5, badgeNumber);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteOfficer(String badgeNumber) {
		
		String sql = "DELETE FROM upd_dispatch.officer WHERE badge_number = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, badgeNumber);

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
