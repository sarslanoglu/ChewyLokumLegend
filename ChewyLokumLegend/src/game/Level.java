package game;
/**
 * Level is an object class 
 * that holds level requirement score, swap amounts and time
 */


public class Level {
	private Board board;
	private int levelNumber;
	private int swapAmount;
	private int specialSwapAmount;
	private int levelRequirementScore;
	private int time;
	
	/**
	 * @param levelNumber			the number of the level
	 * @param swapAmount 			the normal swap given for the level
	 * @param specialSwapAmount 	the special swap given for the level
	 * @param levelRequirementScore the score of the level requirement.
	 * @param time 					the time given for the level
	 */
	public Level (Board board,int levelNumber,int swapAmount,int specialSwapAmount,int levelRequirementScore, int time) {
		this.levelRequirementScore = levelRequirementScore;
		this.levelNumber = levelNumber;
		this.swapAmount = swapAmount;
		this.specialSwapAmount = specialSwapAmount;
		this.time = time;
		this.board = board;
		/**
		 *  Default constructor of Level.
		 *  Constructs a level object with
		 *  its required score, swap amounts, time and its number.
		 */
	}

	/**
	 * 
	 * @returns true if level is timeBased (time!=0)
	 */
	public boolean isTimeBased(){
		if (time == 0){
			
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * @return Returns the required score for the level.
	 */
	public int getlevelRequirementScore(){
		return levelRequirementScore; 
	}
	/**
	 * @return Returns the level number.
	 */
	public int getlevelNumber(){
		return levelNumber;
	}
	/**
	 * @return Returns the swap amounts.
	 */
	public int getswapAmount(){
		return swapAmount;
	}
	/**
	 * @return Returns the special swap amounts.
	 */
	public int getSpecialSwapAmount(){
		return specialSwapAmount;
	}
	/**
	 * @return Returns the time.
	 */
	public int getTime(){
		return time;
	}
	public Board getBoard(){
		return board;
	}
}
	