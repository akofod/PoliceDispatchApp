package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Complaint;

public class ComplaintDAO extends BaseDAO {
	
	public int addComplaint(Complaint complaint) {
		
		String sql = "INSERT INTO upd_dispatch.complaint (complaint, code) VALUES (?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, complaint.getComplaint());
			ps.setString(2, complaint.getCode());
			
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
	
	public Complaint getComplaint(int complaintId) {
		Complaint complaint = null;
		String sql = "SELECT * FROM upd_dispatch.complaint WHERE complaint_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, complaintId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	complaint = new Complaint();
            	complaint.setComplaintId(rs.getInt("complaint_id"));
            	complaint.setComplaint(rs.getString("complaint"));
            	complaint.setCode(rs.getString("code"));
            	complaint.setNumberOccurences(rs.getInt("number_occurences"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return complaint;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Complaint getComplaintByName(String comp) {
		Complaint complaint = null;
		String sql = "SELECT * FROM upd_dispatch.complaint WHERE complaint = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comp);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	complaint = new Complaint();
            	complaint.setComplaintId(rs.getInt("complaint_id"));
            	complaint.setComplaint(rs.getString("complaint"));
            	complaint.setCode(rs.getString("code"));
            	complaint.setNumberOccurences(rs.getInt("number_occurences"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return complaint;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Complaint getComplaintByCode(String code) {
		Complaint complaint = null;
		String sql = "SELECT * FROM upd_dispatch.complaint WHERE code = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	complaint = new Complaint();
            	complaint.setComplaintId(rs.getInt("complaint_id"));
            	complaint.setComplaint(rs.getString("complaint"));
            	complaint.setCode(rs.getString("code"));
            	complaint.setNumberOccurences(rs.getInt("number_occurences"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return complaint;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateComplaint(int complaintId, Complaint complaint) {
		
		String sql = "UPDATE upd_dispatch.complaint SET complaint = ?, code = ?, "
				+ "number_occurences = ? WHERE complaint_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, complaint.getComplaint());
			ps.setString(2, complaint.getCode());
			ps.setInt(3, complaintId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteComplaint(int complaintId) {
		
		String sql = "DELETE FROM upd_dispatch.complaint WHERE complaint_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, complaintId);

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
