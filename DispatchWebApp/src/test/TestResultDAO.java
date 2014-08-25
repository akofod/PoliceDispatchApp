package test;

import static org.junit.Assert.*;
import model.Result;
import model.Vehicle;

import org.junit.Test;

import dao.ResultDAO;
import dao.VehicleDAO;

public class TestResultDAO {

	@Test
	public void test() {
		
		ResultDAO dao = new ResultDAO();
		
		Result result = new Result();
		result.setResult("Test");
		
		assertEquals("Failure adding result", 1, dao.addResult(result));
		
		Result r = dao.getResultByName("Test");
		
		assertEquals("Returned incorrect result", "Test", r.getResult());
		assertEquals("Returned incorrect number of occurences", 0, r.getNumberOccurences());
		
		result.setResultId(r.getResultId());
		result.setResult("TestAgain");
		result.setNumberOccurences(2);
		
		assertEquals("Failure updating result", 1, dao.updateResult(result.getResultId(), result));
		
		r = dao.getResult(result.getResultId());
		
		assertEquals("Returned incorrect result", "TestAgain", r.getResult());
		assertEquals("Returned incorrect number of occurences", 2, r.getNumberOccurences());
		
		assertEquals("Failure deleting result", 1, dao.deleteResult(r.getResultId()));
		
		assertNull("Result was not deleted successfully", dao.getResult(r.getResultId()));
	}

}
