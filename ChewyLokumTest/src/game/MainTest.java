package game;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MainTest {
	public static Lokum[][] lokumGrid = new Lokum[5][5]; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gameEngine e = new gameEngine();
		e.initGame();
	}
	public static void FillEmptySpaces(){


				//!!Test eatAllSameType!!
				eatAllSameType(new normalLokum(0,0,"W", false));

				//!!Change allLokumType test!!
				changeAllLokumType(new specialLokum(0,0,"W","HStriped"));
		 		//!!Test special combo eat!!
				specialLokum lokum1 = new specialLokum(0,2,"W","BOMB");
				specialLokum lokum2 = new specialLokum(0,3,"W","BOMB");
				lokumGrid[0][2] = lokum1;
				lokumGrid[0][3] = lokum2;
				ArrayList<Lokum> lokums = new ArrayList<Lokum>();
				lokums.add(lokum1);
				lokums.add(lokum2);
				Combination c = new Combination("Special",lokums);
				eat(c);
				normalLokum lokum11 = new normalLokum(0,0,"W", false);
				normalLokum lokum21 = new normalLokum(0,0,"W", false);
				normalLokum lokum3 = new normalLokum(0,0,"W", false);
				ArrayList<Lokum> lokums1 = new ArrayList<Lokum>();
				lokums1.add(lokum11);
				lokums1.add(lokum21);
				lokums1.add(lokum3);
				Combination c1 = new Combination("3H",lokums1);
				Combination d = c1;
				if(c1.equals(d)){
					System.out.println("esit");
				}
			printLokums(lokumGrid);
			System.out.println("\n");
			}
		public static void FillEmptySpaces1(){

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
			public static void eatAllSameType(Lokum lokum){
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
			public static void eat(Combination c){
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
						if(first.getPositionY()-1 >= 0) eatAllColumn(second.getPositionY()-1);
						if(first.getPositionY()+1 < lokumGrid.length) eatAllColumn(second.getPositionY()+1);
		
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
					else if(first.getType().equals("BOMB") && second.getType().equals("BOMB")){
						eatAllLokums();
					}
				}
				else{
					for(Lokum l : lokums){
						eatLokum(l);
					}
				}
			}
			public static void eatLokum(Lokum l){
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
				}
			}
			public static void eatAllColumn(int column){
				for (int i = 0; i < lokumGrid.length; i++) {
					eatLokum(lokumGrid[i][column]);
				}
			}
			/**
			 * 
			 * @param row Row index that will be eaten
			 * @modifies lokumGrid
			 */
			public static void eatAllRow(int row){
				for (int i = 0; i < lokumGrid[0].length; i++) {
					eatLokum(lokumGrid[row][i]);
				}
			}
			/**
			 * @modifies lokumGrid
			 */
			public static void eatAllLokums(){
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
			public static void eatWrapped(int locX,int locY){
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
			public static void swap (Lokum a, Lokum b){
		
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
				r = new normalLokum(0, 0, type , false);
				return r;
			}
			public static void printLokums(Lokum[][] lokums){
				for (int i = 0; i < lokums.length; i++) {
					for (int j = 0; j < lokums[0].length; j++) {
						if(lokums[i][j] == null){
							System.out.print("  ");
						}else{
							System.out.print(lokums[i][j].toString() + " ");
						}
					}
					System.out.print("\n");
				}
			}
			
			public static void changeAllLokumType(Lokum lokum){
				for (int i = 0; i < lokumGrid.length; i++) {
					for (int j = 0; j < lokumGrid[0].length; j++) {
						String color = lokum.getColor();
						String type  = lokum.getType();
						if(lokumGrid[i][j].isEqual(lokum)){
							specialLokum sL = new specialLokum(i,j,color,type);
							lokumGrid[i][j] = sL;
						}
					}
				}
			}
	}
