package test;

import db.DBConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DBConnectivityTest {
	private DBConnection con = null;

	@Before
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}


}
