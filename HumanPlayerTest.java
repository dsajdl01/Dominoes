package dominoes.players;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.Play;
import dominoes.Table;

/**
 * The test class for the implementation of DominoPlayer
 * through the ComputerPlayer class.
 * 
 * @author David Sajdl
 * username: dsajdl01
 * 
 * @version 16/02/2014
 */

public class HumanPlayerTest {
	private DominoPlayer dp;
	private Bone bn;
	private Play pl;
	private Table tb;
	private int one;
	private int three;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	/**
     *  Set instance variables and mock objects
     */
	@Before
	public void setUp() throws Exception {
		dp = new ComputerPlayer();
		bn = mock(Bone.class);
		tb = mock(Table.class);
		pl = mock(Play.class);
		one = 1;
		three = 3;
	}

	/**
	 * @throws java.lang.Exception
	 *
     *  resets objects to null after the CumputerPlayerTest Class is executed 
     */
	@After
	public void tearDown() throws Exception {
		dp = null;
		bn = null;
		tb = null;
		pl = null;
	}
	/**
     * Tests getPoints method if points have any value
     *
     */
	@Test
	public void testGetPoints(){
		dp.setPoints(24);
		assertNotNull(dp.getPoints());
	}
	
	/**
     * Tests getPoints method if returns the set points correctly
     *
     * 
     **/
	@Test
	public void testGetPointsToEqual(){
		dp.setPoints(24);
		dp.setPoints(6);
		int points = 30;
		assertEquals("Wrong answer ! ",points, dp.getPoints());
	}
	
	/**
     * Tests if setName method return set names 
     * 
     *      
     */
	@Test
	public void testSetName(){
		String s = "freed";
		dp.setName(s);
		assertEquals("Wronr answer ! ",s, dp.getName());
	}
	
	
	
	/**
     * Tests getName method to ensure a name is inserted
     * 
     * 
     */
	@Test
	public void testGetName() {
		dp.setName("fred");
		assertNotNull(dp.getName());
	}
	
	/**
     * Tests setPoints method to return progressive 
     * points for the player
     *
     *
     **/
	@Test
	public void testSetPoints(){
		int s = 4;
		dp.setPoints(one);
		dp.setPoints(three);
		assertEquals("Wrong answer ! ",s, dp.getPoints());
	}

	/**
     * Tests draw method for correct number of bones 
     * being added from the BoneYard to the players' hand 
     *
     **/
	@Test
	public void testDraw(){
		BoneYard size = new BoneYard(three);
		assertEquals("Wrong answer !", 10 ,size.size());
		assertEquals("Wrong answer !", 0, dp.numInHand());
		dp.draw(size);
		dp.draw(size);
		assertEquals("Wrong answer !",8,size.size());
		assertEquals("Wrong answer !",2, dp.numInHand());
	}
	
	/**
     * Tests numInHand method for the number of bones 
     * in players hand
     * 
     * 
     **/
	@Test
	public void testNumInHand(){
		assertEquals(0,dp.numInHand());
		BoneYard by = new BoneYard(6);
		dp.draw(by);
		assertEquals("Wrong answer !",one,dp.numInHand());
		dp.draw(by);
		dp.draw(by);
		assertEquals("Wrong answer !",three,dp.numInHand());
	}
	
	/**
     * Tests takeBack method
     * if bone is added back to the player's hand
     * 
     * 
     **/
	@Test
	public void testTakeBack(){
		assertEquals(0,dp.numInHand());
		dp.takeBack(bn);
		assertEquals("Wrong answer !",one, dp.numInHand()); 
		dp.takeBack(bn);
		dp.takeBack(bn);
		assertEquals("Wrong answer ! ",three,dp.numInHand());
	}
	
	/**
     * Tests newRound method
     * to check if it clears the hand 
     * 
     **/
	@Test
	public void testNewRound(){
		int zero = 0;
		assertEquals("Wrong answer ! ",zero,dp.numInHand());
		BoneYard by = new BoneYard(three);
		dp.draw(by);
		dp.draw(by);
		dp.draw(by);
		assertEquals(three,dp.numInHand());
		dp.newRound();
		assertEquals("Wrong answer ! ",zero,dp.numInHand());
	}
	
	/**
     * Tests boneInHand method to see if 
     * it creates an empty array
     *
     **/
	@Test
	public void testBoneInHand(){
		assertNotNull(dp.bonesInHand());
	}
	
	/**
	 * Tests if it returns the player's name
	 */
	@Test
	public void textToString(){
		dp.setName("Computer");
		assertEquals("Computer",dp.toString());
	}
	
	/**
     * Tests makePlayException throws CantPlayException
     * If invalid bones is played it throws CantPlayException
     * 
     * 
     **/
	@Test (expected=CantPlayException.class)
	public void testMakePlayExceptionNegatie() throws CantPlayException{
		Bone bns = new Bone(3,4);
		dp.takeBack(bns);
		when(tb.right()).thenReturn(2);
		pl = dp.makePlay(tb);
		
	}
	/**
     * Tests MakePlayException method when correct bone is played 
     * Correct one held on the Right of the Bone
     * 
     **/
	@SuppressWarnings("static-access")
	@Test
	public void testMakePlayExceptionRight() throws CantPlayException{
		when(bn.right()).thenReturn(6);
		when(tb.right()).thenReturn(6);
		dp.takeBack(bn);
		pl = dp.makePlay(tb);
		assertEquals("Wrong answer ! ",one, pl.RIGHT);
	}
	
	/**
     * Tests MakePlayException method when correct bone is played 
     * Correct one held on the Left of the Bone
     * 
     **/
	@SuppressWarnings("static-access")
	@Test
	public void testMakePlayExceptionLeft() throws CantPlayException{
		when(bn.left()).thenReturn(4);
		when(tb.right()).thenReturn(4);
		dp.takeBack(bn);
		pl = dp.makePlay(tb);
		assertEquals("Wrong answer ! ",0,pl.LEFT);
				
	}
	/**
     * Tests MakePlayException method when correct bone is played 
     * and return value is not null
     * 
     **/
	@SuppressWarnings("static-access")
	@Test
	public void testMakePlayExceptionEqualToNull() throws CantPlayException{
		when(bn.left()).thenReturn(4);
		when(tb.right()).thenReturn(4);
		dp.takeBack(bn);
		pl = dp.makePlay(tb);
		assertNotNull(pl.LEFT);
				
	}
}
