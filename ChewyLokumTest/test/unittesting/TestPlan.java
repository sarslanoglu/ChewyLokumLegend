package unittesting;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import game.Board;
import game.Level;
import game.Lokum;
import game.specialLokum;
import game.normalLokum;
import game.Combination;
public class TestPlan {
	public Board board;
	public Board randomBoard;
	public Level level;
	public Lokum[][] randomLokumGrid;
	public Lokum[][] levelLokumGrid;
	public specialLokum lokumHStriped;
	public specialLokum lokumVStriped;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}
	@Before
	public void setUp() throws Exception {
		
		randomLokumGrid = new Lokum[10][10];
		for (int i = 0; i < randomLokumGrid.length; i++) {
			for (int j = 0; j < randomLokumGrid[0].length; j++) {
				Lokum l = generateRandomNormalLokum();
				l.setPositionX(i);
				l.setPositionY(j);
				randomLokumGrid[i][j] = l;
			}
		}
		
		board = new Board(5,5);
		board.constructRandomBoard();
		lokumHStriped = new specialLokum(4,0,"White","HStriped");
		lokumVStriped = new specialLokum(0,4,"Green","VStriped");
		
	}
	@Test 
	public static Lokum generateRandomNormalLokum(){
		Lokum r = null;
		Random rand = new Random();
		int randomNum = rand.nextInt(4);
		String color = "";
		switch (randomNum){
		case 0: color="Green";
		break;
		case 1: color="Red";
		break;
		case 2: color="White";
		break;
		case 3: color="Brown";
		}
		r = new normalLokum(0, 0, color,false);
		return r;
	}
	
	@Test (expected = NullPointerException.class)
	public void testnormalNullLokum(){
		normalLokum normalNullLokum = null; 
		normalNullLokum.repOk();
	}
	@Test (expected = NullPointerException.class)
	public void testSpecialNullLokum(){
		specialLokum specialNullLokum = null;
		specialNullLokum.repOk();
	}
	@Test (expected = NullPointerException.class)
	public void testNullBoard(){
		Lokum[][] test = null;
		Board testBoard = new Board(5,5);
		testBoard.repOk();
	}
	
	@Test
	public void testSwap() {
		boolean testResult = false;
		if(!board.repOk()){
			assertFalse("Board is not valid.",true);
		};
		
		Lokum lokum1 = levelLokumGrid[0][0];
		Lokum lokum2 = levelLokumGrid[0][1];
		if(!lokum1.repOk() || !lokum2.repOk()){
			assertFalse("At least one lokum is not valid", true);
		}
	
		Point p1 = new Point(lokum1.getPositionX(),lokum1.getPositionY());
		Point p2 = new Point(lokum2.getPositionX(),lokum2.getPositionY());
	
		board.swap(lokum1, lokum2);
		if(!board.repOk()){
			assertFalse("Board becomes invalid after swap.",true);
		};
		if(!lokum1.repOk() || !lokum2.repOk()){
			assertFalse("At least one lokum becames invalid after swap.", true);
		}

		Point newp1 = new Point(lokum1.getPositionX(),lokum1.getPositionY());
		Point newp2 = new Point(lokum2.getPositionX(),lokum2.getPositionY());
		
		if(p1 == newp2 && p2 == newp1 && 
			levelLokumGrid[0][0] == lokum2 && levelLokumGrid[0][1] == lokum1){
			testResult = true;
		}
		assertTrue("Swap conditions met.",testResult);
		assertTrue("Swap conditions are not met.",!testResult);
	}
	@Test
	public void testEatingCombinations(){
		ArrayList<Combination> combinations;
		if(!randomBoard.repOk()){
			assertFalse("Board becomes invalid after swap.",true);
		};
		
		combinations = randomBoard.checkCombinations();
		for(Combination c : combinations){
			randomBoard.eat(c);
		}
		
		assertTrue("No combinations left in board.", randomBoard.checkCombinations().size() == 0);
	}
	@Test
	public void testEatSpecialVStriped(){
		boolean testResult = true;
		if(!board.repOk()){
			assertFalse("Board is not valid.",true);
		};
		
		levelLokumGrid[0][4] = lokumVStriped;
		board.eatAllColumn(4);
		
		for (int i = 0; i < levelLokumGrid.length; i++) {
			if(levelLokumGrid[i][4] != null){
				testResult = false;
			}
		}
		
		if(!board.repOk()){
			assertFalse("Board becomes invalid after eat.",true);
		};
		
		assertTrue("All column is eaten." , testResult);
		assertTrue("All column is not eaten.", !testResult);	
	}
	@Test
	public void testEatSpecialHStriped(){
		boolean testResult = true;
		if(!board.repOk()){
			assertFalse("Board is not valid.",true);
		};
		
		levelLokumGrid[4][0] = lokumVStriped;
		board.eatAllRow(4);
		
		for (int i = 0; i < levelLokumGrid[0].length; i++) {
			if(levelLokumGrid[4][i] != null){
				testResult = false;
			}
		}
		
		if(!board.repOk()){
			assertFalse("Board becomes unvalid after eat.",true);
		};
		
		assertTrue("All row is eaten." , testResult);
		assertTrue("All row is not eaten.", !testResult);	
	}
	@Test
	public void testEatAllRow(){
		boolean testResult = true;
		if(board.repOk()){
			assertFalse("Board is not valid.",true);
		};
		
		board.eatAllRow(0);
		for (int i = 0; i < levelLokumGrid[0].length; i++) {
			if(levelLokumGrid[0][i] !=null){
				testResult = false; }
	} }
	@Test
	public void testEatAllColumn(){
		boolean testResult = true;
		if(board.repOk()){
			assertFalse("Board is not valid",true);
		};
		board.eatAllColumn(0);
		for (int i = 0; i < levelLokumGrid.length; i++) {
			if(levelLokumGrid[i][0] != null){
				testResult = false;
		}
	} }
	@Test
	public void testEatAllLokums(){
		boolean testResult = true;
		if(!board.repOk()){
			assertFalse("Board is not valid.",true);
		};
		
		board.eatAllLokums();
		for (int i = 0; i < levelLokumGrid.length; i++) {
			for (int j = 0; j < levelLokumGrid[0].length; j++) {
				if(levelLokumGrid[i][j] != null){
					testResult = false;
				}
			}
		}
		
		if(!board.repOk()){
			assertFalse("Board becomes invalid after eat.",true);
		};
		
		assertTrue("All lokums are eaten.",testResult);
		assertTrue("All lokums are not eaten.",!testResult);
	}
	public void testCalculateCombinationScore(){
		int score = 0;
		
		if(!board.repOk()){
			assertFalse("Board is not valid.",true);
		};
		
		ArrayList<Combination> combinations = board.checkCombinations();
		Combination c = combinations.get(0);
		
		score = board.calculateCombinationScore(c);
		assertTrue("Score calculated.",score != 0);
		assertTrue("Score not calculated.",score == 0);
	}
	
}
