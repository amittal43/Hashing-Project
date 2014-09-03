package hopscotch;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class HopscotchTest {

	@Test
	public void test() {
		Hopscotch<Integer,String> hopscotch = new Hopscotch<Integer,String>();
		hopscotch.put(1, "A");
		hopscotch.put(10, "B");
		hopscotch.put(3, "C");
		hopscotch.put(4, "D");
		hopscotch.put(31, "R");
		hopscotch.put(5, "S");
		hopscotch.put(7, "T");
		hopscotch.put(11, "X");
		
		assertEquals(hopscotch.numEntries(), 8);

	}
	
	@Test
	public void testRegrow(){
		Hopscotch<Integer,String> hopscotch = new Hopscotch<Integer,String>();
		hopscotch.put(1, "A");
		hopscotch.put(10, "B");
		hopscotch.put(3, "C");
		hopscotch.put(4, "D");
		hopscotch.put(31, "R");
		hopscotch.put(5, "S");
		hopscotch.put(7, "T");
		hopscotch.put(11, "X");
//		System.out.println(hopscotch.getSize());
		hopscotch.put(15, "A");
		hopscotch.put(100, "B");
		hopscotch.put(39, "C");
		hopscotch.put(49, "D");
		hopscotch.put(50, "R");
		hopscotch.put(99, "S");
		System.out.println(hopscotch.getSize());	
		hopscotch.put(75, "T");
		hopscotch.put(999, "X");
		assertEquals(hopscotch.getSize(), 49);
		
				
	}

	@Test
	public void testRemove(){
		Hopscotch<Integer,String> hopscotch = new Hopscotch<Integer,String>();
		hopscotch.put(1, "A");
		hopscotch.put(10, "B");
		hopscotch.put(3, "C");
		hopscotch.put(4, "D");
		hopscotch.put(31, "R");
		hopscotch.put(5, "S");
		hopscotch.put(7, "T");
		assertTrue(hopscotch.contains(7));
		hopscotch.remove(7);
		assertFalse(hopscotch.contains(7));
	}
	
	@Test
	public void testClear(){
		Hopscotch<Integer,String> hopscotch = new Hopscotch<Integer,String>();
		hopscotch.put(1, "A");
		hopscotch.put(10, "B");
		hopscotch.put(3, "C");
		hopscotch.put(4, "D");
		hopscotch.put(31, "R");
		hopscotch.put(5, "S");
		hopscotch.put(7, "T");
		hopscotch.clear();
		assertEquals(hopscotch.numEntries(), 0);
		assertFalse(hopscotch.contains(7));
		assertFalse(hopscotch.contains(31));
		assertFalse(hopscotch.contains(1));
	}
	
	@Test
	public void contains(){
		Hopscotch<Integer,String> hopscotch = new Hopscotch<Integer,String>();
		hopscotch.put(1, "A");
		hopscotch.put(10, "B");
		hopscotch.put(3, "C");
		hopscotch.put(4, "D");
		hopscotch.put(31, "R");
		hopscotch.put(5, "S");
		assertFalse(hopscotch.contains(7));
		assertTrue(hopscotch.contains(31));
		assertFalse(hopscotch.contains(110));
		assertTrue(hopscotch.contains(4));
		assertTrue(hopscotch.contains(5));
	}
	
	@Test
	public void testValues(){
		Hopscotch<Integer,String> hopscotch = new Hopscotch<Integer,String>();
		hopscotch.put(1, "A");
		hopscotch.put(10, "B");
		hopscotch.put(3, "C");
		hopscotch.put(4, "D");
		hopscotch.put(31, "R");
		hopscotch.put(5, "S");
		ArrayList list = (ArrayList)(hopscotch.values());
		assertTrue(list.contains("A"));
		assertTrue(list.contains("B"));
		assertTrue(list.contains("C"));
		assertTrue(list.contains("D"));
		assertTrue(list.contains("R"));
		assertTrue(list.contains("S"));	
	}
	
	
}
