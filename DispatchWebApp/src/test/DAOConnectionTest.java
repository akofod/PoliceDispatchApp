package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Dispatcher;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import dao.DispatcherDAO;

public class DAOConnectionTest {

	@BeforeClass
    public static void setUpClass() throws Exception {
        // rcarver - setup the jndi context and the datasource
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
	public void testConnection() {
		
		DispatcherDAO dao = new DispatcherDAO();
		
		Dispatcher d1 = dao.getDispatcher("C");
		
		assertNotNull("Did not return an object", d1);
		assertEquals("First name not returned correctly", "William", d1.getFirstName());
		assertEquals("Last name not returned correctly", "Kofod", d1.getLastName());
		assertEquals("Phone number did not return correctly", "740-501-7241", d1.getPhoneNumber());
		assertEquals("Email did not return correctly", "kofod@villageofutica.com", d1.getEmail());
		
	}

}
