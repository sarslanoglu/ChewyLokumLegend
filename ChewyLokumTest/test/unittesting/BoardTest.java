package unittesting;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import game.Board;

public class BoardTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	Board test = new Board(5,5);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
