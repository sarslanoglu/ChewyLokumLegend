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
		
<<<<<<< HEAD
		// Swap action test


		specialLokum l = new specialLokum(1, 1, "fact", "beyaz");
		normalLokum a = new normalLokum(1, 1, "beyaz", false);
		if(l.isEqual(a)){
			System.out.println("ayn�");
		}
		int[][] semih = new int[5][10];
		System.out.println(semih.length);

		//		for (int i = 0; i < lokumGrid.length; i++) {
		//			for (int j = 0; j < lokumGrid[0].length; j++) {
		//				normalLokum lokum = generateRandom();
		//				lokum.setPositionX(i);
		//				lokum.setPositionY(j);
		//				lokumGrid[i][j] = lokum;
		//			}
		//		}
		//		printLokums(lokumGrid);
		//		System.out.println("\n");
		//		Lokum a = lokumGrid[2][2];
		//		Lokum b = lokumGrid[2][3];
		//		swap(a,b);
		//		printLokums(lokumGrid);
=======
		// Swap action test
				for (int i = 0; i < lokumGrid.length; i++) {
					for (int j = 0; j < lokumGrid[0].length; j++) {
						normalLokum lokum = generateRandom();
						lokum.setPositionX(i);
						lokum.setPositionY(j);
						lokumGrid[i][j] = lokum;
					}
				}
				printLokums(lokumGrid);
				System.out.println("\n");
				Lokum a = lokumGrid[2][2];
				Lokum b = lokumGrid[2][3];
				swap(a,b);
				printLokums(lokumGrid);
>>>>>>> origin/master

		// Special swap action test

		//		for (int i = 0; i < lokumGrid.length; i++) {
		//			for (int j = 0; j < lokumGrid[0].length; j++) {
		//				normalLokum lokum = generateRandom();
		//				lokum.setPositionX(i);
		//				lokum.setPositionY(j);
		//				lokumGrid[i][j] = lokum;
		//			}
		//		}
		//		printLokums(lokumGrid);
		//		System.out.println("");
		//		Lokum c = lokumGrid[0][0];
		//		Lokum d = lokumGrid[4][4];
		//		// Initial implementation will be added to the orijinal code
		//		specialSwap(c,d);
		//		printLokums(lokumGrid);

		// Working with this one droped 3 time lokums to bottom and randomly fill it.
		//		normalLokum tLokum1 = new normalLokum (0,0,"WT",true);
		//		normalLokum tLokum2 = new normalLokum (0,1,"WT",true);
		//		normalLokum tLokum3 = new normalLokum (0,2,"WT",true);
		//		printLokums(lokumGrid);
		//		lokumGrid[0][0] = tLokum1;
		//		lokumGrid[0][1] = tLokum2;
		//		lokumGrid[0][2] = tLokum3;
		//		printLokums(lokumGrid);
		//		FillEmptySpaces();
		//		printLokums(lokumGrid);

		// Time Lokum combinations
		//		for (int i = 0; i < lokumGrid.length; i++) {
		//			for (int j = 0; j < lokumGrid[0].length; j++) {
		//				normalLokum lokum = generateRandomTimeLokum();
		//				lokum.setPositionX(i);
		//				lokum.setPositionY(j);
		//				lokumGrid[i][j] = lokum;
		//			}
		//		}
		//		normalLokum tLokum1 = new normalLokum (0,0,"WT",true);
		//		normalLokum tLokum2 = new normalLokum (0,1,"WT",true);
		//		normalLokum tLokum3 = new normalLokum (0,2,"WT",true);
		//		printLokums(lokumGrid);
		//		lokumGrid[0][0] = tLokum1;
		//		lokumGrid[0][1] = tLokum2;
		//		lokumGrid[0][2] = tLokum3;
		//		printLokums(lokumGrid);
		//		ArrayList<Lokum> lokums = new ArrayList<Lokum>();
		//		Combination c = new Combination("Special",lokums);
		//		eat(c);
		//		printLokums(lokumGrid);
		//	}
		//
		//	public static normalLokum generateRandomTimeLokum(){
		//		normalLokum r;
		//		Random rand = new Random();
		//		int randomNum = rand.nextInt(4);
		//		String type = "";
		//		switch (randomNum){
		//		case 0: type="GT";
		//		break;
		//		case 1:  type="RT";
		//		break;
		//		case 2:  type="WT";
		//		break;
		//		case 3:  type="BT";
		//		} 
		//		r = new normalLokum(0, 0, type , true);
		//		return r;
		//	}
<<<<<<< HEAD
		
		//
		//
=======


>>>>>>> origin/master


		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				normalLokum lok = generateRandom();
				normalLokum lokum = new normalLokum(i,j,lok.getColor(), false);
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

		//!!Chain Eat Lokum Test!!
		specialLokum sLokum1 = new specialLokum (0,1,"W","VStriped");
		specialLokum sLokum2 = new specialLokum (7,1,"W","HStriped");
		specialLokum sLokum3 = new specialLokum (7,4,"W","Wrapped");
		lokumGrid[0][1] = sLokum1;
		lokumGrid[7][1] = sLokum2;
		lokumGrid[7][4] = sLokum3;

		//!!Wrapped Lokum Test!!
		eatWrapped(0,0);
		eatWrapped(lokumGrid.length-1,0);
		eatWrapped(lokumGrid.length-1,lokumGrid[0].length-1);
		eatWrapped(0,lokumGrid[0].length-1);

		//!!CheckCombinations have bug a special test!!
		normalLokum lokum1 = new normalLokum(6,7,"W", false);
		normalLokum lokum2 = new normalLokum(7,7,"W", false);
		normalLokum lokum3 = new normalLokum(8,7,"W", false);
		normalLokum lokum4 = new normalLokum(9,7,"W", false);
		lokumGrid[6][7] = lokum1;
		lokumGrid[7][7] = lokum2;
		lokumGrid[8][7] = lokum3;
		


		printLokums(lokumGrid);
		ArrayList<Combination> combos = checkCombinations(lokumGrid);
		for(Combination c : combos){
			System.out.println(c.toString());
			eat(c);
		}

		      //!!Test FillEmptySpaces!!!
				FillEmptySpaces();
				System.out.println("\n");
				printLokums(lokumGrid);

		//		specialLokum l = new specialLokum(1, 1, "fact", "beyaz");
		//				normalLokum a = new normalLokum(1, 1, "beyaz");
		//				if(l.isEqual(a)){
		//					System.out.println("aynı");
		//				}
		//						int[][] semih = new int[5][10];
		//						System.out.println(semih.length);


		//		for (int i = 0; i < lokums[0].length; i++) {
		//			lokums[2][i] = null;
		//		}
		//		for (int i = 0; i < lokums.length; i++) {
		//			lokums[i][1] = null;
		//		}
		//		System.out.println("\n");
		//		swap(lokums[0][0],lokums[0][1],lokums);
		//		printLokums(lokums);
		//		System.out.println("\n");

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
				// T k�sm�
				ArrayList<Combination> horizontals = new ArrayList<Combination>();
				ArrayList<Combination> verticals = new ArrayList<Combination>();
				ArrayList<Combination> usedHorizontals = new ArrayList<Combination>();
				ArrayList<Combination> usedVerticals = new ArrayList<Combination>();
				ArrayList<Combination> Tcombos = new ArrayList<Combination>();
				ArrayList<Combination> allCombinations = new ArrayList<Combination>();
		
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
		// T kýsmý
		ArrayList<Combination> horizontals1 = new ArrayList<Combination>();
		ArrayList<Combination> verticals1 = new ArrayList<Combination>();
		ArrayList<Combination> usedHorizontals1 = new ArrayList<Combination>();
		ArrayList<Combination> usedVerticals1 = new ArrayList<Combination>();
		ArrayList<Combination> Tcombos1 = new ArrayList<Combination>();
		//ArrayList<Combination> allCombinations = new ArrayList<Combination>();

		for(Combination c1 : combinations){
			if(c1.getType().equals("3H") || c1.getType().equals("4H")){
				horizontals1.add(c1);	
			}
		}
		for(Combination c1 : combinations){
			if(c1.getType().equals("3V") || c1.getType().equals("4V")){
				verticals1.add(c1);	
			}
		}
		//		for(Combination c : combinations){
		//			if(c.getType().equals("5V") || c.getType().equals("5V")){
		//				allCombinations.add(c);	
		//			}
		//		}
		for(Combination c1: horizontals1){
			for(Combination co : verticals1){
				ArrayList<Lokum> clokums = c1.getLokums();
				ArrayList<Lokum> coLokums = co.getLokums();
				for(Lokum l : clokums){
					if(coLokums.contains(l)){
						usedVerticals1.add(co);
						usedHorizontals1.add(c1);
						ArrayList<Lokum> Tlokums = new ArrayList<Lokum>();
						for(Lokum lokum : clokums){
							if(!Tlokums.contains(lokum)){
								Tlokums.add(lokum);	

							}
						}
						else if(c1.getType().equals("3V")){
		
						}
					}else{
						combos.add(c1);
					}
				}
						Combination temp1 = null;
						Combination temp2 = null;
						for(int i1=0; i1<combinations.size(); i1++){
							Combination c11 = combinations.get(i1);
							temp1 = c11;
							
							if(c11.getType().equals("3V")){
								for(int j=0; j<combinations.size(); j++){
									Combination co1 = combinations.get(j);
									if(co1.getType().equals("3H")){
										for(Lokum l : co1.getLokums()){
											if(c11.getLokums().contains(l)){
												combinations.remove(co1);
												temp2 = co1;
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
