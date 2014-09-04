package dominoes;

//import other classes
import dominoes.Driver;
import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.DominoUI;
import dominoes.Table;
import dominoes.players.DominoPlayer;

/**
 * Class GameUI implements all the methods of the 
 * DominioUI.It introduces two fields 'round'  and 'roundWinnner'
 * and overrides methods of the DominoUI that update and display 
 * the game.
 *  
 * 
 * @author David Sajld
 * username dsajdl01
 * @version 31/01/2014
 */
public class GameUI implements DominoUI {
		public static int round;
		public static String roundWinner;
		public static int setPoints;
		public Driver dr;
	
	 /**
	 * Constructor 
	 */
	public GameUI(){
		dr = new Driver();
		round = 0;
		roundWinner ="";
		setPoints = dr.getScore();
	}
	
	/**
     * displays players' names, their points and the table
     * 
     * @param 	players is an object of DominoPlaye class
     * @param	table is an object of Table class
     * @param	boneyard on an object of BoneYard class    
     */
    @Override
    public void display(DominoPlayer[] players, Table table, BoneYard boneyard) {
  
                boolean firstRound = true;
                boolean tableToPrint = true;
                
                // check if the game is not interrupted  
                for(DominoPlayer dmp : players){
                	if(dmp.getPoints() > 999){
                		tableToPrint = false;
                	}
                }
                
               
                if(tableToPrint){
                			
		                	
                			// display player details and their scores 	
			                if(firstRound){
			                    firstRound = false;
			                    System.out.println("\n****************************************************************************");
			                    System.out.println("The game ends at " + setPoints + " points.");
			                    for (DominoPlayer dp : players){
			                        	System.out.println("Player / Score:  "+dp  + "|" +dp.getPoints()  );
			                     }
			                    System.out.println("****************************************************************************");
			                }
			                
			             // display current round 
			                if(round > 0){
			                	System.out.println("\t\t\t" +"Round "+ round + " Winner: "+ roundWinner +"\n");
			                }
			                System.out.println();
			                
			                // display bones on the table
			                if(tableToPrint){
					                System.out.println("On table");     
					                for (Bone b : table.layout()){
					                    System.out.print(" <| " + b.left() + " | " + b.right() + " |> ");
					                }
					                System.out.println("\n");
					                System.out.println("Number of bones left in the yard: " + boneyard.size());          
			                }
                }
    }
    

    /**
     *display message of invalid play when player plays incorrect bone
     * 
     * @param  player is an object of DominoPlayer class
     */
    @Override
    public void displayInvalidPlay(DominoPlayer player) {
        System.out.println("\n\t\t*************************************");
        System.out.println("\t\t*** Invalid play " + player.getName() + " try again! ***");
        System.out.println("\t\t*************************************");
    }

    /**
     * displays the round winner
     * 
     * @param  player is an object of DominoPlayer class  
     */
    @Override
    public void displayRoundWinner(DominoPlayer player) {
    		round++;
    		roundWinner = player.getName();
        	System.out.println("\n\n\n\t\t***********************************");
        	System.out.println("\t\t*** "+ roundWinner +" wins round " + round + " ***\n");
        	System.out.print("\t\t***********************************\n");
    }
} 