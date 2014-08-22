package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Dispatcher;

/**
 * Data Access Object for the database dispatcher table.
 * Extends the BaseDAO class.
 * @author William Kofod
 *
 */
public class DispatcherDAO extends BaseDAO {	
	
	/**
	 * Adds a new dispatcher to the database.
	 * @param dispatcher The Dispatcher to add
	 * @return ALREADY_EXISTS(-1) if the dispatcher already exists
	 * 		   SUCCESS(1) if the dispatcher was added successfully
	 *         FAILURE(0) if the dispatcher was not added successfully
	 */
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
	
	/**
	 * Gets a random salt to encrypt the dispatcher's password
	 * @return A String containing the random salt
	 * @throws NoSuchAlgorithmException
	 */
	private String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
	}
	
	
	/**
	 * Uses the password entered by the dispatcher and the generated
	 * salt to create an encrypted password.
	 * @param password The password entered by the user
	 * @param salt The random salt
	 * @return The encrypted password to store in the database
	 */
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

	/**
	 * Retrieve a Dispatcher from the database
	 * @param unit_number The unit number of the dispatcher to be retrieved
	 * @return The Dispatcher object, or null if the dispatcher does not exist in the database
	 */
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
	
	/**
	 * Update a dispatcher record in the database.
	 * @param unitNumber The unit number of the dispatcher to be updated
	 * @param dispatcher The Dispatcher object containing the data to update
	 * @return SUCCESS(1) if the record is updated successfully or FAILURE(0) if the record is not updated
	 */
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
	
	/**
	 * Delete a dispatcher from the database
	 * @param unitNumber The unit number of the dispatcher to be deleted
	 * @return SUCCESS(1) if the record was deleted successfully or FAILURE(0) if the record was not deleted.
	 */
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
