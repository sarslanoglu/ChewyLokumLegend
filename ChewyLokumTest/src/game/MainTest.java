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

		specialLokum l = new specialLokum(1, 1, "fact", "beyaz");
		normalLokum a = new normalLokum(1, 1, "beyaz");
		if(l.isEqual(a)){
			System.out.println("ayný");
		}
		int[][] semih = new int[5][10];
		System.out.println(semih.length);



		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				normalLokum lok = generateRandom();
				normalLokum lokum = new normalLokum(i,j,lok.getColor());
				lokumGrid[i][j] = lokum;
			}
		}
		for (int i = 0; i < lokum[0].length; i++) {
			lokum[2][i] = null;
		}
		for (int i = 0; i < lokums.length; i++) {
			lokums[i][1] = null;
		}
		System.out.println("\n");
		swap(lokums[0][0],lokums[0][1],lokums);
		printLokums(lokums);
		System.out.println("\n");

		!!Chain Eat Lokum Test!!
		specialLokum sLokum1 = new specialLokum (0,1,"W","VStriped");
		specialLokum sLokum2 = new specialLokum (7,1,"W","HStriped");
		specialLokum sLokum3 = new specialLokum (7,4,"W","Wrapped");
		lokumGrid[0][1] = sLokum1;
		lokumGrid[7][1] = sLokum2;
		lokumGrid[7][4] = sLokum3;

		!!Wrapped Lokum Test!!
		eatWrapped(0,0);
		eatWrapped(lokumGrid.length-1,0);
		eatWrapped(lokumGrid.length-1,lokumGrid[0].length-1);
		eatWrapped(0,lokumGrid[0].length-1);

		!!CheckCombinations have bug a special test!!
		normalLokum lokum1 = new normalLokum(6,7,"W");
		normalLokum lokum2 = new normalLokum(7,7,"W");
		normalLokum lokum3 = new normalLokum(8,7,"W");
		normalLokum lokum4 = new normalLokum(9,7,"W");
		lokumGrid[6][7] = lokum1;
		lokumGrid[7][7] = lokum2;
		lokumGrid[8][7] = lokum3;
		

		printLokums(lokumGrid);
		ArrayList<Combination> combos = checkCombinations(lokumGrid);
		for(Combination c : combos){
			System.out.println(c.toString());
			eat(c);
		}

		      !!Test FillEmptySpaces!!!
				FillEmptySpaces();
				System.out.println("\n");
				printLokums(lokumGrid);

				!!Test eatAllSameType!!
				eatAllSameType(new normalLokum(0,0,"W"));

				!!Change allLokumType test!!
				changeAllLokumType(new specialLokum(0,0,"W","HStriped"));
		 		!!Test special combo eat!!
				specialLokum lokum1 = new specialLokum(0,2,"W","BOMB");
				specialLokum lokum2 = new specialLokum(0,3,"W","BOMB");
				lokumGrid[0][2] = lokum1;
				lokumGrid[0][3] = lokum2;
				ArrayList<Lokum> lokums = new ArrayList<Lokum>();
				lokums.add(lokum1);
				lokums.add(lokum2);
				Combination c = new Combination("Special",lokums);
				eat(c);
				normalLokum lokum1 = new normalLokum(0,0,"W");
				normalLokum lokum2 = new normalLokum(0,0,"W");
				normalLokum lokum3 = new normalLokum(0,0,"W");
				ArrayList<Lokum> lokums = new ArrayList<Lokum>();
				lokums.add(lokum1);
				lokums.add(lokum2);
				lokums.add(lokum3);
				Combination c = new Combination("3H",lokums);
				Combination d = c;
				if(c.equals(d)){
					System.out.println("esit");
				}
			printLokums(lokumGrid);
			System.out.println("\n");
			}
		public static void FillEmptySpaces(){

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
			public static ArrayList<Combination> checkCombinations(Lokum[][] lokumGrid){
				ArrayList<Combination>  combinations = new ArrayList<Combination>();
		
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
				// T kýsmý
				ArrayList<Combination> horizontals = new ArrayList<Combination>();
				ArrayList<Combination> verticals = new ArrayList<Combination>();
				ArrayList<Combination> usedHorizontals = new ArrayList<Combination>();
				ArrayList<Combination> usedVerticals = new ArrayList<Combination>();
				ArrayList<Combination> Tcombos = new ArrayList<Combination>();
				//ArrayList<Combination> allCombinations = new ArrayList<Combination>();
		
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
				for(Combination c : combinations){
					if(c.getType().equals("5V") || c.getType().equals("5V")){
						allCombinations.add(c);	
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
				
//		 Try for T finding- Result = Fail
				ArrayList<Combination> combos = new ArrayList<Combination>();
				for(int i=combinations.size()-1; i>=0; i--){
					Combination c = combinations.get(i);
					combinations.remove(c);
					if(c.getType().equals("3H")||c.getType().equals("3V")){
						if(c.getType().equals("3H")){
							for (int k = combinations.size()-2; k >= 0; k--) {
								Combination co = combinations.get(k);
								if(co.getType().equals("3H")){
									ArrayList<Lokum> lokums = co.getLokums();
									for(Lokum l : lokums){
										if(c.getLokums().contains(l)){
											ArrayList<Lokum> newLokums = new ArrayList<Lokum>();
											for(Lokum lo : c.getLokums()){
												if(!newLokums.contains(lo)){
													newLokums.add(lo);
												}
											}
											for(Lokum lo : co.getLokums()){
												if(!newLokums.contains(lo)){
													newLokums.add(lo);
												}
											}
											combinations.remove(c);
											combinations.remove(co);
											Combination wrapped = new Combination("T",newLokums);
											combos.add(wrapped);
										}
									}
								}
							}
						}
						else if(c.getType().equals("3V")){
		
						}
					}else{
						combos.add(c);
					}
				}
						Combination temp1 = null;
						Combination temp2 = null;
						for(int i=0; i<combinations.size(); i++){
							Combination c = combinations.get(i);
							temp1 = c;
							
							if(c.getType().equals("3V")){
								for(int j=0; j<combinations.size(); j++){
									Combination co = combinations.get(j);
									if(co.getType().equals("3H")){
										for(Lokum l : co.getLokums()){
											if(c.getLokums().contains(l)){
												combinations.remove(co);
												temp2 = co;
											}
										}
									}
								}
							}
							if(temp2 != null){
								ArrayList<Lokum> newLokums = new ArrayList<Lokum>();
								for(Lokum l : temp1.getLokums()){
									if(!newLokums.contains(l)){
										newLokums.add(l);
									}
								}
								for(Lokum l : temp2.getLokums()){
									if(!newLokums.contains(l)){
										newLokums.add(l);
									}
								}
								Combination wrapped = new Combination("T",newLokums);
								combinations.add(wrapped);
							}else{
								temp1 = null;
								temp2 = null;
								
							}
							
						}


				return combinations;
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
