package game;
import java.util.ArrayList;

/**
 * 
 * Combination is a class that
 * checks for special lokums, holds 
 * an arraylist of lokums and
 * specifies the type of lokums.
 * 
 *
 */
public class Combination {


	private String type;
	private ArrayList<Lokum> Lokums;

	/**
	 * 
	 * @param type      the type of the combination
	 * @param Lokums    the list of the lokums that used in combination.
	 */


	public Combination( String type, ArrayList<Lokum> Lokums) {
		this.type = type;
		this.Lokums = Lokums;
		
		boolean validity = true;
		if(!isSpecial()){
			if(type.equals("3H") || type.equals("3V")){
				if(Lokums.size() != 3){
					validity=false;
				}
			}
			else if(type.equals("4H") || type.equals("4V")){
				if(Lokums.size() != 4){
					validity=false;
				}
			}
			else if(type.equals("5H") || type.equals("5V")){
				if(!(Lokums.size() == 5 || Lokums.size() == 6)){
					validity=false;
				}
			}
			for(int i=0; i<Lokums.size()-1; i++){
				if(!Lokums.get(i).isEqual(Lokums.get(i+1))){
					validity = false;
				}
			}
		}
		else{
			if(Lokums.size() != 2){
				validity = false;
			}
			else if(!Lokums.get(0).isSpecial() && !Lokums.get(1).isSpecial()){
				validity = false;
			}
		}
		if(validity == false){
			this.type = null;
			this.Lokums = null;
		}

		/**
		 * 	Default constructor of combination class.
		 *  Constructs a combination object with 
		 * a Lokums list, and a type in string.
		 * 
		 * 
		 */

	}

	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return checks size of lokums array list and returns true if size is exactly 2.
	 */
	public boolean isSpecial() {
		if(Lokums.size() == 2){
			return true;
		}
		return false;
	}

	public ArrayList<Lokum> getLokums() {
		return Lokums;

	}
	public boolean repOk(){
		if(Lokums.size() <= 1 || Lokums.size() > 6 || Lokums == null || type == null 
				|| 
				!(type.equals("3H") || type.equals("4H") || type.equals("5H")
						||  type.equals("3V") || type.equals("4V") || type.equals("5V")
						|| type.equals("Special") || type.equals("T"))){
			return false;
		}
		return true;
	}
	public String toString(){
		String result = "";
		result +="Combination type is:" + type +"\n";
		result += "Combination includes these lokums:\n";
		for(Lokum l : Lokums) {
			result+=l.toString();
			result+="\n";
		}
		if(isSpecial()){
			result+="and combination is special";
		}else{
			result+="and combination is not special";
		}
		return result;
	}
}
