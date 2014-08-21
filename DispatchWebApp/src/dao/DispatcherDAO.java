package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Dispatcher;


public class DispatcherDAO extends BaseDAO {
	
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
            
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return dispatcher;
	}
	
}
