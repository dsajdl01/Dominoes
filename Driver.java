package dominoes;

//import other classes
import dominoes.players.ComputerPlayer;
import dominoes.players.DominoPlayer;
import dominoes.players.HumanPlayer;

//import java library
import java.util.Scanner;

/**
 * The class displays the players, their points and final score. 
 * Provides the winner. 
 *  
 * @author David Sajdl
 * username dsajdl01
 * @version 31/01/2014
 */
public class Driver {
		private static int p;
	 /**
	  * Prompts the player to choose; 
	  * - to be a computer or human player
	  * - to set the score for the game to end.
	  * - Creates a new instance for the  Domino UI. 
	  *  
	  *  @param args
     */
    public static void main(String[] args) {
        
    	//display the game instructions
        System.out.println("\t\t\tD.O.M.I.N.O.\n");
        System.out.println("The game starts with introducing two players; "
        		+ "of type human and of type computer.\nA player can play against "
        		+ "another player or against the computer or the computer\ncan play "
        		+ "against itself.\n\nThe objective of each player is to clear their "
        		+ "hands of the bones first.\nThe bones remaining in the hands of the "
        		+ "player that has failed to clear hands on\nthat round, will count "
        		+ "towards the points of the winning player.\n\nThe player that reaches "
        		+ "the initially set game score first will win the game.\n ");    
  
        Scanner inSc = new Scanner(System.in);
        boolean newGame = true;
        
        do{
    	
    	if(newGame){
				        DominoPlayer pl1;
				        DominoPlayer pl2;
				       
				        // ask user if first player would be PC or human player 
				        System.out.print("\nSet the first player: \nPress 1 if you want to play the human."
				        		+ "\nPress 0 if you want to play the computer. ");
				            int num = getNumber();
				            if(num == 0){
				                pl1 = new ComputerPlayer();
				                pl1.setName("Computer");
				            }
				            else{
				                pl1 = new HumanPlayer();
				                System.out.print("Enter name: ");
				                String name = inSc.nextLine();
				                pl1.setName(name);
				            }
				            
				        // ask user if second player would be PC or human player
				            System.out.print("\nSet the second player: \nPress 1 if you want to play the human."
					        		+ "\nPress 0 if you want to play the computer. ");
				            num = getNumber();
				            if(num == 0){
				                pl2 = new ComputerPlayer();
				                pl2.setName("PC");
				            }
				            else{
				                pl2 = new HumanPlayer();
				                System.out.print("Enter name: ");
				                String name = inSc.nextLine();
				                pl2.setName(name);
				               
				            }
				       // ask user to set the score to win the game 
				       int score =  getFinalPoints();
				       setScore(score);
				       
				        Dominoes d = new Dominoes(new GameUI(), pl1, pl2, score, 6);
				        DominoPlayer winner = d.play();
				        
				        
				        // display the winner and the score for each player or interrupt the game
				        if(pl1.getPoints() < 999 && pl2.getPoints() < 999){
				        	System.out.println("\n"+pl2.getName() + " scores " + pl2.getPoints() 
				        			+" and "+ pl1.getName() + " scores " + pl1.getPoints());
				        	System.out.println("\n\t\t*****************************");
				        	System.out.println("\t\t*** WINNER IS " + winner + "! ***");
				        	System.out.println("\t\t*****************************\n");
				        	// Ask user to initiate the game
				            System.out.print("\n\nPlease press Y to start a new game or N to finish playing the game.  ");
				            newGame = getPlay();
				        }
				        // if game is interrupted 
				        else{
				        	System.out.println("\n\n\n\t\t********************************");
				        	System.out.println("\t\t*** THE GAME WAS INTERRUPTED ***");
				        	System.out.println("\t\t********************************\n\n");
				        	// Ask user to initiate the game
				            System.out.print("\nPlease press Y to start a new game or N to finish playing the game.  ");
				            newGame = getPlay();
				        }
    	}
    }while(newGame);
        System.out.println("\n\nGAME OVER");
        inSc.close();
    }
    
    
    /**
     * method prompts the user to set an integer value to end the game   
     * 
     * @return num integer value to end the game
     */
    private static int getFinalPoints(){
    @SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
   	 int num = 0;
   	 boolean condition = true;
   	 while(condition){
   		 System.out.print("\nSet points to end the game. "
   		 		+ "\nEnter a number between 30 and 200 : " );
   		 if(scanner.hasNextInt()){
   			 num = scanner.nextInt();
   			 if(num >= 30 && num <= 200){
   				 condition = false;
   			 }
   			 else{condition = true;}
   		 }
   		 else{
   			 System.out.print("\""+scanner.nextLine() + "\" + in not an integer. Please try again: "); 
   		 }
   	 }
   	 return num;
    }
    
    /**
     * sets the player as human or as computer
     *  
     * @return result the user input
     */
      private static int getNumber(){
    	@SuppressWarnings("resource")
    	Scanner in = new Scanner(System.in);
        boolean condition = true;
        int result;
        while(true){
            if(in.hasNextInt()){
                result = in.nextInt();
                condition = checkNumber(result);
                if(condition){
                    return result;
                }
                else{
                    System.out.println("Please enter either 0 or 1 to continue.");
                }
            }
            else{
            	String str = in.nextLine();
                System.out.print("\n " + str + " is not a number. Please enter either 0 or 1 to continue. ");
            }
        }
    }
      
      /**
       * check parameter value
       * if value is 1 or 0 method returns true otherwise return false  
       * 
       * @param  n is number (user integer input)
       * @return true or false 
       */
    private static boolean checkNumber(int n){
        if(n == 0 || n == 1){
            return true;}
        return false;
    }
    
    /**
     * Allow the player to start the game or to finish the game. 
     * 
     * @return true if the player enters Y else false if the player wants to finish playing.  
     */
    private static boolean getPlay(){
    	@SuppressWarnings("resource")
    	Scanner read = new Scanner(System.in);
    	boolean isChar = true;
    	boolean result = false;
    	do{
    		String str = read.nextLine();
    		if(str.length()==1){
    			if(str.equals("n") || str.equals("N")){
    				result = false;
    				isChar = false;
    			}
    			else if(str.equals("y") || str.equals("Y")){
    				result = true;
    				isChar = false;
    			}
    			else{
    				isChar = true;
    				System.out.println("Please enter either Y or N to continue. ");
    			}
    		}
    		else{
    			isChar = true;
    			System.out.println("Please enter either Y or N to continue. ");
    		}
    	}while(isChar);

    	return result;
    }
    /**
     * set final score to win the game
     *
     * @param s is an integer of the final score to win the game
     */
    public static void setScore(int s){
    	p = s;
    }
    /**
     * get final score to win the game
     *
     * @return p as an integer of final score to win the game
     */
    public int getScore(){
    	return p;
    }
}

