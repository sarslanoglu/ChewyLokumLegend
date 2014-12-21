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
// Alternative toString() method
//	public String toString(){
//		String result = "";
//		result +="specialLokum.toString(): \n";
//		result +="Positions: \n";
//		result +="X:" + super.getPositionX();
//		result +=" Y:" + super.getPositionY();
//		result +="\nColor:" + color;
//		result +="\nType:" + type;
//		return result;
//	}
	public String toString(){
		return color + " " + type;
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
}
