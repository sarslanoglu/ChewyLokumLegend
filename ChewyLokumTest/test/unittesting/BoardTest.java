package unittesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.*;
public class BoardTest {
	private Board testBoard;

	@Before
	public void setUp() throws Exception {
		testBoard = new Board(6,6);
		testBoard.repOk();
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
		
		assertTrue(lok1.getPositionX()==1 && lok1.getPositionY() == 1
				&& lok2.getPositionX()==0 && lok2.getPositionY() == 0
				&& testBoard.get(1, 1)==lok1 && testBoard.get(0, 0) == lok2);
		
		
	}
	
}

