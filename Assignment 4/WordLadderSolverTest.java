package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordLadderSolverTest {

	static ArrayList<String> dictionary;
	static HashMap<String, ArrayList<String>> graph;
	static Assignment4Interface wordLadderSolver;
	
	@BeforeClass
	public static void setup(){
		dictionary = new ArrayList<String>();
		dictionary = Assign4Driver.dictionaryFile("A4words.dat");
	    graph = new HashMap<String, ArrayList<String>>();
        for (String thewords : dictionary) {
            graph.put(thewords, new ArrayList<String>());
        }
        Assign4Driver.populateValue(dictionary, graph);
        wordLadderSolver = new WordLadderSolver(graph);
	}
	@Test
	public void testComputeLadder() throws NoSuchLadderException {
		List<String> result = wordLadderSolver.computeLadder("dears", "fears");
		int resultsize = result.size();
		assertTrue(resultsize == 2);
		
		try{
			List<String> result1 = wordLadderSolver.computeLadder("devil", "angel");
			int resultsize1 = result1.size();
			assertTrue(resultsize1 == 2);
		    fail("should throw exception");
		}
		catch(NoSuchLadderException e){
			String message = e.getMessage();
			assertEquals(message,"There is no word ladder between devil and angel!");
		}
		
		try{
			List<String> result1 = wordLadderSolver.computeLadder("devil", "devil");
			int resultsize1 = result1.size();
			assertTrue(resultsize1 == 2);
		    fail("should throw exception");
		}
		catch(NoSuchLadderException e){
			String message = e.getMessage();
			assertEquals(message,"There is no word ladder between devil and devil!");
		}
		
		List<String> result2 = wordLadderSolver.computeLadder("heads", "teals");
		int resultsize2 = result2.size();
		assertTrue(resultsize2 == 3);
	}
}
