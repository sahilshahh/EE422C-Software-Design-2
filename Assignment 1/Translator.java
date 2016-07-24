package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Translator 
{
	
	public static void main (String args[]) 
	{ 
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile (args[0]);
		
	}

	/******************************************************************************
	* Method Name: processLinesInFile                                             *
	* Purpose: Opens the file specified in String filename, reads each line in it *
	*          Invokes translate () on each line in the file, and prints out the  *
	*          translated piglatin string.                                        *
	* Returns: None                                                               *
	******************************************************************************/
	public static void processLinesInFile (String filename) 
	{ 

		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String pigLatin = translator.translate(s);
				System.out.println(pigLatin);
			}
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
	}
	
	/******************************************************************************
	* Method Name: translate                                                      *
	* Purpose: Converts String inputString into piglatin based on rules specified *
	*          in your assignment write-up.                                       *
	* Returns: String object containing the piglatin translation of               *
	*          String inputString                                                 *
	******************************************************************************/
	
	public String translate (String inputString) 
	{ 
		//takes the inputString and splits it into separate words
		String[] arr = inputString.split(" ");
		
		int m = arr.length;
		int wordNumber = 0;
		int ruleNumber = 0;
		String newWord = "";
		String outputString = "";
		
		//formulates the outputString using helper functions
		while(m != 0){
			ruleNumber = rule(arr[wordNumber]);
			newWord = implement(ruleNumber, arr[wordNumber]);
			wordNumber++;
			m--;
			outputString = outputString + " " + newWord; 
		}
		
		return outputString.substring(1);
	}
	
	/******************************************************************************
	* Method Name: punctuation                                                    *
	* Purpose: Takes a string and determines if it has any punctuation mark at    *
	*          end                                                                *
	* Returns: True or false depending on if there is punctuation at the end      *
	******************************************************************************/
	
	public boolean punctuation(String string)
	{
		String punctuation = ".,:;!?";
		int n = string.length();
		string = string.substring(n-1);
		
		//checks if the last character of the string is a punctuation mark
		if(punctuation.contains(string)){
			return true;
		}
		else{
			return false;
		}		
	}
	
	/******************************************************************************
	* Method Name: implement                                                      *
	* Purpose: Converts String string into piglatin based on the rule given by    *
	*          the rule function.                                                 *
	* Returns: String object containing the piglatin translation of               *
	*          String string                                                      *
	******************************************************************************/
	
	public String implement(int ruleNumber, String string) 
	{	
		String newString = "";
		switch(ruleNumber){
		
		//words that begin with a single consonant
		case 1:
			if(punctuation(string)){
				int n = string.length();
				newString = string.substring(1,n-1)+string.substring(0, 1)+ "ay" + string.substring(n-1);
			}
			else{
				newString = string.substring(1)+string.substring(0, 1)+ "ay";
			}
			return newString;
		
		//words that begin with double or multiple consonants
		case 2:
			int n = string.length();
			String vowels = "aeiou";
			int index=0;
			if(punctuation(string)){
				//checks every character in word to see where the first vowel is so
				//that it can create the new string
				for (index = 0; index < string.length(); index++)
			    {
			        if (vowels.contains(String.valueOf(string.charAt(index))))
			        {
			        	newString = string.substring(index, n-1) + string.substring(0,index)+"ay" + string.substring(n-1);
			        	return newString;
			        }
			    }
				//if there are no vowels in the word, this code is executed
				newString = string.substring(0,n-1)+"ay"+string.substring(n-1);
				return newString;
			}
			else{
				//checks every character in word to see where the first vowel is so
				//that it can create the new string
				for (index = 0; index < string.length(); index++)
			    {
			        if (vowels.contains(String.valueOf(string.charAt(index))))
			        {
			        	newString = string.substring(index) + string.substring(0,index)+"ay";
			        	return newString;
			        }
			    }
				//if there are no vowels in the word, this code is executed
				newString = string.substring(0,n)+"ay";
				return newString;
			}
		
		//words that begin with a vowel
		case 3:
			if(punctuation(string)){
				int f = string.length();
				newString = string.substring(0,f-1)+"yay"+string.substring(f-1);
			}
			else{
				newString = string + "yay";
			}
			return newString;
		
		//hyphenated words
		case 4:
			//splits the hyphenated word into two words
			String[] arr = string.split("-");
			int m = arr.length;
			int rule;
			String word;
			int counter = 0;

			//finds the rule to be applied on each word
			while(m!=0){
				rule = rule(arr[counter]);
				word = implement(rule, arr[counter]);
				m--;
				counter++;
				newString = newString + "-" + word; 
			}
			
			return newString.substring(1);
			
		//contracted words	
		case 5:
			int apostrophe = string.indexOf("'");
			
			//index of the letter after the apostrophe
			char afterapos = string.charAt(apostrophe+1);
			
			//gives you the string without the apostrophe mark
			String string1 = string.substring(0,apostrophe)+string.substring(apostrophe+1);
			
			int newRule = rule(string1);
			String string1New = implement(newRule, string1);
			
			//index of the letter that was after the apostrophe
			int afteraposindex = string1New.indexOf(afterapos);
			
			//places the apostrophe back into the string based off of the letter
			//that was after it in the original word
			newString = string1New.substring(0, afteraposindex)+ "'" + string1New.substring(afteraposindex);
			return newString;
		
		//words with no rule
		case 6:
			return string;
		}
		return string;
	}

	/******************************************************************************
	* Method Name: rule                                                           *
	* Purpose: Takes String inputString and decides which of the six rules it     *
	*          should follow.                                                     *
	* Returns: An integer containing the piglatin rule it should follow.            *
	******************************************************************************/
	
	public int rule (String inputString)
	{
		char[] c = inputString.toCharArray();
		
		//rule applied for an empty inputString
		if(inputString.isEmpty()){
			return 6;
		}
		
		//for if inputString is a single letter
		if(inputString.length() == 1){
			if("AEIOUaeiou".indexOf(c[0]) != -1){
				return 3;
			}
			else{
				return 1;
			}
		}
		
		//for if inputString has a number in it
		if(inputString.matches(".*\\d+.*")){
			return 6;
		}
		
		//for if inputString has a hyphen in it
		if(inputString.matches(".*-.*")){
			return 4;
		}
		
		//for if inputString has an apostrophe
		if(inputString.matches(".*'.*")){
			return 5;
		}
		
		//checks if there is punctuation inside the word
		String newinputString = inputString.substring(0, inputString.length()-1);
		if (!(newinputString.matches("^[-a-zA-Z]+"))){
			return 6;
		}
		
		//checks the first and second characters of the string to implement rules 1 or 2
		if("AEIOUaeiou".indexOf(c[0]) == -1){
			if("AEIOUaeiou".indexOf(c[1]) != -1){
				return 1;
			}
			else{
				return 2;
			}
		}
		
		//checks if the first character of the word is a vowel
		if("AEIOUaeiou".indexOf(c[0]) != -1){
			return 3;
		}
		
		//if all the checks fail, the word will be given rule 6
		return 6;
	}
}
