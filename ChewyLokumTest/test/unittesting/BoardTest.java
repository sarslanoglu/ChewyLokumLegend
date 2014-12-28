package unittesting;

// GlassBox tests are mentioned before methods,
// all other tests are specification , unit test and black box tests.

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
		testBoard = new Board(7,7);
		testBoard.constructRandomBoard();
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
		testBoard = new Board(1,4);
		normalLokum lokum1 = new normalLokum (0,0,"W",false);
		normalLokum lokum2 = new normalLokum (0,1,"W",false);
		normalLokum lokum3 = new normalLokum (0,3,"W",false);

		testBoard.set(lokum1.getPositionX(),lokum1.getPositionY(),lokum1);
		testBoard.set(lokum2.getPositionX(),lokum2.getPositionY(),lokum2);
		testBoard.set(lokum3.getPositionX(),lokum3.getPositionY(),lokum3);

		ArrayList<Combination> c = testBoard.checkCombinations();

		assertTrue(c.size() == 0);
	
		normalLokum lokum4 = new normalLokum (0,2,"G",false);
		
		testBoard.set(lokum4.getPositionX(),lokum4.getPositionY(),lokum4);
		testBoard.swap(lokum3, lokum4);

		ArrayList<Combination> cm = testBoard.checkCombinations();
		assertTrue(cm.size() != 0);
		assertTrue(cm.get(0).getType().equals("3H"));
	}

	//GlassBox Test
	@Test
	public void swapActionTest(){
		normalLokum a = new normalLokum (0,0,"W",false);
		normalLokum b = new normalLokum (0,1,"G",false);
		
		testBoard.set(a.getPositionX(),a.getPositionY(),a);
		testBoard.set(b.getPositionX(),b.getPositionY(),b);
		
		assertTrue(a.repOk());
		assertTrue(b.repOk());
		
		int tempx = b.getPositionX();
		int tempy = b.getPositionY();
		
		testBoard.swap(a, b);
		
		int tempx1 = a.getPositionX();
		int tempy1 = a.getPositionY();

		if(tempx == tempx1 && tempy == tempy1){
			assertTrue(a.repOk());
			assertTrue(b.repOk());
		}else{
			assertFalse(a.repOk());
			assertFalse(b.repOk());
		}
	}
	@Test
	public void unswapActionTest(){
		normalLokum a = new normalLokum (0,0,"W",false);
		normalLokum b = new normalLokum (1,1,"G",false);
		
		testBoard.set(a.getPositionX(),a.getPositionY(),a);
		testBoard.set(b.getPositionX(),b.getPositionY(),b);
		
		int tempx = b.getPositionX();
		int tempy = b.getPositionY();
		
		testBoard.unswap(a, b);
		
		int tempx1 = a.getPositionX();
		int tempy1 = a.getPositionY();
		
		if(tempx == tempx1 && tempy == tempy1){
			assertTrue(a.repOk());
			assertTrue(b.repOk());
		}else{
			assertFalse(a.repOk());
			assertFalse(b.repOk());
		}
		
	}
	@Test
	public void specialSwapActionTest(){
		normalLokum c = new normalLokum (0,0,"R",false);
		normalLokum d = new normalLokum (4,4,"B",false);

		testBoard.set(c.getPositionX(),c.getPositionY(),c);
		testBoard.set(d.getPositionX(),d.getPositionY(),d);
		
<<<<<<< HEAD
=======
		assertTrue(c.repOk());
		assertTrue(d.repOk());
		
		testBoard.swap(c, d);
		
		assertTrue(c.repOk());
		assertTrue(d.repOk());
		assertTrue(testBoard.repOk());
		
		assertTrue(testBoard.get(0,0) == d && testBoard.get(4,4) == c);
	}
	
	// GlassBox Test
	// In this test we want to see that GenerateRandom method works properly.
	// We expect that GenerateRandom will enter each switch case because of too many inputs.
	// Number of different types of lokums are 8 (4 Colors with time lokum possibility).
	@Test
	public void testGenerateRandom(){
		ArrayList<Lokum> tooManyLokums = new ArrayList<Lokum>();
		int[] validation = new int[8]; // Can be 0 or 1 each cell represents a lokum
		
		for (int i = 0; i < validation.length; i++) {
			validation[i] = 0;
		}
		
		for(int i = 0; i<1000000; i++){
			tooManyLokums.add(testBoard.generateRandom());
		}
		
		for(Lokum l : tooManyLokums){
			if(l.isTimeLokum()){
				if(l.getColor().equals("W")){
					validation[0] = 1;
				}
				else if(l.getColor().equals("B")){
					validation[1] = 1;
				}
				else if(l.getColor().equals("R")){
					validation[2] = 1;
				}
				else if(l.getColor().equals("G")){
					validation[3] = 1;
				}
			}
			else{
				if(l.getColor().equals("W")){
					validation[4] = 1;
				}
				else if(l.getColor().equals("B")){
					validation[5] = 1;
				}
				else if(l.getColor().equals("R")){
					validation[6] = 1;
				}
				else if(l.getColor().equals("G")){
					validation[7] = 1;
				}
			}
		}
		for (int i = 0; i < validation.length; i++) {
			assertTrue(validation[i] == 1);
		}
	}
	//GlassBox Test of EatLokum starts (9 Methods)
	// This test try to reach every branch of eatLokum method and wants to cover and check  
	// all affects of eatLokum method on board.
	@Test
	public void eatLokumTestNormalLokum(){
		normalLokum lok = new normalLokum(0,0,"W",false);
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		assertTrue(testBoard.get(0, 0) == null);
	}
	@Test
	public void eatLokumTestVStripedLokum(){
		specialLokum lok = new specialLokum(0,0,"W","VStriped");
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		for (int i = 0; i < testBoard.getHeight(); i++) {
			assertTrue(testBoard.get(i, 0) == null);
		}
	}
	@Test
	public void eatLokumTestHStripedLokum(){
		specialLokum lok = new specialLokum(0,0,"R","HStriped");
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		for (int i = 0; i < testBoard.getWidth(); i++) {
			assertTrue(testBoard.get(0, i) == null);
		}
	}
	@Test
	public void eatLokumTestWrappedLokum1(){
		specialLokum lok = new specialLokum(0,0,"B","Wrapped");
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		
		assertTrue(testBoard.get(0, 0) == null);
		assertTrue(testBoard.get(0, 1) == null);
		assertTrue(testBoard.get(1, 0) == null);
		assertTrue(testBoard.get(1, 1) == null);
		
	}
	@Test
	public void eatLokumTestWrappedLokum2(){
		specialLokum lok = new specialLokum(1,1,"G","Wrapped");
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		
		assertTrue(testBoard.get(0, 0) == null);
		assertTrue(testBoard.get(0, 1) == null);
		assertTrue(testBoard.get(0, 2) == null);
		assertTrue(testBoard.get(1, 0) == null);
		assertTrue(testBoard.get(1, 1) == null);
		assertTrue(testBoard.get(1, 2) == null);
		assertTrue(testBoard.get(2, 0) == null);
		assertTrue(testBoard.get(2, 1) == null);
		assertTrue(testBoard.get(2, 2) == null);
	}
	@Test
	public void eatLokumTestWrappedLokum3(){
		specialLokum lok = new specialLokum(6,6,"G","Wrapped");
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		
		assertTrue(testBoard.get(5, 5) == null);
		assertTrue(testBoard.get(5, 6) == null);
		assertTrue(testBoard.get(6, 5) == null);
		assertTrue(testBoard.get(6, 6) == null);
		
	}
	@Test
	public void eatLokumTestBOMB(){
		specialLokum lok = new specialLokum(6,6,"NULL","BOMB");
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		assertTrue(testBoard.get(6, 6) == null); // BOMB branch implemented as eat only itself. 
												 // Other eats because of BOMB interaction is handled by another methods.
	}
	@Test
	public void eatLokumTimeIncrease(){
		normalLokum lok = new normalLokum(4,4,"W",true);
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		assertTrue(testBoard.getTime() == 5); // A time lokum is eaten and we expect boards temporary time to be 5.
	}
	@Test
	public void eatLokumTimeStability(){
		normalLokum lok = new normalLokum(4,4,"W",false);
		testBoard.set(lok.getPositionX(), lok.getPositionY(), lok);
		
		assertTrue(lok.repOk());
		assertTrue(testBoard.repOk());
		
		testBoard.eatLokum(lok);
		
		assertTrue(testBoard.repOk());
		assertTrue(testBoard.getTime() == 0); // A normal lokum is eaten and we expect boards temporary time to be 0.
>>>>>>> origin/master
	}
	//GlassBox test of eatLokum ends
}


