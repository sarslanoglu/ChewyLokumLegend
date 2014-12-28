package unittesting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import game.*;
public class BoardTest {

	private Board testBoard;
	ArrayList<Combination>  combinations = new ArrayList<Combination>();

	@Before
	public void setUp() throws Exception {
		testBoard = new Board(6,6);
		testBoard.repOk();
	}
	@Test
	public void testLegalBoard(){
		testBoard = new Board(10,8);
		assertTrue(testBoard.repOk());
	}
	@Test(expected = NullPointerException.class)
	public void testNullBoard(){
		testBoard = null;
		testBoard.repOk();
	}
	@Test
	public void testIllegalNegativeBoard(){
		testBoard = new Board(-1,-1);
		assertFalse(testBoard.repOk());
	}
	@Test
	public void testIllegalNegativeBoard2(){
		testBoard = new Board(-1,1);
		assertFalse(testBoard.repOk());

	}
	@Test
	public void testIllegalNegativeBoard3(){
		testBoard = new Board(1,-1);
		assertFalse(testBoard.repOk());
	}
	@Test
	public void testIllegalLargeBoard(){
		testBoard = new Board(40,40);
		assertFalse(testBoard.repOk());
	}
	public void testBiggerBoard(){
		testBoard = new Board(100,100);
		assertFalse(testBoard.repOk());
	}
	@Test
	public void testSetandGet(){
		normalLokum lok = new normalLokum(2,2,"G",false);

		assertTrue(lok.repOk());
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);

		assertTrue(testBoard.repOk());
		assertTrue(lok.repOk());
		assertTrue(testBoard.get(2, 2)==lok);
	}
	@Test
	public void testSwap() {
		normalLokum lok1 = new normalLokum(0,0,"W",false);
		normalLokum lok2 = new normalLokum(1,1,"B",false);

		assertTrue(lok1.repOk());
		assertTrue(lok2.repOk());

		testBoard.set(lok1.getPositionX(),lok1.getPositionY(),lok1);
		testBoard.set(lok2.getPositionX(),lok2.getPositionY(),lok2);
		testBoard.swap(lok1,lok2);

		assertTrue(lok1.repOk());
		assertTrue(lok2.repOk());
		assertTrue(testBoard.repOk());

		assertTrue(lok1.getPositionX()==1 && lok1.getPositionY() == 1
				&& lok2.getPositionX()==0 && lok2.getPositionY() == 0
				&& testBoard.get(1, 1)==lok1 && testBoard.get(0, 0) == lok2);	
	}
	@Test 
	public void testUnswap(){
		normalLokum lok1 = new normalLokum(0,0,"W",false);
		normalLokum lok2 = new normalLokum(1,1,"B",false);
		assertTrue(lok1.repOk());
		assertTrue(lok2.repOk());

		testBoard.set(lok1.getPositionX(),lok1.getPositionY(),lok1);
		testBoard.set(lok2.getPositionX(),lok2.getPositionY(),lok2);
		testBoard.unswap(lok1,lok2);

		assertTrue(lok1.repOk());
		assertTrue(lok2.repOk());
		assertTrue(testBoard.repOk());

		assertTrue(lok1.getPositionX()==1 && lok1.getPositionY() == 1
				&& lok2.getPositionX()==0 && lok2.getPositionY() == 0
				&& testBoard.get(1,1)==lok1 && testBoard.get(0,0) == lok2);
	}
	//GlassBox Test
	@Test
	public void testCreationOf3HCombination(){
		testBoard = new Board(5,5);
		normalLokum lokum1 = new normalLokum (0,0,"W",false);
		normalLokum lokum2 = new normalLokum (0,1,"W",false);
		normalLokum lokum3 = new normalLokum (0,3,"W",false);

		testBoard.set(lokum1.getPositionX(),lokum1.getPositionY(),lokum1);
		testBoard.set(lokum2.getPositionX(),lokum2.getPositionY(),lokum2);
		testBoard.set(lokum3.getPositionX(),lokum3.getPositionY(),lokum3);

		ArrayList<Combination> c = testBoard.checkCombinations();

		if(c == null){
			assertTrue(testBoard.repOk());
		}else{
			assertFalse(testBoard.repOk());
		}

		normalLokum lokum4 = new normalLokum (0,2,"G",false);
		testBoard.set(lokum4.getPositionX(),lokum4.getPositionY(),lokum4);
		testBoard.swap(lokum3, lokum4);

		ArrayList<Combination> cm = testBoard.checkCombinations();

		if(cm != null){
			assertTrue(testBoard.repOk());
		}else{
			assertFalse(testBoard.repOk());
		}

		// Yigit burada bir sikinti var
		//testBoard.eat(cm);

		if(testBoard.get(0, 0) == null){
			assertTrue(testBoard.repOk());
		}else{
			assertFalse(testBoard.repOk());
		}
	}

	//GlassBox Test
	@Test
	public void swapActionTest(){
		testBoard = new Board(5,5);
		normalLokum a = new normalLokum (0,0,"W",false);
		normalLokum b = new normalLokum (0,1,"G",false);

		testBoard.set(a.getPositionX(),a.getPositionY(),a);
		testBoard.set(b.getPositionX(),b.getPositionY(),b);

		int tempx = b.getPositionX();
		int tempy = b.getPositionY();
		testBoard.swap(a, b);
		int tempx1 = a.getPositionX();
		int tempy1 = a.getPositionY();

		if(tempx == tempx1 || tempy == tempy1){
			assertTrue(a.repOk());
			assertTrue(b.repOk());
		}else{
			assertFalse(a.repOk());
			assertFalse(b.repOk());
		}
	}
	@Test
	public void unswapActionTest(){
		testBoard = new Board(5,5);
		normalLokum a = new normalLokum (0,0,"W",false);
		normalLokum b = new normalLokum (1,1,"G",false);

		testBoard.set(a.getPositionX(),a.getPositionY(),a);
		testBoard.set(b.getPositionX(),b.getPositionY(),b);

		int tempx = b.getPositionX();
		int tempy = b.getPositionY();
		testBoard.unswap(a, b);
		int tempx1 = a.getPositionX();
		int tempy1 = a.getPositionY();

		if(tempx == tempx1 || tempy == tempy1){
			assertTrue(a.repOk());
			assertTrue(b.repOk());
		}else{
			assertFalse(a.repOk());
			assertFalse(b.repOk());
		}

	}
	@Test
	public void specialSwapActionTest(){
		testBoard = new Board(5,5);
		normalLokum c = new normalLokum (0,0,"R",false);
		normalLokum d = new normalLokum (4,4,"B",false);

		testBoard.set(c.getPositionX(),c.getPositionY(),c);
		testBoard.set(d.getPositionX(),d.getPositionY(),d);

		// Special swap olup olmadigini check etmeli ama nasil
		// testBoard.swap(c, d);

		int tempx = d.getPositionX();
		int tempy = d.getPositionY();
		testBoard.swap(c, d);
		int tempx1 = c.getPositionX();
		int tempy1 = c.getPositionY();

		if(tempx == tempx1 || tempy == tempy1){
			assertTrue(c.repOk());
			assertTrue(d.repOk());
		}else{
			assertFalse(c.repOk());
			assertFalse(d.repOk());
		}
	}

	@Test
	public void scoreTest(){
		testBoard = new Board(5,5);
		testBoard.constructRandomBoard();

		testBoard.setScore(0);
		normalLokum lokum1 = new normalLokum (0,0,"W",false);
		normalLokum lokum2 = new normalLokum (0,1,"W",false);
		normalLokum lokum3 = new normalLokum (0,2,"W",false);
		testBoard.set(0, 0, lokum1);
		testBoard.set(0, 1, lokum2);
		testBoard.set(0, 2, lokum3);

		ArrayList<Combination> c = testBoard.checkCombinations();

		// Array list in combination la uyusmazligi sorunu var sanýrým

		//		testBoard.calculateCombinationScore(c);
		//		testBoard.eat(c);

		if(testBoard.getScore() != 0){
			assertTrue(testBoard.repOk());
		}else{
			assertFalse(testBoard.repOk());
		}
	}

	@Test
	public void timeCombinationTest(){
		testBoard = new Board(5,5);
		testBoard.constructRandomBoard();

		testBoard.setScore(0);
		testBoard.setTime(10000);
		normalLokum lokum1 = new normalLokum (0,0,"W",true);
		normalLokum lokum2 = new normalLokum (0,1,"W",true);
		normalLokum lokum3 = new normalLokum (0,2,"W",true);
		testBoard.set(0, 0, lokum1);
		testBoard.set(0, 1, lokum2);
		testBoard.set(0, 2, lokum3);

		ArrayList<Combination> c = testBoard.checkCombinations();

		// Array list in combination la uyusmazligi sorunu var sanýrým

		//		testBoard.calculateCombinationScore(c);
		//		testBoard.eat(c);

		if(testBoard.getTime() > 10000){
			assertTrue(testBoard.repOk());
		}else{
			assertFalse(testBoard.repOk());
		}
	}

}


