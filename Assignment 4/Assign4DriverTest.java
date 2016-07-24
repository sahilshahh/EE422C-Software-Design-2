package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Assign4DriverTest {

	@Test
	public void testOneCharDifference() {
		assertEquals(true, Assign4Driver.oneCharDifference("house", "mouse"));
		assertEquals(false, Assign4Driver.oneCharDifference("house", "mouth"));
		assertEquals(false, Assign4Driver.oneCharDifference("house", "mouth"));
	}

	@Test
	public void testValidWords() {
		ArrayList<String> dictionary = new ArrayList<String>();
		dictionary.add("mouse");
		dictionary.add("tooth");
		dictionary.add("brush");
		dictionary.add("house");
		assertEquals(true, Assign4Driver.validWords("house", "brush", dictionary));
		assertEquals(false, Assign4Driver.validWords("house", "broth", dictionary));
		assertEquals(false, Assign4Driver.validWords("dog", "brush", dictionary));
	}

}
