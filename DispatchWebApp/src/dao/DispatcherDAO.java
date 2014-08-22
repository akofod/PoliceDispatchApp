package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Dispatcher;


public class DispatcherDAO extends BaseDAO {	
	
	public int addDispatcher(Dispatcher dispatcher) {
		
		// Check to ensure that the unit number is not already in use
		
		if (getDispatcher(dispatcher.getUnitNumber()) != null) {
			return ALREADY_EXISTS;
		}
		
		String sql = "INSERT INTO upd_dispatch.dispatcher (unit_number, first_name,"
                + "last_name, phone_number, email, password, salt) VALUES"
                + "(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            String salt = getSalt();
            String hashPass = getSecurePassword(dispatcher.getPassword(), salt);
            
            ps.setString(1, dispatcher.getUnitNumber());
            ps.setString(2, dispatcher.getFirstName());
            ps.setString(3, dispatcher.getLastName());
            ps.setString(4, dispatcher.getPhoneNumber());
            ps.setString(5, dispatcher.getEmail());
            ps.setString(6, hashPass);
            ps.setString(7, salt);
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return SUCCESS;
			
		}
		catch (Exception e) {
			System.out.println(e);
            return FAILURE;
		}
	}
	
	private String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
	}
	
	private String getSecurePassword(String password, String salt) {
		String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}

	public Dispatcher getDispatcher(String unit_number) {
		
		Dispatcher dispatcher = null;
		
		String sql = "SELECT * FROM upd_dispatch.dispatcher " + "WHERE unit_number = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unit_number);

            ResultSet rs = ps.executeQuery();
            dispatcher = new Dispatcher();

            while (rs.next()) {
            	dispatcher.setUnitNumber(rs.getString("unit_number"));
            	dispatcher.setFirstName(rs.getString("first_name"));
            	dispatcher.setLastName(rs.getString("last_name"));
            	dispatcher.setPhoneNumber(rs.getString("phone_number"));
            	dispatcher.setEmail(rs.getString("email"));
            	dispatcher.setPassword(rs.getString("password"));
            	dispatcher.setSalt(rs.getString("salt"));
            }
            rs.close();
            ps.close();
            con.close();
            
            if(dispatcher.getUnitNumber() == null) {
            	return null;
            }
            else {
            	return dispatcher;
            }
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public int updateDispatcher(String unitNumber, Dispatcher dispatcher) {
		
		String sql = "UPDATE upd_dispatch.dispatcher SET first_name = ?, last_name = ?,"
				+ " phone_number = ?, email = ? WHERE unit_number = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dispatcher.getFirstName());
			ps.setString(2, dispatcher.getLastName());
			ps.setString(3, dispatcher.getPhoneNumber());
			ps.setString(4, dispatcher.getEmail());
			ps.setString(5, unitNumber);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteDispatcher(String unitNumber) {
		
		String sql = "DELETE FROM upd_dispatch.dispatcher WHERE unit_number = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, unitNumber);

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
