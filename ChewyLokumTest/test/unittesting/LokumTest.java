package unittesting;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.*;

public class LokumTest {

	@Before
	public void setUp() throws Exception {
	
	}
	@Test(expected = NullPointerException.class)
	public void testNullnormalLokum() {
		normalLokum lok = null;
		lok.repOk();
	}
	@Test(expected = NullPointerException.class)
	public void testNullspecialLokum() {
		specialLokum lok = null;
		lok.repOk();
	}
	@Test
	public void testillegalnormalLokum(){
		normalLokum lok = new normalLokum(-1,-1,"W",false);
		assertTrue(!lok.repOk());
	}

	@Test
	public void testillegalnormalLokum2(){
		normalLokum lok = new normalLokum(0,0,"P",false);
		assertTrue(!lok.repOk());	
		}
	@Test
	public void testillegalspecialLokum(){
		specialLokum lok = new specialLokum(-1,-1,"W","VStriped");
		assertTrue(!lok.repOk());
	}
	@Test
	public void testillegalspecialLokum2(){
		specialLokum lok = new specialLokum(0,0,"P","VStriped");
		assertTrue(!lok.repOk());

	}
	@Test
	public void testillegalspecialLokum3(){
		specialLokum lok = new specialLokum(0,0,"NULL","TYPE");
		assertTrue(!lok.repOk());

	}
	@Test
	public void testWhiteNormalLokum(){
		normalLokum lok = new normalLokum(0,0,"W",false);
		assertTrue(lok.repOk());
	}
	@Test
	public void testGreenNormalLokum(){
		normalLokum lok = new normalLokum(0,0,"G",false);
		assertTrue(lok.repOk());
	}@Test
	public void testRedNormalLokum(){
		normalLokum lok = new normalLokum(0,0,"R",false);
		assertTrue(lok.repOk());
	}@Test
	public void testBrownNormalLokum(){
		normalLokum lok = new normalLokum(0,0,"B",false);
		assertTrue(lok.repOk());
	}
	@Test
	public void testTimeLokum(){
		normalLokum lok = new normalLokum(0,0,"B",true);
		assertTrue(lok.repOk());
		assertTrue(lok.isTimeLokum());
	}
	@Test
	public void testVStripedspecialLokum(){
		specialLokum lok = new specialLokum(0,0,"W","VStriped");
		assertTrue(lok.repOk());
	}
	@Test
	public void testHStripedspecialLokum(){
		specialLokum lok = new specialLokum(0,0,"W","HStriped");
		assertTrue(lok.repOk());
	}
	@Test
	public void testWrappedspecialLokum(){
		specialLokum lok = new specialLokum(0,0,"W","Wrapped");
		assertTrue(lok.repOk());
	}
	@Test
	public void testBOMBspecialLokum(){
		specialLokum lok = new specialLokum(0,0,"W","BOMB");
		assertTrue(lok.repOk());
	}
	@Test
	public void testIsEqual(){
		normalLokum lok1 = new normalLokum(0,0,"B",false);
		specialLokum lok2 = new specialLokum(1,1,"B","HStriped");
		assertTrue(lok1.repOk());
		assertTrue(lok2.repOk());
		assertTrue(lok1.isEqual(lok2));
	}
	@Test
	public void testnormalLokumisSpecial(){
		normalLokum lok = new normalLokum(0,0,"W",false);
		assertTrue(lok.repOk());
		assertTrue(!lok.isSpecial());
	}
	@Test
	public void testspecialLokumisSpecial(){
		specialLokum lok = new specialLokum(0,0,"B","VStriped");
		assertTrue(lok.repOk());
		assertTrue(lok.isSpecial());
	}
}
