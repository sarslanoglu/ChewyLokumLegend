package unittesting;

//GlassBox tests are mentioned before methods,
//all other tests are specification , unit test and black box tests.

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import game.*;

public class CombinationTest {
		ArrayList<Lokum> lokums = new ArrayList<Lokum>();;
		private normalLokum lok1 = new normalLokum(0,0,"W",false);
		private normalLokum lok2 = new normalLokum(0,1,"W",false);
		private normalLokum lok3 = new normalLokum(0,2,"W",false);
		private normalLokum lok4 = new normalLokum(0,3,"W",false);
		private normalLokum lok5 = new normalLokum(0,4,"W",false);
		
		private normalLokum lok6 = new normalLokum(0,0,"W",false);
		private normalLokum lok7 = new normalLokum(1,0,"W",false);
		private normalLokum lok8 = new normalLokum(2,0,"W",false);
		private normalLokum lok9 = new normalLokum(3,0,"W",false);
		private normalLokum lok10 = new normalLokum(4,0,"W",false);
		
	@Before
	public void setUp() throws Exception {
		assertTrue(lok1.repOk());
		assertTrue(lok2.repOk());
		assertTrue(lok3.repOk());
		assertTrue(lok4.repOk());
		assertTrue(lok5.repOk());
		assertTrue(lok6.repOk());
		assertTrue(lok7.repOk());
		assertTrue(lok8.repOk());
		assertTrue(lok9.repOk());
		assertTrue(lok10.repOk());
	}

	@Test
	public void test3HCombination() {
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("3H",lokums);
		assertTrue(c.repOk());
	}
	@Test
	public void test4HCombination() {
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		Combination c = new Combination("4H",lokums);
		assertTrue(c.repOk());
	}
	@Test
	public void test5HCombination() {
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		lokums.add(lok5);
		Combination c = new Combination("5H",lokums);
		assertTrue(c.repOk());
	}
	@Test
	public void test3VCombination() {
		lokums.add(lok6);
		lokums.add(lok7);
		lokums.add(lok8);
		Combination c = new Combination("3V",lokums);
		assertTrue(c.repOk());
	}
	@Test
	public void test4VCombination() {
		lokums.add(lok6);
		lokums.add(lok7);
		lokums.add(lok8);
		lokums.add(lok9);
		Combination c = new Combination("4V",lokums);
		assertTrue(c.repOk());
	}
	@Test
	public void test5VCombination() {
		lokums.add(lok6);
		lokums.add(lok7);
		lokums.add(lok8);
		lokums.add(lok9);
		lokums.add(lok10);
		Combination c = new Combination("5V",lokums);
		assertTrue(c.repOk());
	}
	@Test
	public void testTCombination() {
		lok1.setPositionX(1);
		lok1.setPositionX(1);
		lok2.setPositionX(1);
		lok2.setPositionX(2);
		
		lokums.add(lok6);
		lokums.add(lok7);
		lokums.add(lok8);
		lokums.add(lok1);
		lokums.add(lok2);
		
		Combination c = new Combination("T",lokums);
		assertTrue(c.repOk());
	}
	@Test (expected = NullPointerException.class)
	public void testNullCombination(){
		Combination c = null;
		c.repOk();
	}
	@Test
	public void testIllegal3VCombination(){
		lokums.add(lok1);
		Combination c = new Combination("3V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal3VCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("3V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal4VCombination(){
		lokums.add(lok1);
		Combination c = new Combination("4V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal4VCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("4V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal4VCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("4V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5VCombination(){
		lokums.add(lok1);
		Combination c = new Combination("5V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5VCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("5V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5VCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("5V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5VCombination4(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		Combination c = new Combination("5V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6VCombination(){
		lokums.add(lok1);
		Combination c = new Combination("6V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6VCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("6V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6VCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("6V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6VCombination4(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		Combination c = new Combination("6V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6VCombination5(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		lokums.add(lok5);
		Combination c = new Combination("6V",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal3HCombination(){
		lokums.add(lok1);
		Combination c = new Combination("3H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal3HCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("3H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal4HCombination(){
		lokums.add(lok1);
		Combination c = new Combination("4H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal4HCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("4H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal4HCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("4H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5HCombination(){
		lokums.add(lok1);
		Combination c = new Combination("5H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5HCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("5H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5HCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("5H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal5HCombination4(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		Combination c = new Combination("5H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6HCombination(){
		lokums.add(lok1);
		Combination c = new Combination("6H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6HCombination2(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("6H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6HCombination3(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("6H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6HCombination4(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		Combination c = new Combination("6H",lokums);
		assertTrue(!c.repOk());
	}
	@Test
	public void testIllegal6HCombination5(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		lokums.add(lok4);
		lokums.add(lok5);
		Combination c = new Combination("6H",lokums);
		assertTrue(!c.repOk());
	}	
	@Test 
	public void testLegalisSpecial(){
		specialLokum sLokum = new specialLokum(0,0,"B","VStriped");
		assertTrue(sLokum.repOk());
		lokums.add(sLokum);
		lokums.add(lok1);
		Combination c = new Combination("Special",lokums);
		assertTrue(c.repOk());
		assertTrue(c.isSpecial());
	}
	@Test (expected = NullPointerException.class)
	public void testIllegalisSpecial(){
		lokums.add(lok1);
		lokums.add(lok2);
		Combination c = new Combination("Special",lokums);
		assertTrue(!c.repOk());
		assertTrue(c.isSpecial());
	}
	@Test
	public void testisNotSpecial(){
		lokums.add(lok1);
		lokums.add(lok2);
		lokums.add(lok3);
		Combination c = new Combination("3H",lokums);
		assertTrue(c.repOk());
		assertFalse(c.isSpecial());
	}
	
}
