package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Assign4Driver
{
    public static void main(String[] args)
    {
		ArrayList<String> dictionary = new ArrayList<String>();
		dictionary = dictionaryFile(args[0]);
		ArrayList<String> input = new ArrayList<String>();
		input = inputFile(args[1]);
        // Create a word ladder solver object
	    HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        for (String thewords : dictionary) {
            graph.put(thewords, new ArrayList<String>());
        }
        populateValue(dictionary, graph);

        Assignment4Interface wordLadderSolver = new WordLadderSolver(graph);
        int x = 0;
        
        	while (x < input.size())
        	{
        		try{
	        		String word1 = input.get(x).substring(0,input.get(x).indexOf(' '));
	        		int y = input.get(x).indexOf(' ');
	        		while(input.get(x).charAt(y) == ' ')
	        		{
	        			y++;
	        		}
	        		String word2 = input.get(x).substring(y);
	        		if(validWords(word1, word2, dictionary))
	        		{
	        			List<String> result = wordLadderSolver.computeLadder(word1, word2);
	 //             	boolean correct = wordLadderSolver.validateResult(word1, word2, result);
	        			System.out.println("For the input words \"" + word1 + "\" and \"" + word2 + "\" the following word ladder was found");
	        			
	        			for(int z = 0; z < result.size(); z++)
	        			{
	        				System.out.print(result.get(z));
	        				if(z != (result.size() - 1))
	        					System.out.print(" ");
	        				else
	        					System.out.print("\n");
	        			}
	        			x++;
	        		}
	        		else{
	        			x++;
	        		}
	        		System.out.println("**********");
        		}
                catch (NoSuchLadderException e) 
                {
                    x++;
                	System.out.println(e.getMessage());
                	System.out.println("**********");
                }
        	}	
        } 
    
       /********************************************************************************
	   * Method Name: dictionaryFile                                                             *
	   * Purpose: opens the dictionary file and then reads each line until there are no*
	   * 		   more lines to read and stores each line as its own                  *
	   *           element in an arraylist.                                            *                
	   * Returns: arraylist of every line in the input file, each line is              *
	   * 		  it's own element in arraylist                                        *                       
	   ********************************************************************************/
	  static ArrayList<String> dictionaryFile(String filename)
	  {		  
		  ArrayList<String> inputlines = new ArrayList<String>();		  
		  try 
			{
				FileReader freader = new FileReader(filename);
				BufferedReader reader = new BufferedReader(freader);
				
				for (String s = reader.readLine(); s != null; s = reader.readLine()) 
				{ 
					if (s.charAt(0)!= '*'){
					    char[] chars = s.toCharArray();
					    for (int i = 0; i < chars.length; i++){
					    	if(!Character.isLetter(chars[i])){
					    		s = s.substring(0,i);
					    		i = chars.length;
					    	}
					    }		
						inputlines.add(s);
					}
				}
				reader.close();
				return inputlines;
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println ("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) 
			{
				System.err.println ("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}		  		  
		  return null;
	  }
	  
	  /********************************************************************************
	   * Method Name: inputFile                                                        *
	   * Purpose: opens the input file and then reads each line until there are no     *
	   * 		   more lines to read and stores each line as its own                  *
	   *           element in an arraylist.                                            *                
	   * Returns: arraylist of every line in the input file, each line is              *
	   * 		  it's own element in arraylist                                        *                       
	   ********************************************************************************/
	  static ArrayList<String> inputFile(String filename)
	  {
		  
		  ArrayList<String> inputlines = new ArrayList<String>();
		  
		  try 
			{
				FileReader freader = new FileReader(filename);
				BufferedReader reader = new BufferedReader(freader);
				
				for (String s = reader.readLine(); s != null; s = reader.readLine()) 
				{ 		
					inputlines.add(s);				
				}
				reader.close();
				return inputlines;
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println ("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) 
			{
				System.err.println ("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}		  		  
		  return null;
	  }
	  
	  
       /********************************************************************************
	   * Method Name: populateValue                                                    *
	   * Purpose: takes every key and finds the other keys that only differ one        *
	   * 		  character from it. All these other keys are placed into an arraylist *
	   *          which is the value for the primary key                               *                
	   * Returns: nothing                                                              *                       
	   ********************************************************************************/
	   public static void populateValue(ArrayList<String> words, HashMap<String, ArrayList<String>> graph) 
	   {
		   Set <String> primarykeys = graph.keySet();
	       //does this for every key in the graph
		   for (String x : primarykeys) {
		       //setting the value of the key to this arraylist
	           ArrayList<String> primaryterms = graph.get(x);
	  	       Set <String> secondarykeys = graph.keySet(); 
	  	       //does this for every key in the graph
	           for (String y : secondarykeys) {
				   //setting the value of the key to this arraylist
	               ArrayList<String> secondaryterms = graph.get(y);
	               //if the keys differ by one letter then they will be added to the arraylists
	               if (oneCharDifference(x,y)) {
	                   if (!primaryterms.contains(y)) {
	                       primaryterms.add(y);
	                   }
	                   if (!secondaryterms.contains(x)) {
	                       secondaryterms.add(x);
	                   }
	               }
	               //overwrites the key with the updated values
	               graph.put(y, secondaryterms);
	           }
               //overwrites the key with the updated values
	           graph.put(x, primaryterms);
	       }
	   }
	  
	  
       /********************************************************************************
	   * Method Name: oneCharDifference                                                *
	   * Purpose: to see whether the two words are the same except for one letter      *
	   * Returns: true or false                                                        *                       
	   ********************************************************************************/
	   public static boolean oneCharDifference(String word1, String word2) {
	       int difference = 0;
	       for (int i = 0; i < word1.length(); i++) {
	           if (word1.charAt(i) != word2.charAt(i)) {
	               difference++;
	           }
	       }
	       if (difference == 1){
	    	   return true;
	       }
	       else{
	    	   return false;
	       }
	   }
	   
       /********************************************************************************
	   * Method Name: validWords                                                       *
	   * Purpose: checks if the two words are valid input words                        *
	   * Returns: true or false                                                        *                       
	   ********************************************************************************/
	   
	   public static boolean validWords(String first, String last, ArrayList<String> dict)
	   {
		   if(first.length() != 5 || last.length() != 5)
		   {
			    System.out.println("For the input words \"" + first + "\" and \"" + last + "\" ");
   				//changes err to out
			    System.out.println("At least one of the words " + first + " and " + last 
   					+ " are not legitimate 5-letter words from the dictionary.");
   				return false;
		   }
		   
		   if(!dict.contains(first) || !dict.contains(last))
		   {
			    System.out.println("For the input words \"" + first + "\" and \"" + last + "\" ");
   				//changed err to out
			    System.out.println("At least one of the words " + first + " and " + last 
   					+ " are not legitimate 5-letter words from the dictionary.");
   				return false;
		   }

		   return true;
	   }
}
