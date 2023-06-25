import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	/* TODO: Add your own tests */
//	@Test
//	public void dummyTest() {
//		
//	}
	
	@Test
	public void testEmpty() {
		BST<Integer, Integer> bst = new BST<>();
		
		bst.put(1, 1);
		bst.put(0, 0);
		
		bst.remove(1);
		bst.remove(0);
		
		assertEquals(0, bst.size());

		assertTrue(bst.isEmpty());
		
		bst.put(12, 12);
		assertFalse(bst.isEmpty());
	}
	
	@Test
	public void testGet() {
		BST<Integer, Integer> bst = new BST<>();
		
		bst.put(1, 1);
		bst.put(0, 0);
		
		assertEquals(2, bst.size());
		
		assertEquals((Integer) 1, bst.get(1));
		assertEquals((Integer) 0, bst.get(0));
	}
	
}