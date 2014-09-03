package cuckoo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

public class CuckooTest {

	@Test
	public void test() {
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(5, "First");
		cuckoo.put(5, "Second");
		//cuckoo.put(5, "Check Infinite");
		cuckoo.put(6, "CheckA");
		cuckoo.put(22, "CheckB");
		assertTrue(cuckoo.contains(5));
		assertFalse(cuckoo.contains(8));
		
	}
	
	@Test
	public void testGet(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(7, "Sean");
		cuckoo.put(54, "Brian");
		cuckoo.put(1, "Rose");
		cuckoo.put(23, "Jordan");
		cuckoo.put(6, "Jay");
		
		assertEquals(cuckoo.get(7),"Sean");
		assertEquals(cuckoo.get(54), "Brian");
		assertEquals(cuckoo.get(1), "Rose");
		assertEquals(cuckoo.get(23), "Jordan");
		assertEquals(cuckoo.get(6), "Jay");
	}
	
	@Test
	public void testOverwrite(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(1332,"Waters");
		cuckoo.put(2110, "Leahy");
		cuckoo.put(-4, "UGA");
		assertEquals(cuckoo.get(-4),"UGA");
		cuckoo.put(-4, "Negative Four");
		assertEquals(cuckoo.get(-4),"Negative Four");
	}
	
	@Test
	public void testRegrow(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(1, "1");
		cuckoo.put(2, "2");
		cuckoo.put(3, "3");
		cuckoo.put(4, "4");
		cuckoo.put(5, "5");
		cuckoo.put(6, "6");
		cuckoo.put(7, "7");
		cuckoo.put(8, "8");
		cuckoo.put(9, "9");
		cuckoo.put(11, "1");
		cuckoo.put(21, "2");
		cuckoo.put(31, "3");
		cuckoo.put(41, "4");
		assertEquals(cuckoo.getTotalSize(), 66);
	}
	
	@Test
	public void testInfiniteLoopCheck(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(5, "First");
		cuckoo.put(21, "Second");
		cuckoo.put(37, "Third");
		cuckoo.put(53, "Fourth");
		cuckoo.put(69, "Fifth");
		cuckoo.put(85, "Sixth");
		cuckoo.put(101, "Seventh");
		cuckoo.put(117, "Eight");
		cuckoo.put(133, "Nine");
		cuckoo.put(149, "Ten");
		cuckoo.put(165, "Eleven");
		cuckoo.put(171, "Twelve");
		cuckoo.put(187, "Thirteen");
		
		// Could not force an infinite loop!
		assertEquals(cuckoo.getTotalSize(),66);
	}

	@Test
	public void testRemove(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(1, "1");
		cuckoo.put(2, "2");
		cuckoo.put(3, "3");
		cuckoo.put(4, "4");
		cuckoo.put(5, "5");
		cuckoo.put(6, "6");
		cuckoo.put(7, "7");
		cuckoo.put(8, "8");
		cuckoo.put(9, "9");
		assertTrue(cuckoo.contains(8));
		cuckoo.remove(8);
		assertEquals(cuckoo.numEntries(), 8);
		assertFalse(cuckoo.contains(8));
	}
	
	@Test
	public void testClear(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(1, "1");
		cuckoo.put(2, "2");
		cuckoo.put(3, "3");
		cuckoo.put(4, "4");
		cuckoo.put(5, "5");
		cuckoo.put(6, "6");
		assertTrue(cuckoo.contains(4));
		assertTrue(cuckoo.contains(5));
		cuckoo.clear();
		assertFalse(cuckoo.contains(4));
		assertFalse(cuckoo.contains(5));
		assertEquals(cuckoo.numEntries(), 0);
	}
	
	@Test
	public void testValues(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(1, "1");
		cuckoo.put(2, "29");
		cuckoo.put(3, "32");
		cuckoo.put(4, "43");
		cuckoo.put(5, "58");
		cuckoo.put(6, "7");
		ArrayList list = (ArrayList)(cuckoo.values());
		assertTrue(list.contains("1"));
		assertTrue(list.contains("29"));
		assertTrue(list.contains("32"));
		assertTrue(list.contains("43"));
		assertTrue(list.contains("58"));
		assertTrue(list.contains("7"));	
	}
	
	@Test
	public void testKeySet(){
		Cuckoo<Integer,String> cuckoo = new Cuckoo<Integer,String>();
		cuckoo.put(1, "1");
		cuckoo.put(21, "2");
		cuckoo.put(35, "3");
		cuckoo.put(14, "4");
		cuckoo.put(57, "5");
		cuckoo.put(16, "6");
		Set<Integer> list = (cuckoo.keySet());
		assertTrue(list.contains(1));
		assertTrue(list.contains(21));
		assertTrue(list.contains(35));
		assertTrue(list.contains(14));
		assertTrue(list.contains(57));
		assertTrue(list.contains(16));

	}
	

}
