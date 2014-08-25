package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Result;

public class ResultDAO extends BaseDAO {
	
	public int addResult(Result result) {
		
		String sql = "INSERT INTO upd_dispatch.result (result) VALUES (?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, result.getResult());
			
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
	
	public Result getResult(int resultId) {
		Result result = null;
		String sql = "SELECT * FROM upd_dispatch.result WHERE result_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, resultId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	result = new Result();
            	result.setResultId(rs.getInt("result_id"));
            	result.setResult(rs.getString("result"));
            	result.setNumberOccurences(rs.getInt("number_occurences"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return result;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Result getResultByName(String res) {
		Result result = null;
		String sql = "SELECT * FROM upd_dispatch.result WHERE result = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, res);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	result = new Result();
            	result.setResultId(rs.getInt("result_id"));
            	result.setResult(rs.getString("result"));
            	result.setNumberOccurences(rs.getInt("number_occurences"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return result;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateResult(int resultId, Result result) {
		
		String sql = "UPDATE upd_dispatch.result SET result = ?, number_occurences = ? WHERE result_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, result.getResult());
			ps.setInt(2, result.getNumberOccurences());
			ps.setInt(3, resultId);

			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteResult(int resultId) {
		
		String sql = "DELETE FROM upd_dispatch.result WHERE result_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, resultId);

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
