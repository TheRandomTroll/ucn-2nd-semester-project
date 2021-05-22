package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import db.DBConnection;
import org.junit.Test;

class DBConnectivityTest {
	DBConnection con = null;

	/** Fixture for pay station testing. */
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
