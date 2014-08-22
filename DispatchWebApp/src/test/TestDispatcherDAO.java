package test;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Dispatcher;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import dao.DispatcherDAO;

public class TestDispatcherDAO {
	
	@BeforeClass
    public static void setUpClass() throws Exception {
        // setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, 
                "org.apache.naming");            
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");
           
            // Construct DataSource
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/upd_dispatch");
            ds.setUser("dispatch_app");
            ds.setPassword("!Utica734$");
            
            ic.bind("java:/comp/env/jdbc/DispatchDB", ds);
        } catch (NamingException ex) {
            System.out.print(ex);
        }
        
    }
	
	@Test
	public void test() {
		
		DispatcherDAO dao = new DispatcherDAO();
		
		if(dao.getDispatcher("TE") != null) {
			dao.deleteDispatcher("TE");
		}
		
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setUnitNumber("TE");
		dispatcher.setFirstName("Test");
		dispatcher.setLastName("Dispatcher");
		dispatcher.setPhoneNumber("123-456-7890");
		dispatcher.setEmail("test@villageofutica.com");
		dispatcher.setPassword("UticaPolice");
		
		assertEquals("Failure adding dispatcher", 1, dao.addDispatcher(dispatcher));
		
		Dispatcher d = dao.getDispatcher("TE");
		
		assertEquals("Returned incorrect unit number", "TE", d.getUnitNumber());
		assertEquals("Returned incorrect first name", "Test", d.getFirstName());
		assertEquals("Returned incorrect last name", "Dispatcher", d.getLastName());
		assertEquals("Returned incorrect phone number", "123-456-7890", d.getPhoneNumber());
		assertEquals("Returned incorrect email ", "test@villageofutica.com", d.getEmail());
		
		dispatcher.setFirstName("Tested");
		dispatcher.setLastName("Disp");
		dispatcher.setPhoneNumber("098-765-4321");
		dispatcher.setEmail("retest@villageofutica.com");
		
		assertEquals("Failure updating dispatcher", 1, dao.updateDispatcher("TE", dispatcher));
		
		d = dao.getDispatcher("TE");
		
		assertEquals("Returned incorrect unit number", "TE", d.getUnitNumber());
		assertEquals("Returned incorrect first name", "Tested", d.getFirstName());
		assertEquals("Returned incorrect last name", "Disp", d.getLastName());
		assertEquals("Returned incorrect phone number", "098-765-4321", d.getPhoneNumber());
		assertEquals("Returned incorrect email ", "retest@villageofutica.com", d.getEmail());
		
		assertEquals("Failure deleting dispatcher", 1, dao.deleteDispatcher("TE"));
		
		assertNull("Dispatcher was not deleted successfully", dao.getDispatcher("TE"));
	}

}
