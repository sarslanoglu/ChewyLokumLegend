package game;

/**
 * 
 * normalLokum is an object
 * extends lokum and has a color. 
 *
 */

public class normalLokum extends Lokum {

	private String color;
	private boolean isTimeLokum;
	/**
	 * 	
	 * @param color  the color of the lokum.
	 */
 

	public normalLokum (int x, int y,String color, boolean timeLokum) {
		super(x, y);
		this.color = color;
		isTimeLokum = timeLokum;
		/**
		 * Default construction of normalLokum.
		 * Constructs the normalLokum object 
		 * with color.
		 */
	}

	public String getColor() {
		return color;
	}
	public boolean isSpecial() {
		return false;
	}

	public String getType() {
		return "NotSpecial";
	}

	@Override
	public boolean isEqual(Lokum l) {
		// TODO Auto-generated method stub
		if(l.getColor().equals(color)){
			return true;
		}
		return false;
	}
	
	public boolean isTimeLokum(){
		if(isTimeLokum == true){
			return true;
		}
		return false;
	}

	@Override
	public void setType(String s) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean repOk(){
		if(super.getPositionX()<0 || super.getPositionY()<0 
				|| !(color.equals("W") || color.equals("B") || color.equals("G") || 
				color.equals("R") || color.equals("NULL"))){
			return false;
		}
		return true;
	}
	
	public String toString(){
		String result = "";
		result +="normalLokum.toString(): \n";
		result +="Positions: \n";
		result +="X:" + super.getPositionX();
		result +=" Y:" + super.getPositionY();
		result +="\nColor:" + color;
		return result;
	}
	
}
