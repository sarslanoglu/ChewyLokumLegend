package unittesting;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.*;

public class BoardTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
	Board testBoard = new Board(5,5);
	
	}

	
	@Test
	public void testNullLokum() {
		normalLokum lok = null;
		assertTrue(lok.repOk());
	}

}
