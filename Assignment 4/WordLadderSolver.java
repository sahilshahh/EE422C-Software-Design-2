package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.
	private HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
	//private ArrayList<String> finalList = new ArrayList<String>();
    
	// add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver()
	{
		
	}
	
	public WordLadderSolver(HashMap<String, ArrayList<String>> graph)
	{
		hashMap = graph;
	}

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	Map<String, String> previousWord = new HashMap<String, String>();
    	Queue<String> queue = new LinkedList<String>();
    	
    	queue.add(startWord);
    	//this loop creates linkages between the starting word and all of the words that are one letter different.
    	//for each word that is one letter different, it finds the letters that are one letter different from it
    	while (!queue.isEmpty())
    	{
    		String store = queue.element();
    		queue.remove();
    		List<String> adj = hashMap.get(store);
    		
    		if(adj != null)
    		{
    			for(String next : adj)
    			{
    				if(previousWord.get(next) == null)
    				{
    					previousWord.put(next, store);
    					queue.add(next);
    				}
    			}
    		}
    	}   	
    	previousWord.put(startWord, null);   	    	
    	LinkedList<String> result = new LinkedList<String>();
    	//given the ending word, this loop goes back up the map and finds how the words relate to get to the first
    	if(previousWord.get(endWord) != null)
    	{
    		for(String s = endWord; s != null; s = previousWord.get(s))
    		{
    			result.addFirst(s);
    		}
    	}
    	
    	if (result.size()==0 ){
    		throw new NoSuchLadderException("There is no word ladder between " + startWord + " and " + endWord + "!");
    	}
    	return result;

    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
