/**
 * Level is an object class 
 * that holds the score of level requirements 
 * and gets the shape of lokums in level.
 * 
 *
 */


public class Level {
	private int levelNumber;
	private int swapAmount;
	private int levelRequirementScore;
	private long time;

	/**
	 * 
	 * @param levelRequirementScore     the score of the level requirement. 
	 * @param levelShape				Shape of lokums in level. 
	 */
	public Level (int levelNumber,int swapAmount,int levelRequirementScore, Lokum[][] levelShape, long time) {
		this.levelRequirementScore = levelRequirementScore;
		this.levelNumber = levelNumber;
		this.swapAmount = swapAmount;
		this.time = time;
		/**
		 *  Default constructor of Level.
		 *  Constructs a level object with
		 *  its required score and its shape of lokums.
		 */
	}
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
	public long getTime(){
		return time;
	}
}
	