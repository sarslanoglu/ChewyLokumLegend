package unittesting;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import game.*;
public class BoardTest {

	private Board testBoard;
	private static Lokum[][] lokumGrid = new Lokum[5][5];

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
	//GlassBox Test
	@Test
	public void testCreationOf3HCombination(){

	}

	//GlassBox Test Sarslanoglu
	@Test
	public void allSwapActionsTest(){
		testBoard = new Board(5,5);
		normalLokum a = new normalLokum (0,0,"W",false);
		normalLokum b = new normalLokum (4,4,"G",false);
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
}


