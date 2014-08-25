package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ContactType;

public class ContactTypeDAO extends BaseDAO {
	
	public int addContactType(ContactType type) {
		
		String sql = "INSERT INTO upd_dispatch.contact_type (type) VALUES (?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, type.getType());
			
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
	
	public ContactType getContactType(int typeId) {
		
		ContactType type = null;
		String sql = "SELECT * FROM upd_dispatch.contact_type WHERE contact_type_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, typeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	type = new ContactType();
            	type.setContactTypeId(rs.getInt("contact_type_id"));
            	type.setType(rs.getString("type"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return type;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public ContactType getContactTypeByName(String typ) {
		ContactType type = null;
		String sql = "SELECT * FROM upd_dispatch.contact_type WHERE type = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, typ);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	type = new ContactType();
            	type.setContactTypeId(rs.getInt("contact_type_id"));
            	type.setType(rs.getString("type"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return type;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateContactType(int typeId, ContactType type) {
		
		String sql = "UPDATE upd_dispatch.contact_type SET type = ? WHERE contact_type_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, type.getType());
			ps.setInt(2, typeId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteContactType(int typeId) {
		
		String sql = "DELETE FROM upd_dispatch.contact_type WHERE contact_type_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, typeId);

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
