package dominoes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test Driver class.
 * 
 * @author David Sajdl
 * username: dsajdl01
 * 
 * @version 16/02/2014
 */

public class DriverTest {
	
	Driver dr;

	@Before
	public void setUp() throws Exception {
		dr = new Driver();
	}

	@After
	public void tearDown() throws Exception {
		dr = null;
	}

	/**
	 * Tests score is set to a number to end the game
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testSetPoints() {
		dr.setScore(30);
		assertNotNull(dr.getScore());
	}
	/**
	 * Tests the score to end the game
	 * 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testGetPoints(){
		dr.setScore(50);
		assertEquals("Wrong answer ! ",50,dr.getScore());
	}

}
