package dominoes;


import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dominoes.players.ComputerPlayer;


/**
 * test class GameUI 
 *  
 * @author David Sajdl
 * username dsajdl01
 * @version 31/01/2014
 */
public class GameUITest
{
    
	private GameUI gUI;
	private ComputerPlayer dp1;
	private static final ByteArrayOutputStream  outContent = new ByteArrayOutputStream();
	/**
     * Default constructor for test class GameUITest
     */
    public GameUITest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    	gUI = new GameUI();
    	System.setOut(new PrintStream(outContent));
    	dp1 = new ComputerPlayer();
    }
    /**
     * Test out print contain set name.
     * 
     */

    @Test
    public void testDisplayInvalidPlay(){
    	dp1.setName("DS"); 
    	gUI.displayInvalidPlay(dp1);
    	assertEquals("\n\t\t*************************************\r\n\t\t*** Invalid play DS try again! ***\r\n\t\t*************************************\r\n",outContent.toString());
	    
    }
    
   
    
	/**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
    	dp1 = null;
    	;
    }
}

