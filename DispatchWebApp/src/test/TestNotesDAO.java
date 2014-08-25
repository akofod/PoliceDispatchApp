package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import model.Notes;

import org.junit.Test;

import dao.NotesDAO;

public class TestNotesDAO {

	@Test
	public void test() {
		
		NotesDAO dao = new NotesDAO();
		
		Notes note = new Notes();
		note.setCallRecord(1);
		note.setNote("This is a test note.");
		
		assertEquals("Failure adding note", 1, dao.addNote(note));
		
		ArrayList<Notes> nl = dao.getNotesForCall(1);
		
		assertEquals("Returned incorrect call record", 1, nl.get(0).getCallRecord());
		assertEquals("Returned incorrect note", "This is a test note.", nl.get(0).getNote());
		
		note.setNoteId(nl.get(0).getNoteId());
		note.setNote("Second test note.");
		
		assertEquals("Failure updating note", 1, dao.updateNote(note.getNoteId(), note));
		
		Notes n = dao.getNote(note.getNoteId());
		
		assertEquals("Returned incorrect call record", 1, n.getCallRecord());
		assertEquals("Returned incorrect note", "Second test note.", n.getNote());
		
		assertEquals("Failure deleting note", 1, dao.deleteNote(n.getNoteId()));
		
		assertNull("Note was not deleted successfully", dao.getNote(n.getNoteId()));
	}

}
