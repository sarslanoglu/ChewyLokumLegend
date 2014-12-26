package game;
/**
 * Level is an object class 
 * that holds level requirement score, swap amounts and time
 */


public class Level {
	private int levelNumber;
	private int swapAmount;
	private int specialSwapAmount;
	private int levelRequirementScore;
	private long time;

	/**
	 * @param levelRequirementScore     the score of the level requirement.  
	 */
	public Level (int levelNumber,int swapAmount,int specialSwapAmount,int levelRequirementScore, long time) {
		this.levelRequirementScore = levelRequirementScore;
		this.levelNumber = levelNumber;
		this.swapAmount = swapAmount;
		this.specialSwapAmount = specialSwapAmount;
		this.time = time;
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
	public int getlevelRequirementScore(){
		return levelRequirementScore; 
	}
	public int getlevelNumber(){
		return levelNumber;
	}
	public int getswapAmount(){
		return swapAmount;
	}
	public int getSpecialSwapAmount(){
		return specialSwapAmount;
	}
	public long getTime(){
		return time;
	}
}
	