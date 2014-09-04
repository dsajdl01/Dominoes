package dominoes.players;

//import other classes
import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.Play;
import dominoes.Table;

//import java library
import java.util.ArrayList;

/**
 * Class ComputerPlayer implements all the methods of the DominoPlayer interface. 
 * It introduces three fields 'name', 'inTheHand'  and 'points'
 * and overrides methods of the DominoPlayer that the players implement.
 *  
 * 
 * 
* @author David Sajdl
 * username dsajdl01
 * @version 31/01/2014
 */
public class ComputerPlayer implements DominoPlayer {
    private String name;
    private int points;
    private ArrayList<Bone> inTheHands;
    
    /**
     * Constructor 
     */
    public ComputerPlayer(){
        inTheHands = new ArrayList<Bone>();
    }
      
    /**
     *creates a new object of type array list representing the bones the Computer player holds 
     * 
     * @return Bone[] the computer player's bone array
     */
    @Override
    public Bone[] bonesInHand() {
        Bone[] ba = new Bone[inTheHands.size()];
        return inTheHands.toArray(ba);
    }
    
    /**
     * picks a bone from the boneyard and adds to the player's hand
     * 
     * @param  bY is an object of the class BoneYard
     */
    @Override
    public void draw(BoneYard bY) {
        Bone b = bY.draw();
        inTheHands.add(b);
    }

    /**
     * returns the computer player's name 
     * 
     * @return name that is the name of the player
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns the number of points that the player has 
     *
     * @return points that the player has
     */
    @Override
    public int getPoints() {
        return points;
    }
    
    /**
     * allows the player to pick a bone to play
     * 
     * @param   table that is taken from the Table class providing the current table configuration
     * @return  p is an object of the Player class that holds 
     * the value of the bones which the current player has picked
     * @throws CantPlayException  when the player has no more bones left to play
     */
	@SuppressWarnings("unused")
	@Override
    public Play makePlay(Table table) throws CantPlayException {
        
		// displays left and right of the table
		//	System.out.println("Left of table is " + table.left());
		//  System.out.println("Right of table is " + table.right());
        
		Play p = null;
        displayWhatIsInHand();
        
        // scan through bone array and pick the bone to play
         for(Bone b : bonesInHand()){
                    if (b.right() == table.left() || b.left() == table.left()){
                        inTheHands.remove(b);
                        p = new Play(b, Play.LEFT);
                        printBonesInHands();
                        return p;
                    }
                    else if (b.left() == table.right()|| b.right() == table.right()){
                        inTheHands.remove(b);
                        p = new Play(b, Play.RIGHT);
                        printBonesInHands();
                        return p;
                    }
              }
         // if can not play  throw an exception
     if (p == null) {
    	 throw new CantPlayException();
     }
     return p;   
    }  
	
	/**
     * clears player's hand   
     */
    @Override
    public void newRound() {
        inTheHands.clear();
    }

    /**
     * returns the number of bones the player has 
     * 
     * @return inTheHand.size() size of the bone array in the computer player
     */
    @Override
    public int numInHand() {
         return inTheHands.size();
    }

    
    /**
     * sets the player's name
     * 
     * @param  name  the player's name
     */
    @Override
    public void setName(String name) {
       this.name = name;
    }

    /**
     *sets the number of points the player has
     * 
     * @param  points number of points the player has
     */
    @Override
    public void setPoints(int points) {
        this.points += points;
    }

    /**
     * adds bone back to the player's hand 
     * 
     * @param  bn that is the bone played incorrectly by the player
     */
    @Override
    public void takeBack(Bone bn) {
        inTheHands.add(bn);
    }
    
    /**
     * represents the current object
     * 
     * @return a string that is the player's name
     */
    @Override
    public String toString (){
        return this.getName();
    }
  
    /**
     * displays bones that are in the player's hand
     * 
     *
     */
    private void displayWhatIsInHand(){
        System.out.println(getName()+ "'s hand: ");
       for(Bone b: bonesInHand()){
            System.out.print("  |" + b.left()+" | "+ b.right()+"|  ");
       }
      System.out.println(); 
     }
   
    /**
     * display number of the bones that computer holds
     * 
     *
     */
    private void printBonesInHands(){
    	if(inTheHands.size() == 0){
    		System.out.println("\n" + getName() + " has no bones to play");
    	}
    	else if(inTheHands.size() == 1){
        	System.out.println("\n"+ getName() + " holds " + inTheHands.size() + " bone");
    	}
        else{
        	System.out.println("\n"+ getName() + " holds " + inTheHands.size() + " bones");
        }
    }
}
