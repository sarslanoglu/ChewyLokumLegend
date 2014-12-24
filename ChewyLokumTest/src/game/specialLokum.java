package game;
/**
 * specialLokum extends lokum and has a type and color.
 *
 */
public class specialLokum extends Lokum {

	private String type;
	private String color;


	/**
	 * 
	 * @param type 		type of the specialLokum.
	 * @param color		color of the specialLokum.
	 */


	public specialLokum (int x, int y, String color, String type) {

		super(x, y);

		this.color = color;
		this.type= type;

		/**
		 * Default construction of the specialLokum. 
		 * Constructs the specialLokum object with a 
		 * specific type on its own and  color. 
		 */
	}


	public String getColor() {

		return color;
	}
	public String getType() {
		return type;
	}
	public void setType (String type) {
		this.type = type;
	}
	public boolean isSpecial() {
		return true;
	}


	public boolean isEqual(Lokum l) {
		if(l.getColor().equals(color)){
			return true;
		}
		return false;
	}


	public boolean isTimeLokum() {
		return false;
	}
	public boolean repOk(){
		if(super.getPositionX()<0 || super.getPositionY()<0 
				|| 
				!(color.equals("W") || color.equals("B") || color.equals("G") || 
						color.equals("R") || color.equals("NULL")) 
						||
						(!(type.equals("HStriped") || type.equals("VStriped") 
								|| type.equals("Wrapped") || type.equals("BOMB")))){
			return false;
		}
		return true;
	}
	public String toString(){
		String result = "";
		result +="specialLokum.toString(): \n";
		result +="Positions: \n";
		result +="X:" + super.getPositionX();
		result +=" Y:" + super.getPositionY();
		result +="\nColor:" + color;
		result +="\nType:" + type;
		return result;
	}
}
