package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Person;

public class PersonDAO extends BaseDAO {
	
	public int addPerson(Person person) {
		
		String sql = "INSERT INTO upd_dispatch.person (first_name,"
                + "last_name, phone_number) VALUES (?, ?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getPhoneNumber());
			
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
	
	public Person getPerson(int personId) {
		
		Person person = null;
		String sql = "SELECT * FROM upd_dispatch.person WHERE person_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, personId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	person = new Person();
            	person.setPersonId(rs.getInt("person_id"));;
            	person.setFirstName(rs.getString("first_name"));
            	person.setLastName(rs.getString("last_name"));
            	person.setPhoneNumber(rs.getString("phone_number"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return person;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Person getPersonByName(String firstName, String lastName) {
		
		Person person = null;
		String sql = "SELECT * FROM upd_dispatch.person WHERE first_name = ? AND last_name = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	person = new Person();
            	person.setPersonId(rs.getInt("person_id"));;
            	person.setFirstName(rs.getString("first_name"));
            	person.setLastName(rs.getString("last_name"));
            	person.setPhoneNumber(rs.getString("phone_number"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return person;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updatePerson(int personId, Person person) {
		
		String sql = "UPDATE upd_dispatch.person SET first_name = ?, last_name = ?,"
				+ " phone_number = ? WHERE person_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getPhoneNumber());
			ps.setInt(4, personId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deletePerson(int personId) {
		
		String sql = "DELETE FROM upd_dispatch.person WHERE person_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, personId);

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
