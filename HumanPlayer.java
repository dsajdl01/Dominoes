package dominoes.players;

//import java library
import java.util.ArrayList;
import java.util.Scanner;

//import other classes
import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.Play;
import dominoes.Table;

/**
 * Class HumanPlayer implements all the methods of the DominoPlayer interface. 
 * It introduces three fields 'name', 'inHand'  and 'points'
 * and overrides methods of the DominoPlayer that the players implement.
 *  
 * 
 * 
* @author David Sajdl
 * username dsajdl01
 * @version 31/01/2014
 */
public class HumanPlayer implements DominoPlayer {
	private String name;
    private ArrayList<Bone> inHand;
    private int points;

    /**
     * Constructor
     */
    public HumanPlayer(){
        inHand = new ArrayList<Bone>();
    }
    
    /**
     *creates a new object of type array representing the players bones in hand
     * 
     * @return Bone[] in the player's hand
     */
    @Override
    public Bone[] bonesInHand() {
    	Bone[] bn = new Bone[inHand.size()]; 
        return inHand.toArray(bn);
    }
    
    /**
     * picks a bone from the boneyard and adds to the bones in hand 
     * 
     * @param  boneyard is an object of the class BoneYard
     */
    @Override
    public void draw(BoneYard boneyard) {
        Bone b = boneyard.draw();
        inHand.add(b);
    }
    
    /**
     * returns the player's name 
     * 
     * @return name that is the name of the player
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * returns the number of points that the player has 
     *
     * @return points that the player has
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * allows the player to pick a bone to play
     * 
     * @param   table that is taken from the Table class providing the current table configuration
     * @return  p is an object of the Player class that holds 
     * the value of the bones which the current player has picked
     * @throws CantPlayException  when the player has no more bones left to play
     */
    @Override
    public Play makePlay(Table table) throws CantPlayException {
    
    	// displays left and right of the table
        System.out.println("Left of table is " + table.left());
        System.out.println("Right of table is " + table.right());
        Play p = null;
        
        // while it is valid play the game continues else throws CantPlayException
        boolean isNum = true;
        while(isNum){
            displayWhatisInHand();
            System.out.print("Enter the bone you wish to "
            		+ "play or enter 0 to pick up a bone from the "
            		+ "boneyard and pass the game to the other player.\nEnter 100 to end the game: ");
         try{
            int pick = getInputNumber();
             isNum = false;
             
             // when the player has no bones or pass the game to the other player
             if(pick == 0){
                 throw new CantPlayException();
             }
             
             //when the player want to finish the game
             if(pick == 100){
            	 for(int i = 0; i <= 6; i++){
            		if(i == table.left()){
            			 Bone s = new Bone(i,i);
            			 p = new Play(s, Play.LEFT); 
            		 }
            	 }
             } 
             else{
                Bone b = inHand.remove(pick - 1);
                	// check if picked bone can be played
                    if (b.right() == table.left()|| (b.left() == table.left())||b.left() == table.right()||b.right() == table.right()){
                        
                    	// check if the bone at hand matches either right of left of the table
                        if((b.right() == table.right() || b.right() == table.left())
                        &&(b.left() == table.right() || b.left() == table.left())
                        && b.left() != b.right()){
                                boolean rL;
                                
                                	// if the bone at hand can play either side of the table prompt user to choose
                                    do{
                                       rL = false;
                                    int side = playRightOrLeft();
                                    if(side == 0){
                                        if (b.right() == table.left()|| (b.left() == table.left())){
                                            p = new Play(b, Play.LEFT);
                                        }
                                   }
                                   else if(side == 1){
                                        if (b.left() == table.right()||(b.right() == table.right())){
                                            p = new Play(b, Play.RIGHT);
                                        }
                                    }
                                    else{
                                        System.out.println("Invalid entry. Please try again!");
                                        rL = true;
                                    }
                                }while(rL);
                        }
                        else{
                        	// if only one side of the bone at hand matches that of the table 
                             if (b.left() == table.right()||(b.right() == table.right())){
                                            p = new Play(b, Play.RIGHT);
                              }
                             else if (b.right() == table.left()|| (b.left() == table.left())){
                                            p = new Play(b, Play.LEFT);
                                        }
                        }
               }
               else{
            	  // invalid play  
                 System.out.println("\n");
            	   p = new Play(b, Play.LEFT);
                }
            } 
        }
         // invalid input caught with IllegalArgumentExcpetion 
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
          isNum = true;
        }
     } 
        return p;
    }
    
    /**
     * clears player's hand   
     */
    @Override
    public void newRound() {
    	inHand.clear();
    }
    /**
     * returns the number of bones the player has 
     * 
     * @return inHand.size() size of the bone array in the player's hand
     */
    @Override
    public int numInHand() {
        return inHand.size();
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
        this.points = points;
    }

    /**
     * adds bone back to the player's hand 
     * 
     * @param  bone that is the bone played incorrectly by the player
     */
    @Override
    public void takeBack(Bone bone) {
        inHand.add(bone);
    }
    /**
     * represents the current object
     * 
     * @return a string that is the player's name
     */
    @Override
    public String toString() {
        return this.getName();
    }
    
    /**
     * displays bones that are in the player's hand
     * 
     *
     */
    private void displayWhatisInHand(){
	    System.out.println("\n" +getName() + "'s hand: ");
	    int i = 1;
	    for(@SuppressWarnings("unused") Bone num : bonesInHand()){
	        System.out.print("   "+ i + "   ");
	        i++;
	    }
	    System.out.println();
	    for(Bone b: bonesInHand()){
	        System.out.print(" |"+ b.left()+"|"+ b.right() + "| ");}
	    System.out.println();
    }
    
    /**
     * allows the player to pick a bone to play
     *
     *
     * @return result the player's input
     * @throws illegalArgumentException  if no number is entered
     */
    @SuppressWarnings("resource")
    private int getInputNumber(){
        Scanner inSc = new Scanner(System.in);
       while(true){
           if(!inSc.hasNextInt())
           {throw new IllegalArgumentException("\""+ inSc.nextLine() + "\" is not a valid pick. Please "
           		+ "enter the position of the bone you wish to play.  ");}
           int result =  inSc.nextInt(); 
           
           // player ends the game
           if(result == 100){
        	   setPoints(1000);
        	   newRound();
        	   return result;
           }
           else if(result < 0 || result > inHand.size()){
                     System.out.print("\""+ result + "\" is invalid play. Please try again!  ");
                }
            else{
                return result;
            }
        }
    }
    
    /**
     * allows the player to  play right or left
     * 
     * @return result the player's input
     */
    private int playRightOrLeft(){
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        System.out.print("Press 0 if you want to play to the LEFT\nor "
        		+ "press 1 if you want to play to the RIGHT of the table ");
        while(true){
            if(in.hasNextInt()){
                int result = in.nextInt();
                return result;
            }
            else{
                System.out.print("\""+in.nextLine() + "\" + in not an integer. Please try again: "); 
                in.nextLine();
            }
        }
    }
}