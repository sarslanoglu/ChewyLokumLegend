package game;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/**
 *  Board is a class that holds lokums in two dimensional lokum array
 *  and it also operates logic actions(like fill empty spaces, eat, swap...) of game
 */
public class Board {

	private Lokum[][] lokumGrid;
	private int height;
	private int width;
	private int score;
	private int multiplier;
	private long time;
	private Lokum SLokum1;
	private Lokum SLokum2;
	/**
	 * 
	 * @param lokumGrid The two dimensional of lokum array that hold lokums in the board.
	 */
	public Board(int height,int width){
		
		if(0<height && height<30 && 0<width && width<30){
			this.lokumGrid = new Lokum[height][width];
			removeAll();
		}
		
		SLokum1 = new normalLokum(0,0,"NULL",false);
		SLokum2 = new normalLokum(0,0,"NULL",false);
		this.width = width;
		this.height = height;
		/**
		 *  The default constructor of board with lokumGrid .
		 *  
		 */
	}
	public int getScore(){
		return score;
	}
	public void setScore(int x){
		score = x;
	}
	public long getTime(){
		return time;
	}
	public void setTime(long x){
		time = x;
	}
	/**
	 * 
	 * @param c The combination which's score will be counted.
	 * @requires (Combination c).
	 * @return returns score of that combination.
	 */
	public int calculateCombinationScore(Combination c){
		int score = 0;
		ArrayList<Lokum> lokums = c.getLokums();
		if(c.isSpecial()){
			Lokum lokum1 = lokums.get(0);
			Lokum lokum2 = lokums.get(1);
			if(lokum1.getType().equals("HStriped") && lokum2.getType().equals("HStriped") ||
					lokum1.getType().equals("VStriped") && lokum2.getType().equals("VStriped")||
					lokum1.getType().equals("HStriped") && lokum2.getType().equals("VStriped")||
					lokum1.getType().equals("VStriped") && lokum2.getType().equals("HStriped"))
			{
				score += multiplier*((2*lokumGrid.length*60)+(2*lokumGrid[0].length*60));
			}
			else if(lokum1.getType().equals("Wrapped") && lokum2.getType().equals("HStriped")||
					lokum1.getType().equals("HStriped") && lokum2.getType().equals("Wrapped")||
					lokum1.getType().equals("Wrapped") && lokum2.getType().equals("VStriped")||
					lokum1.getType().equals("VStriped") && lokum2.getType().equals("Wrapped")){
				score += multiplier*((3*lokumGrid.length*60)+(3*lokumGrid[0].length*60));
			}
			else if(lokum1.getType().equals("BOMB") && lokum2.getType().equals("HStriped")||
					lokum1.getType().equals("HStriped") && lokum2.getType().equals("BOMB")
					)
			{
				score += multiplier*lokumGrid[0].length*lokumGrid[0].length*60;
			}
			else if(lokum1.getType().equals("VStriped") && lokum2.getType().equals("BOMB")||
					lokum1.getType().equals("BOMB") && lokum2.getType().equals("VStriped")){
				score += multiplier*lokumGrid.length*lokumGrid.length*60;
			}
			else if(lokum1.getType().equals("Wrapped") && lokum2.getType().equals("Wrapped")){
				score += multiplier*3600;
			}
			else if(lokum1.getType().equals("Wrapped") && lokum2.getType().equals("BOMB")||
					lokum1.getType().equals("BOMB") && lokum2.getType().equals("Wrapped")){
				int sameLokums = 0;
				if(lokum1.getType().equals("Wrapped")){
					for (int i = 0; i < lokumGrid.length; i++) {
						for (int j = 0; j < lokumGrid[0].length; j++) {
							if(lokum1.isEqual(lokumGrid[i][j])){
								sameLokums++;
							}
						}
					}
				}
				else if(lokum2.getType().equals("Wrapped")){
					for (int i = 0; i < lokumGrid.length; i++) {
						for (int j = 0; j < lokumGrid[0].length; j++) {
							if(lokum2.isEqual(lokumGrid[i][j])){
								sameLokums++;
							}
						}
					}
				}
				score += multiplier*2*sameLokums*sameLokums*60;
			}
			else if(lokum1.getType().equals("BOMB") && lokum2.getType().equals("BOMB")){
				score += multiplier*lokumGrid.length*
						lokumGrid[0].length*lokumGrid.length*lokumGrid[0].length*100;
			}
			else if(!lokum1.isSpecial() && lokum2.getType().equals("BOMB")){
				int sameLokums = 0;
				for (int i = 0; i < lokumGrid.length; i++) {
					for (int j = 0; j < lokumGrid[0].length; j++) {
						if(lokumGrid[i][j] != null){
							if(lokum1.isEqual(lokumGrid[i][j])){
								sameLokums++;
							}
						}
					}
				}
				score += score * sameLokums*sameLokums*60;	
			}
			else if(lokum1.getType().equals("BOMB") && !lokum2.isSpecial()){
				int sameLokums = 0;
				for (int i = 0; i < lokumGrid.length; i++) {
					for (int j = 0; j < lokumGrid[0].length; j++) {
						if(lokumGrid[i][j] != null){
							if(lokum2.isEqual(lokumGrid[i][j])){
								sameLokums++;
							}
						}
					}
				}
				score += score * sameLokums*sameLokums*60;
			}
		}else{
			if(c.getType().equals("3H")||c.getType().equals("3V")){
				score += multiplier*20*3;
				for(Lokum l : lokums){
					if(l.getType().equals("VStriped")){
						score += multiplier*60*lokumGrid.length;
					}
					else if(l.getType().equals("HStriped")){
						score += multiplier*60*lokumGrid[0].length;
					}
					else if(l.getType().equals("Wrapped")){
						score += multiplier * 1080;
					}
				}
			}
			if(c.getType().equals("4H")||c.getType().equals("4V")){
				score += multiplier*30*4;
				for(Lokum l : lokums){
					if(l.getType().equals("VStriped")){
						score += multiplier*60*lokumGrid.length;
					}
					else if(l.getType().equals("HStriped")){
						score += multiplier*60*lokumGrid[0].length;
					}
					else if(l.getType().equals("Wrapped")){
						score += multiplier * 1080;
					}
				}
			}
			if(c.getType().equals("5H")||c.getType().equals("5V") || c.getType().equals("T")){
				score += multiplier*40*5;
				for(Lokum l : lokums){
					if(l.getType().equals("VStriped")){
						score += multiplier*60*lokumGrid.length;
					}
					else if(l.getType().equals("HStriped")){
						score += multiplier*60*lokumGrid[0].length;
					}
					else if(l.getType().equals("Wrapped")){
						score += multiplier * 1080;
					}
				}
			}
		}
		return score;
	}
	/**
	 * 
	 * @param a First selected lokum for swap.
	 * @param b Second selected lokum for swap.
	 * @requires a!=null b!=null
	 * @modifies lokumGrid, a, b.
	 */
	public void swap (Lokum a, Lokum b){
		multiplier = 1;
		SLokum1 = a;
		SLokum2 = b;

		int ax = a.getPositionX();
		int ay = a.getPositionY();
		int bx = b.getPositionX();
		int by = b.getPositionY();

		Lokum temp = lokumGrid[ax][ay];
		lokumGrid[ax][ay] = lokumGrid[bx][by];
		lokumGrid[bx][by] = temp;

		b.setPositionX(ax);
		b.setPositionY(ay);
		a.setPositionX(bx);
		a.setPositionY(by);


	}
	/**
	 * 
	 * @param a First selected lokum for swap.
	 * @param b First selected lokum for swap.
	 * @requires a!=null b!=null
	 * @modifies lokumGrid, a, b.
	 */
	public void unswap(Lokum a,Lokum b){
		int ax = a.getPositionX();
		int ay = a.getPositionY();
		int bx = b.getPositionX();
		int by = b.getPositionY();

		Lokum temp = lokumGrid[ax][ay];
		lokumGrid[ax][ay] = lokumGrid[bx][by];
		lokumGrid[bx][by] = temp;

		b.setPositionX(ax);
		b.setPositionY(ay);
		a.setPositionX(bx);
		a.setPositionY(by);
		System.out.println("Unswapped both selected lokums deselected");
	}
	/**
	 * @requires lokumGrid
	 * @return returns array list of combinations in the board.
	 */
	public ArrayList<Combination> checkCombinations(){

		ArrayList<Combination>  combinations = new ArrayList<Combination>();

		if(SLokum1.isSpecial() && SLokum2.isSpecial()){
			ArrayList<Lokum> spLokums = new ArrayList<Lokum>();
			spLokums.add(SLokum1);
			spLokums.add(SLokum2);
			Combination special = new Combination("Special",spLokums);
			combinations.add(special);
		}else if(SLokum1.getType().equals("BOMB") && !SLokum2.isSpecial() 
				|| !SLokum1.isSpecial() && SLokum2.getType().equals("BOMB")){
			ArrayList<Lokum> Lokums = new ArrayList<Lokum>();
			Lokums.add(SLokum1);
			Lokums.add(SLokum2);
			Combination special = new Combination("Special",Lokums);
			combinations.add(special);
		}
		SLokum1 = new normalLokum(0,0,"NULL",false);
		SLokum2 = new normalLokum(0,0,"NULL",false);
		for (int i = 0; i < lokumGrid.length; i++) {	
			for (int j = 0; j < lokumGrid[0].length-2; j++) {
				ArrayList<Lokum> lokums = new ArrayList<Lokum>();
				//Searh for horizontal combination of 5
				if( j<lokumGrid[0].length-4 &&
						lokumGrid[i][j].isEqual(lokumGrid[i][j+1])&&
						lokumGrid[i][j+1].isEqual(lokumGrid[i][j+2])&&
						lokumGrid[i][j+2].isEqual(lokumGrid[i][j+3])&&
						lokumGrid[i][j+3].isEqual(lokumGrid[i][j+4]))
				{
					lokums.add(lokumGrid[i][j]);
					lokums.add(lokumGrid[i][j+1]);
					lokums.add(lokumGrid[i][j+2]);
					lokums.add(lokumGrid[i][j+3]);
					lokums.add(lokumGrid[i][j+4]);
					Combination c = new Combination("5H", lokums);
					combinations.add(c);

					j = j + 4;
				}
				//Searh for horizontal combination of 4
				else if(j<lokumGrid[0].length-3 && 
						lokumGrid[i][j].isEqual(lokumGrid[i][j+1])&&
						lokumGrid[i][j+1].isEqual(lokumGrid[i][j+2])&&
						lokumGrid[i][j+2].isEqual(lokumGrid[i][j+3]))
				{
					lokums.add(lokumGrid[i][j]);
					lokums.add(lokumGrid[i][j+1]);
					lokums.add(lokumGrid[i][j+2]);
					lokums.add(lokumGrid[i][j+3]);
					Combination c = new Combination("4H", lokums);
					combinations.add(c);
					j = j + 3;
				}
				//Searh for horizontal combination of 3
				else if(j<lokumGrid[0].length-2 && 
						lokumGrid[i][j].isEqual(lokumGrid[i][j+1])&&
						lokumGrid[i][j+1].isEqual(lokumGrid[i][j+2]))
				{
					lokums.add( lokumGrid[i][j]);
					lokums.add( lokumGrid[i][j+1]);
					lokums.add( lokumGrid[i][j+2]);
					Combination c = new Combination("3H", lokums);
					combinations.add(c);

					j = j + 2;
				}
			}
		}


		for (int i = 0; i < lokumGrid[0].length; i++) {
			for (int j = 0; j < lokumGrid.length-2; j++) {
				ArrayList<Lokum> lokums = new ArrayList<Lokum>();
				//Searh for vertical combination of 5
				if( 	j < lokumGrid.length-4 &&
						lokumGrid[j][i].isEqual(lokumGrid[j+1][i])&&
						lokumGrid[j+1][i].isEqual(lokumGrid[j+2][i])&&
						lokumGrid[j+2][i].isEqual(lokumGrid[j+3][i])&&
						lokumGrid[j+3][i].isEqual(lokumGrid[j+4][i]))
				{
					lokums.add( lokumGrid[j][i]);
					lokums.add( lokumGrid[j+1][i]);
					lokums.add( lokumGrid[j+2][i]);
					lokums.add( lokumGrid[j+3][i]);
					lokums.add( lokumGrid[j+4][i]);
					Combination c = new Combination("5V", lokums);
					combinations.add(c);
					j = j + 4;
				}
				//Searh for vertical combination of 4
				else if(j < lokumGrid.length-3 && 
						lokumGrid[j][i].isEqual(lokumGrid[j+1][i])&&
						lokumGrid[j+1][i].isEqual(lokumGrid[j+2][i])&&
						lokumGrid[j+2][i].isEqual(lokumGrid[j+3][i]))
				{
					lokums.add( lokumGrid[j][i]);
					lokums.add( lokumGrid[j+1][i]);
					lokums.add( lokumGrid[j+2][i]);
					lokums.add( lokumGrid[j+3][i]);
					Combination c = new Combination("4V", lokums);
					combinations.add(c);
					j = j + 3;
				}
				//Searh for vertical combination of 3
				else if(j < lokumGrid.length-2 &&
						lokumGrid[j][i].isEqual(lokumGrid[j+1][i])&&
						lokumGrid[j+1][i].isEqual(lokumGrid[j+2][i]))
				{
					lokums.add( lokumGrid[j][i]);
					lokums.add( lokumGrid[j+1][i]);
					lokums.add( lokumGrid[j+2][i]);
					Combination c = new Combination("3V", lokums);
					combinations.add(c);
					j = j + 2;
				}
			}
		}
		// Check for T or L combinations (Check whether vertical and horizontal combinations are intersect 
		ArrayList<Combination> horizontals = new ArrayList<Combination>();
		ArrayList<Combination> verticals = new ArrayList<Combination>();
		ArrayList<Combination> usedHorizontals = new ArrayList<Combination>();
		ArrayList<Combination> usedVerticals = new ArrayList<Combination>();
		ArrayList<Combination> Tcombos = new ArrayList<Combination>();

		for(Combination c : combinations){
			if(c.getType().equals("3H") || c.getType().equals("4H")){
				horizontals.add(c);	
			}
		}
		for(Combination c : combinations){
			if(c.getType().equals("3V") || c.getType().equals("4V")){
				verticals.add(c);	
			}
		}

		for(Combination c: horizontals){
			for(Combination co : verticals){
				ArrayList<Lokum> clokums = c.getLokums();
				ArrayList<Lokum> coLokums = co.getLokums();
				for(Lokum l : clokums){
					if(coLokums.contains(l)){
						usedVerticals.add(co);
						usedHorizontals.add(c);
						ArrayList<Lokum> Tlokums = new ArrayList<Lokum>();
						for(Lokum lokum : clokums){
							if(!Tlokums.contains(lokum)){
								Tlokums.add(lokum);	
							}
						}
						for(Lokum lokum : coLokums){
							if(!Tlokums.contains(lokum)){
								Tlokums.add(lokum);	
							}
						}
						Combination Tcombination = new Combination("T",Tlokums);
						Tcombos.add(Tcombination);
					}
				}
			}
		}
		for(Combination c : horizontals){
			if(usedHorizontals.contains(c)){
				combinations.remove(c);
			}
		}
		for(Combination c : verticals){
			if(usedVerticals.contains(c)){
				combinations.remove(c);
			}
		}
		for(Combination c : Tcombos){
			combinations.add(c);
		}



		return combinations;
	}

	/**
	 * @requires There should be a empty(null) places in lokumGrid.
	 * @modifies lokumGrid.
	 * @effects After FillEmptySpaces, there shouldn't be any null spaces.
	 * 
	 */
	public void FillEmptySpaces(){
		for (int i = 0; i < lokumGrid[0].length; i++) {
			Stack<Lokum> s = new Stack<Lokum>();

			for (int j = 0; j < lokumGrid.length; j++) {
				if(lokumGrid[j][i]!=null){
					s.push(lokumGrid[j][i]);
					lokumGrid[j][i] = null;
				}
			}
			int j2 = lokumGrid.length-1;
			while (!s.empty()) {
				Lokum temp = s.pop();
				temp.setPositionX(j2);
				temp.setPositionY(i);
				lokumGrid[j2][i] = temp;
				j2--;
			}
		}
		for (int i = lokumGrid.length-1; i >= 0; i--) {
			for (int j = lokumGrid[0].length-1; j >= 0; j--) {
				if(lokumGrid[i][j] == null){
					Lokum temp = generateRandom();
					temp.setPositionX(i);
					temp.setPositionY(j);
					lokumGrid[i][j] = temp;
				}
			}
		}
	}
	/**
	 * 
	 * @return normalLokum choosen arbitrary
	 */
	public static normalLokum generateRandom(){
		normalLokum r;
		Random rand = new Random();
		int randomNum = rand.nextInt(4);
		String type = "";
		switch (randomNum){
		case 0: type="G";
		break;
		case 1:  type="R";
		break;
		case 2:  type="W";
		break;
		case 3:  type="B";
		}
		int randomNum2 = rand.nextInt(5);
		if(randomNum2<1){
			r = new normalLokum(0, 0, type,true);	
		}
		else{
			r = new normalLokum(0, 0, type,false);
		}
		return r;
	}
	/**
	 * @requires lokum l
	 * @param l
	 * @modifies lokumGrid
	 */
	public void eatLokum(Lokum l){
		if(l != null){
			String type = l.getType();
			if(type=="HStriped"){
				lokumGrid[l.getPositionX()][l.getPositionY()] = null;
				eatAllRow(l.getPositionX());
			}
			else if(type == "VStriped"){
				lokumGrid[l.getPositionX()][l.getPositionY()] = null;
				eatAllColumn(l.getPositionY());	
			}
			else if(type == "Wrapped"){
				lokumGrid[l.getPositionX()][l.getPositionY()] = null;
				eatWrapped(l.getPositionX(), l.getPositionY());	
			}
			else if(type == "BOMB"){
				lokumGrid[l.getPositionX()][l.getPositionY()] = null;
			}
			else{
				lokumGrid[l.getPositionX()][l.getPositionY()] = null;
			}
			if(l.isTimeLokum()){
				time = time + 5;
			}
		}
	}
	/**
	 * 
	 * @param c the combination that will be eat
	 * @modifies lokumGrid, score, 
	 * @requires (Combination c!= null)
	 * @effects After first eat action, if there is an another eat combination, then it again eats.
	 *
	 */
	public void eat(Combination c){
		score += calculateCombinationScore(c);
		multiplier = multiplier * 2;
		System.out.println("Multiplier:"+multiplier);

		ArrayList<Lokum> lokums = c.getLokums();
		if(c.isSpecial()){
			Lokum first = lokums.get(0);
			Lokum second = lokums.get(1);

			if(first.getType().equals("HStriped") && second.getType().equals("VStriped")
					||
					(first.getType().equals("VStriped") && second.getType().equals("HStriped"))){
				eatLokum(first);
				eatLokum(second);
			}
			else if(first.getType().equals("HStriped") && second.getType().equals("HStriped")
					||
					(first.getType().equals("VStriped") && second.getType().equals("VStriped"))){
				Random r = new Random();
				int select = r.nextInt(2);
				if(select == 0){
					if(first.getType().equals("HStriped")){
						first.setType("VStriped");	
					}
					else if(first.getType().equals("VStriped")){
						first.setType("HStriped");	
					}
				}else if(select == 1){
					if(second.getType().equals("HStriped")){
						second.setType("VStriped");	
					}
					else if(second.getType().equals("VStriped")){
						second.setType("HStriped");	
					}
				}
				eatLokum(first);
				eatLokum(second);
			}
			else if(((first.getType().equals("HStriped")  || first.getType().equals("VStriped"))
					&& second.getType().equals("Wrapped")) 
					||
					(first.getType().equals("Wrapped") && 
							(second.getType().equals("VStriped") ||second.getType().equals("HStriped")))){
				eatAllRow(first.getPositionX());
				if(first.getPositionX()-1 >= 0) eatAllRow(first.getPositionX()-1);
				if(first.getPositionX()+1 < lokumGrid.length) eatAllRow(first.getPositionX()+1);
				eatAllColumn(second.getPositionY());
				if(second.getPositionY()-1 >= 0) eatAllColumn(second.getPositionY()-1);
				if(second.getPositionY()+1 < lokumGrid[0].length) eatAllColumn(second.getPositionY()+1);

			}
			else if(first.getType().equals("Wrapped") && second.getType().equals("Wrapped")){
				eatLokum(first);
				eatLokum(second);
			}
			else if(first.getType().equals("HStriped") && second.getType().equals("BOMB")
					||
					(first.getType().equals("VStriped") && second.getType().equals("BOMB"))){
				changeAllLokumType(first);
				eatLokum(second);
				eatAllSameType(first);
			}
			else if(first.getType().equals("BOMB") && second.getType().equals("VStriped")
					||
					(first.getType().equals("BOMB") && second.getType().equals("HStriped"))){
				changeAllLokumType(second);
				eatLokum(first);
				eatAllSameType(second);
			}
			else if(first.getType().equals("BOMB") && second.getType().equals("Wrapped")){
				eatAllSameType(second);
				eatLokum(first);
			}
			else if(first.getType().equals("Wrapped") && second.getType().equals("BOMB")){
				eatAllSameType(first);
				eatLokum(second);
			}

			else if(first.getType().equals("BOMB") && second.getType().equals("BOMB")){
				eatAllLokums();
			}else if(first.getType().equals("BOMB") && !second.isSpecial()){
				eatAllSameType(second);
			}else if(!first.isSpecial() && second.getType().equals("BOMB")){
				eatAllSameType(first);
			}
		}
		else{
			if(c.getType().equals("5H") || c.getType().equals("5V")){

				specialLokum bomb = new specialLokum(lokums.get(2).getPositionX(),lokums.get(2).getPositionY(),"NULL","BOMB"); 
				lokumGrid[lokums.get(2).getPositionX()][lokums.get(2).getPositionY()] = bomb;

				lokums.remove(2);
			}else if(c.getType().equals("4H")){
				specialLokum VStriped = new specialLokum(lokums.get(1).getPositionX(),lokums.get(1).getPositionY()
						,lokums.get(1).getColor(),"VStriped");
				lokumGrid[lokums.get(1).getPositionX()][lokums.get(1).getPositionY()] = VStriped;
				lokums.remove(1);
			}else if(c.getType().equals("4V") ){
				specialLokum HStriped = new specialLokum(lokums.get(1).getPositionX(),lokums.get(1).getPositionY()
						,lokums.get(1).getColor(),"HStriped");
				lokumGrid[lokums.get(1).getPositionX()][lokums.get(1).getPositionY()] = HStriped;
				lokums.remove(1);
			}else if(c.getType().equals("T")){
				specialLokum wrapped = new specialLokum(lokums.get(2).getPositionX(),lokums.get(2).getPositionY()
						,lokums.get(2).getColor(),"Wrapped");
				lokumGrid[lokums.get(2).getPositionX()][lokums.get(2).getPositionY()] = wrapped;
				lokums.remove(2);
			}
			for(Lokum l : lokums){
				eatLokum(l);
			}
		}
	}
	/**
	 * This method is used at creation of random board. This method does not create special lokums
	 * from combination of 3,4,5 lokums.
	 * @param c Combination that will be eat.
	 * 
	 */
	public void eatForConstructingBoard(Combination c){
		ArrayList<Lokum> lokums = c.getLokums();
		for(Lokum l : lokums){
			lokumGrid[l.getPositionX()][l.getPositionY()] = null;
		}
	}
	/**
	 * It constructs a board with random lokums.
	 * @modifies lokumGrid
	 */
	public void constructRandomBoard(){
		ArrayList<Combination> combinations = checkCombinations();
		while(combinations.size() != 0){
			for(Combination c : combinations){
				eatForConstructingBoard(c);
			}
			FillEmptySpaces();
			combinations = checkCombinations();
		}
	}
	/**
	 * @modifies lokumGrid
	 * It is used for constructing random board to prevent null pointer exception.
	 */
	public void removeAll(){
		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				normalLokum lokum = new normalLokum(i,j,"NULL",false);
				lokumGrid[i][j] = lokum;
			}
		}
	}
	/**
	 * 
	 * @param column Column index that will be eaten
	 * @requires column!=0 isSpecial()==true ¤¤ lokum==v.striped
	 * @modifies lokumGrid
	 * @effects It destroys the selected column.
	 * 
	 */
	public void eatAllColumn(int column){
		for (int i = 0; i < lokumGrid.length; i++) {
			if(lokumGrid[i][column] != null){
				eatLokum(lokumGrid[i][column]);
			}
		}
	}
	/**
	 * 
	 * @param row Row index that will be eaten
	 * @requires row!=0 isSpecial()==true ¤¤ lokum==h.striped
	 * @modifies lokumGrid
	 * @effects It destroys the selected row.
	 */
	public void eatAllRow(int row){
		for (int i = 0; i < lokumGrid[0].length; i++) {
			if(lokumGrid[row][i] !=null){
				eatLokum(lokumGrid[row][i]);
			}
		}
	}
	/**
	 * @requires lokumGrid!=0 (isSpecial()==true ¤¤ (lokum a==colorBomb && lokum b==colorBomb))
	 * @modifies lokumGrid
	 * @effects It destroys all rows and columns.
	 */
	public void eatAllLokums(){
		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				eatLokum(lokumGrid[i][j]);
			}
		}
	}
	
	/**
	 * 
	 * @param locX The X location of bomb lokum 
	 * @param locY The Y location of bomb lokum
	 * @modifies lokumGrid
	 */
	public void eatWrapped(int locX,int locY){
		eatLokum(lokumGrid[locX][locY]);
		if(locY+1 < lokumGrid[0].length) 	eatLokum(lokumGrid[locX][locY+1]);
		if(locY-1 >= 0) 	eatLokum(lokumGrid[locX][locY-1]);
		if(locX+1 < lokumGrid.length) 	eatLokum(lokumGrid[locX+1][locY]);
		if(locX+1 < lokumGrid.length && locY-1 >= 0) 	eatLokum(lokumGrid[locX+1][locY-1]);
		if(locX+1 < lokumGrid.length && locY+1 < lokumGrid[0].length) 	eatLokum(lokumGrid[locX+1][locY+1]);
		if(locX-1 >= 0) 	eatLokum(lokumGrid[locX-1][locY]);
		if(locX-1 >= 0 && locY-1 >= 0) 	eatLokum(lokumGrid[locX-1][locY-1]);
		if(locX-1 >= 0 && locY+1 < lokumGrid[0].length) eatLokum(lokumGrid[locX-1][locY+1]);
	}
	/**
	 * 
	 * @param lokum Lokum that will be eaten all over the board
	 * @requires lokumGrid!=null isSpecial()==true
	 * @modifies lokumGrid
	 * 
	 */
	public void eatAllSameType(Lokum lokum){
		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				if(lokumGrid[i][j] != null){
					if(lokumGrid[i][j].isEqual(lokum)){
						eatLokum(lokumGrid[i][j]);
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param lokum The lokum that will be changed
	 * @param type  The type that lokum will changed into
	 * @requires lokumGrid!=null
	 * @modifies lokumGrid
	 */
	public void changeAllLokumType(Lokum lokum){
		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				String color = lokum.getColor();
				String type  = lokum.getType();
				if(lokumGrid[i][j] != null){
					if(lokumGrid[i][j].isEqual(lokum)){
						specialLokum sL = new specialLokum(i,j,color,type);
						lokumGrid[i][j] = sL;
					}
				}
			}
		}
	}
	
	public void set(int x,int y, Lokum l){
		lokumGrid[x][y] = l;
	}
	public Lokum get(int x,int y){
		return lokumGrid[x][y];
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public String toString(){
		String result = "";
		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid.length; j++) {
				result += lokumGrid[i][j].toString();
				result +="\n";
			}
		}
		return result;
	}
	public boolean repOk(){
		if(width <= 0 || height <= 0 || height>30 || width > 30){
			return false;
		}
		return true;
	}
}
