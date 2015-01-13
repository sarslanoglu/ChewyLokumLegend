package game;

/**
 * 
 * Lokum is an abstract class that has 
 * a position X and position Y.

 *
 */

public abstract class Lokum {

	private int positionX;
	private int positionY;
	/**
	 * 
	 * 	
	 * @param x       x-coordinate of the lokum.
	 * @param y       y-coordinate of the lokum.
	 */

	public Lokum (int x, int y) {

		positionX = x;
		positionY = y;
		

		/**
		 * the default constructor of lokum.	
		 * Constucts the lokum object with 
		 * position x and position y.
		 */

	}

	public abstract String getColor();
	public abstract String getType(); 
	public abstract void setType(String s);
	public abstract boolean isSpecial();
	public abstract boolean isEqual(Lokum l); 
	public abstract boolean isTimeLokum();
	public abstract boolean repOk();
	
	/**
	 * @return Returns the X position of a lokum.
	 */
	public int getPositionX() {
		return positionX;
	}
	/**
	 * @param positionX
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	/**
	 * @return Returns the Y position of a lokum.
	 */
	public int getPositionY() {
		return positionY;
	}
	/**
	 * @param positionY
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
 
	

}
