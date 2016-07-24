package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class A3Driver 
	{

	  public static void main(String[] args) 
	  {		  		
		  ArrayList<String> fileinput = new ArrayList<String>();
		  fileinput = file(args[0]);
		  ArrayList<Item> shoppingCart = new ArrayList<Item>();
		  for (int i = 0; i < fileinput.size(); i++){
			  String[] temp = input(fileinput.get(i));
			  if(checkErrors(temp)){
				  switch(temp[0]){
				  	case "insert":
				  		Item temporary = createobjects(temp);
				  		insert(shoppingCart, temporary);
				  		break;
				  	case "delete":
				  		delete(shoppingCart, temp[1]);
				  		break;
				  	case "search":
				  		search(shoppingCart, temp[1]);
				  		break;
				  	case "update":
				  		int number = Integer.parseInt(temp[2]);
				  		update(shoppingCart, temp[1], number);
				  		break;
				  	case "print":
				  		print(shoppingCart);
				  }					  
			  }
		  }	
	  }
	  
	  
	  /*********************************************************************************
	  * Method Name: insert                                                            
	  * Purpose: takes a new item and inserts it into the arraylist(your shopping cart)
	  *          in alphabetical order. It can add 2 of the same elements, so that     
	  *          there can be 2 different hats      									   
	  * Returns: None                                                                  
	  * 																			   
	  * questions: 1. if it passes in an existing item should i just add it in again or   
	  * 		  update the current item quantity. what if the prices are different?  
	  * 
	  * 		 2. is Hat the same as hat? i did a toupperCase when comparing the strings
	  * 			
	  ******************************************************************************/	  
	  static void insert(ArrayList<Item> shoppingCart, Item input){ //yash
		  Iterator<Item> i = shoppingCart.iterator();
		  int x = 0;
		  boolean added = false;
		  String inputname = input.getName();
		  
			while (i.hasNext() && !added) 
			{
				boolean incremented = false;
				Item temp = i.next();
				String name = temp.getName();
				
                //if the shoppingcart word's first letter is greater than inputname's
                //first letter then add
				if(name.charAt(0) > inputname.charAt(0))
				{
					shoppingCart.add(x, input);
					added = true;
					System.out.println("The item " + input.getName()+ " was added to the shoppingcart");
				}
				
                //if first letter less than input's first letter then increment x
				if(name.charAt(0) < inputname.charAt(0))
				{
					x++;
					incremented = true;
				}
				
				
                //in case they have the same beginning letters, for example shirts and shorts
				if(name.charAt(0)==inputname.charAt(0))
				{
					for(int y = 1; y<name.length();y++)
					{
						
						if( y<inputname.length() && !added)
						{
							if( (name.charAt(y)>inputname.charAt(y)) )
							{
								shoppingCart.add(x, input);
								added = true;
								System.out.println("The item " + input.getName()+ " was added to the shoppingcart");
							}
						}
					}
				}
				
                //increment x if same beginning but shoppingcart name should come before input name
				if(!added && !incremented)
				{
					x++;
				}
			}

            //in case the item to be added is supposed to be the last entry in arraylist
			if(!added)
			{
				shoppingCart.add(shoppingCart.size(), input);
				added = true;
				System.out.println("The item " + input.getName()+ " was added to the shoppingcart");
			}
	  }
	  
	  /******************************************************************************
	  * Method Name: search                                                         *
	  * Purpose: Given the shopping cart and the name of the item, this function    *
	  *          finds the number of items in the shopping cart that match the given*
	  *          name                                                               *
	  * Returns: None                                                               *
	  ******************************************************************************/
	  static void search(ArrayList<Item> shoppingCart, String input){ //sahil
		  Iterator<Item> i = shoppingCart.iterator();
		  int quantity = 0;
		  //iterates through the shopping cart to find the variable that matches the input variable name
		  while (i.hasNext())
		  {
			  Item temp = i.next();
			  if (temp.getName().equals(input)){
				  quantity = quantity + temp.getQuantity();
			  }
		  }
		  System.out.print("There are/is " + quantity + " " + input + " in the shopping cart.");
		  System.out.print("\n");
	  }
	  
	  /******************************************************************************
	  * Method Name: delete                                                         *
	  * Purpose: Given the shopping cart and the name of the item, this function    *
	  *          deletes the items in the shopping cart that match the given name   *
	  *          and prints the amount of times that were deleted                   *
	  * Returns: None                                                               *
	  ******************************************************************************/
	  static void delete(ArrayList<Item> shoppingCart, String input){ //sahil
		  Iterator<Item> i = shoppingCart.iterator();
		  ArrayList<Integer> indexlist = new ArrayList<Integer>();
		  int index = 0;
		  int delete = 0;
		  //iterates through the shopping cart to find the variable that matches the input variable name
		  while (i.hasNext()) 
		  {
			  Item temp = i.next();
			  if (temp.getName().equals(input)){
				  //adds the index of the element to be deleted into an arraylist
				  indexlist.add(index);
				  delete++;
			  }
			  index++;
		  } 
		  int count =0;
		  Iterator<Item> j = shoppingCart.iterator();
		  while (j.hasNext()) 
		  {			  
		        j.next();
		        //removes the elements at the index specified by the indexlist
		        if(indexlist.contains(count)){
		            j.remove();
		        }
		        count++;
		  }
		  System.out.print(delete + " " + input + " " + "item/items were/was deleted.");
		  System.out.print("\n");
	  }
	  
	  
	  /******************************************************************************
	  * Method Name: update                                                         
	  * Purpose: input item and quantity change and it will update the quantity
	  * 		 as long as the change you want to make is >0.                                                          
	  * Returns: None                                               
	  * 
	  * questions: what if current item quantity = 3 but they want to take 1 out?
	  * 			would -1 be a legal input
	  ******************************************************************************/  
	  static void update(ArrayList<Item> shoppingCart, String input, int change){ //yash
		  Iterator<Item> i = shoppingCart.iterator();
		  boolean changed = false;
		  
		  if(change>=0)
		  {
			  while (i.hasNext()&& !changed) 
			  {
				  Item temp = i.next();
				  
				  if(temp.getName().equals(input))
				  {
					  temp.setQuantity(change);
					  System.out.println("The item " + temp.getName()+" has a new quantity of "+change);
					  changed = true;
				  }
			  }
		  }
	  }
	  
	  /******************************************************************************
	  * Method Name: print                                                          *
	  * Purpose: Given the shopping cart, this function prints out the instance     *
	  *          variables of every item as well as the total price of the item.    *
	  *          It then prints out the total price for the shopping cart           *
	  * Returns: None                                                               *
	  ******************************************************************************/
	  static void print(ArrayList<Item> shoppingCart){ //sahil
			Iterator<Item> i = shoppingCart.iterator();
			double cartprice = 0;
			while (i.hasNext()) 
			{
				Item temp = i.next();
				temp.printItemAttributes();
				double totalprice = temp.calculatePrice();
				cartprice= cartprice + totalprice;
				System.out.print("Total Item Price: $" + totalprice);
				System.out.print("\n");
				System.out.print("\n");
			}
			System.out.print("Total Shopping Cart Price: $" + cartprice);
	  }
	  
      /******************************************************************************
	  * Method Name: input                                                         
	  * Purpose: takes the whole input line string(comes from read file function)
	  * 		   and splits it into different words which are put in an array of 
	  * 		   strings without the spaces                                                               
	  * Returns: array of strings with separate words                                                                
	  ******************************************************************************/		  
	  static String[] input(String input){ //yash
			  
		  String[] inputs = input.split("\\s+");  
		  return inputs;  
			
	  }
	  
	  
	  /******************************************************************************
	  * Method Name: checkErrors                                                    *
	  * Purpose: Checks if the input string has any errors                          *
	  * Returns: None                                                               *
	  ******************************************************************************/
	  static boolean checkErrors(String[] transaction){ //sahil
		  transaction[0] = transaction[0].toLowerCase();
		  if (transaction[0].equals("insert")){
			  transaction[1] = transaction[1].toLowerCase();
			  if(transaction[1].equals("clothing")){
				  double amount = Double.parseDouble(transaction[3]);
				  if (amount > 0){
					  //checks if the value is too big for an integer to hold
					  if (Double.parseDouble(transaction[4])>(Math.pow(2, 32)-1)){
						    System.out.print("Invalid input. Number is too big.");
							System.out.print("\n");
						  	return false;
					  }
					  int amount1 = Integer.parseInt(transaction[4]);
					  if (amount1 >0){
						  try{
							  //checks if the value is too big for an integer to hold
							  if (Double.parseDouble(transaction[5])>(Math.pow(2, 32)-1)){
								  	System.out.print("Invalid input. Number is too big.");
									System.out.print("\n");
								  	return false;
							  }
							  //if the next line fails, the program goes to the catch
							  int amount2 = Integer.parseInt(transaction[5]);
							  if (amount2 > 0){
							  	return true;
						  	  }
						  	  else{
							  	System.out.print("Invalid input. Number isn't positive.");
								System.out.print("\n");
							  	return false;
						  	}
						  }
						  catch(Exception e){
								System.out.print("Invalid input. Number isn't an integer.");
								System.out.print("\n");
								return false;
						  }						  						  						  					
					  }
					  else{
						  System.out.print("Invalid input. Number isn't positive.");
						  System.out.print("\n");
						  return false;
					  }
				  }
				  else{
					  System.out.print("Invalid input. Number isn't positive.");
					  System.out.print("\n");
					  return false;
				  }
			  }
			  if(transaction[1].equals("grocery")){
				  double amount = Double.parseDouble(transaction[3]);
				  if (amount > 0){
					//checks if the value is too big for an integer to hold
					  if (Double.parseDouble(transaction[4])>(Math.pow(2, 32)-1)){
						  	System.out.print("Invalid input. Number is too big.");
							System.out.print("\n");
						  	return false;
					  }
					  int amount1 = Integer.parseInt(transaction[4]);
					  if (amount1 >0){
						  try{
							  //checks if the value is too big for an integer to hold
							  if (Double.parseDouble(transaction[5])>(Math.pow(2, 32)-1)){
								  	System.out.print("Invalid input. Number is too big.");
									System.out.print("\n");
								  	return false;
							  }
							  //if the next line fails, the program goes to the catch
							  int amount2 = Integer.parseInt(transaction[5]);
							  if (amount2 > 0){
								  if (transaction[6].equals("NP")|| transaction[6].equals("P")){
									  return true;
								  }
								  else{
									  System.out.print("Invalid input. Wrong perishable value.");
									  System.out.print("\n");
									  return false;
								  }
							  }
							  else{
								  System.out.print("Invalid input. Number isn't positive.");
								  System.out.print("\n");
								  return false;
						  		}
						  	}
						  	catch (Exception e){
						  		System.out.print("Invalid input. Number isn't an integer.");
						  		System.out.print("\n");
						  		return false;
						  	}
					  }
					  else{
						  System.out.print("Invalid input. Number isn't positive.");
						  System.out.print("\n");
						  return false;
					  }
				  }
				  else{
					  System.out.print("Invalid input. Number isn't positive.");
					  System.out.print("\n");
					  return false;
				  }
			  }
			  if(transaction[1].equals("electronics")){
				  String[] states = {
			                  "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
			                  "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
			                  "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
			                  "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
			                  "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY",
			                  "AS", "DC", "FM", "GU", "MH", "MP", "PR", "PW", "VI"
			      };
				  double amount = Double.parseDouble(transaction[3]);
				  if (amount > 0){
					//checks if the value is too big for an integer to hold
					  if (Double.parseDouble(transaction[4])>(Math.pow(2, 32)-1)){
						  	System.out.print("Invalid input. Number is too big.");
							System.out.print("\n");
						  	return false;
					  }
					  int amount1 = Integer.parseInt(transaction[4]);
					  if (amount1 >0){
						  try{
							//checks if the value is too big for an integer to hold
							  if (Double.parseDouble(transaction[5])>(Math.pow(2, 32)-1)){
								  	System.out.print("Invalid input. Number is too big.");
									System.out.print("\n");
								  	return false;
							  }
							  //if the next line fails, the program goes to the catch
							  int amount2 = Integer.parseInt(transaction[5]);
							  if (amount2 > 0){
								  if (transaction[6].equals("F")|| transaction[6].equals("NF")){
									  //checks if the input is a valid state
									  if (Arrays.asList(states).contains(transaction[7].toUpperCase())){
										  return true;
									  }
									  else{
										  System.out.print("Invalid input. Not a valid state.");
										  System.out.print("\n");
										  return false;
									  }
								  }
								  else{
									  System.out.print("Invalid input. Number isn't positive.");
									  System.out.print("\n");
									  return false;
								  }
							  }
							  else{
								  System.out.print("Invalid input. Number isn't positive.");
								  System.out.print("\n");
								  return false;
							  }
						  }
						  catch(Exception e){
							  System.out.print("Invalid input. Number isn't an integer.");
							  System.out.print("\n");
							  return false;
						  }
					  }
					  else{
						  System.out.print("Invalid input. Number isn't positive.");
						  System.out.print("\n");
						  return false;
					  }
				  }
				  else{
					  System.out.print("Invalid input. Number isn't positive.");
					  System.out.print("\n");
					  return false;
				  }
			  }
			  else{
				  System.out.print("Invalid input. Not a valid item.");
				  System.out.print("\n");
				  return false;
			  }
		  }
		  else if (transaction[0].equals("search")){
			  return true;
		  }
		  else if (transaction[0].equals("delete")){
			  return true;
		  }
		  else if (transaction[0].equals("update")){
			  int amount = Integer.parseInt(transaction[2]);
			  if (amount > 0){
				  return true;
			  }
			  else{
				  System.out.print("Invalid input. Number is negative.");
				  System.out.print("\n");
				  return false;
			  }
		  }
		  else if (transaction[0].equals("print")){
			  return true;
		  }
		  else {
			  System.out.print("Invalid input. Transaction is not valid.");
			  System.out.print("\n");
			  return false;
		  }
	  }
	  
	  
	  /******************************************************************************
	   * Method Name: file                                                         
	   * Purpose: opens the file and then reads each line until there are no
	   * 		   more lines to read and stores each line as its own
	   *           element in an arraylist.                                                            
	   * Returns: arraylist of every line in the input file, each line is
	   * 		  it's own element in arraylist                                                               
	   ******************************************************************************/
	  static ArrayList<String> file(String filename) //yash
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
	  
	  /******************************************************************************
	  * Method Name: createobjects                                                  *
	  * Purpose: Creates the item that is specified in the input string             *
	  * Returns: The item object                                                               *
	  ******************************************************************************/
	  public static Item createobjects(String[] input){
		  String temp = input[1];
		  temp = temp.toUpperCase();
		  Electronics elec =new Electronics();
		  Clothing cloth = new Clothing();
		  Grocery groc = new Grocery();
		  
		  if(temp.equals("CLOTHING"))
		  {
			cloth.setName(input[2]);
			double price = Double.parseDouble(input[3]);
			cloth.setPrice(price);
			int quantity = Integer.parseInt(input[4]);
			cloth.setQuantity(quantity);
			int weight = Integer.parseInt(input[5]);
			cloth.setWeight(weight);
			return cloth;
		  }
		  if(temp.equals("GROCERY"))
		  {
			  groc.setName(input[2]);
			  double price = Double.parseDouble(input[3]);
			  groc.setPrice(price);
			  int quantity = Integer.parseInt(input[4]);
			  groc.setQuantity(quantity);
			  int weight = Integer.parseInt(input[5]);
			  groc.setWeight(weight);
			  String itemperish = input[6];
			  if (itemperish.equals("P")){
			  	groc.setPerishable(true);;
			  }
			  else{
				groc.setPerishable(false);;
		      }
			  return groc;

		  }
		  if(temp.equals("ELECTRONICS"))
		  {
			  elec.setName(input[2]);
			  double price = Double.parseDouble(input[3]);
			  elec.setPrice(price);
			  int quantity = Integer.parseInt(input[4]);
			  elec.setQuantity(quantity);
			  int weight = Integer.parseInt(input[5]);
			  elec.setWeight(weight);
			  String fragileitem = input[6];
			  String itemstate = input[7];
			  elec.setState(itemstate);
			  if (fragileitem.equals("F")){
				elec.setFragile(true);
			  }
			  else{
			 	elec.setFragile(false);
		      }
			  if (itemstate.equals("TX")||itemstate.equals("NM")||itemstate.equals("VA")||itemstate.equals("AZ")||itemstate.equals("AK") ){
				elec.setSalestax(true);
			  }
			  else{
			 	elec.setSalestax(false);
			  }				
			  return elec;
		  }
		  return null;  
	  }
}
