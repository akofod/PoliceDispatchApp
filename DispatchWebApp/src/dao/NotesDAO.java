package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Notes;

public class NotesDAO extends BaseDAO {
	
	public int addNote(Notes note) {

		String sql = "INSERT INTO upd_dispatch.notes (call_record,"
                + "note) VALUES (?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, note.getCallRecord());
			ps.setString(2, note.getNote());
			
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
	
	public Notes getNote(int noteId) {
		
		Notes note = null;
		String sql = "SELECT * FROM upd_dispatch.notes WHERE note_id = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, noteId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	note = new Notes();
            	note.setCallRecord(rs.getInt("call_record"));;
            	note.setNote(rs.getString("note"));
            }
            rs.close();
            ps.close();
            con.close();
            
            return note;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public ArrayList<Notes> getNotesForCall(int callRecordId) {
		ArrayList<Notes> notes = null;
		String sql = "SELECT * FROM upd_dispatch.notes WHERE call_record = ?";
		
		try {
			Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, callRecordId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	notes = new ArrayList<Notes>();
            	Notes note = new Notes();
            	note.setNoteId(rs.getInt("note_id"));
            	note.setCallRecord(rs.getInt("call_record"));;
            	note.setNote(rs.getString("note"));
            	
            	notes.add(note);
            }
            rs.close();
            ps.close();
            con.close();
            
            return notes;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int updateNote(int noteId, Notes note) {
		
		String sql = "UPDATE upd_dispatch.notes SET call_record = ?, note = ?"
				+ " WHERE note_id = ?";
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, note.getCallRecord());
			ps.setString(2, note.getNote());
			ps.setInt(3, noteId);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILURE;
		}
	}
	
	public int deleteNote(int noteId) {
		
		String sql = "DELETE FROM upd_dispatch.notes WHERE note_id = ?";

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, noteId);

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
