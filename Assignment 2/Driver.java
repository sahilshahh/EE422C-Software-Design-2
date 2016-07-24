package Assignment2;

import javax.swing.JOptionPane;

public class Driver {

	public static void main(String[] args) {
		
		//creating the 10 customers for the bank
		CheckingAccount checking0 = new CheckingAccount(0);
		SavingsAccount primary0 = new SavingsAccount(0);
		CarAccount car0 = new CarAccount(0);
		StudentAccount student0 = new StudentAccount(0);
		Customer Zero = new Customer("Zero", 0, "Plano", checking0, primary0, car0, student0);
		
		CheckingAccount checking1 = new CheckingAccount(0);
		SavingsAccount primary1 = new SavingsAccount(0);
		CarAccount car1 = new CarAccount(0);
		StudentAccount student1 = new StudentAccount(0);
		Customer One = new Customer("One", 1, "Plano", checking1, primary1, car1, student1);

		CheckingAccount checking2 = new CheckingAccount(0);
		SavingsAccount primary2 = new SavingsAccount(0);
		CarAccount car2 = new CarAccount(0);
		StudentAccount student2 = new StudentAccount(0);
		Customer Two = new Customer("Two", 2, "Plano", checking2, primary2, car2, student2);
		
		CheckingAccount checking3 = new CheckingAccount(0);
		SavingsAccount primary3 = new SavingsAccount(0);
		CarAccount car3 = new CarAccount(0);
		StudentAccount student3 = new StudentAccount(0);
		Customer Three = new Customer("Three", 3, "Plano", checking3, primary3, car3, student3);
		
		CheckingAccount checking4 = new CheckingAccount(0);
		SavingsAccount primary4 = new SavingsAccount(0);
		CarAccount car4 = new CarAccount(0);
		StudentAccount student4 = new StudentAccount(0);
		Customer Four = new Customer("Four", 4, "Plano", checking4, primary4, car4, student4);
		
		CheckingAccount checking5 = new CheckingAccount(0);
		SavingsAccount primary5 = new SavingsAccount(0);
		CarAccount car5 = new CarAccount(0);
		StudentAccount student5 = new StudentAccount(0);
		Customer Five = new Customer("Five", 5, "Plano", checking5, primary5, car5, student5);
		
		CheckingAccount checking6 = new CheckingAccount(0);
		SavingsAccount primary6 = new SavingsAccount(0);
		CarAccount car6 = new CarAccount(0);
		StudentAccount student6 = new StudentAccount(0);
		Customer Six = new Customer("Six", 6, "Plano", checking6, primary6, car6, student6);
		
		CheckingAccount checking7 = new CheckingAccount(0);
		SavingsAccount primary7 = new SavingsAccount(0);
		CarAccount car7 = new CarAccount(0);
		StudentAccount student7 = new StudentAccount(0);
		Customer Seven = new Customer("Seven", 7, "Plano", checking7, primary7, car7, student7);
		
		CheckingAccount checking8 = new CheckingAccount(0);
		SavingsAccount primary8 = new SavingsAccount(0);
		CarAccount car8 = new CarAccount(0);
		StudentAccount student8 = new StudentAccount(0);
		Customer Eight = new Customer("Eight", 8, "Plano", checking8, primary8, car8, student8);
		
		CheckingAccount checking9 = new CheckingAccount(0);
		SavingsAccount primary9 = new SavingsAccount(0);
		CarAccount car9 = new CarAccount(0);
		StudentAccount student9 = new StudentAccount(0);
		Customer Nine = new Customer("Nine", 9, "Plano", checking9, primary9, car9, student9);
		
		//creating an array of customers
		Customer[] customerarray = {Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine};
		String end = "N";
		
		//main loop where transactions are taken care of
		while(end.equals("N")){
			String transaction;
			transaction = JOptionPane.showInputDialog("Input the transaction string");
			
			//checks whether the input to the option pane is valid
			//if not, the dialog box is shown again
			if ((transaction != null) && (transaction.length() > 0)) {
				String[] elements = transaction.split(" ");
				
				//calls the checkError function to see if there is an error with the transaction string
				if(checkError(elements)){
					int customerID = Integer.parseInt(elements[0]);					
					switch(elements[1]){
					
					//deposit case
					case "D":
						double amount = Double.parseDouble(elements[2]);
						if (elements[3].equals("C")){
							customerarray[customerID].checking.deposit(amount);
							JOptionPane.showMessageDialog(null,"Deposited $" + amount + " to checking account");
						}
						else if (elements[3].equals("S")){
							customerarray[customerID].savings.deposit(amount);
							JOptionPane.showMessageDialog(null,"Deposited $" + amount + " to savings account");
						}
						else if (elements[3].equals("L")){
							customerarray[customerID].student.deposit(amount);
							JOptionPane.showMessageDialog(null,"Deposited $" + amount + " to student loan account");
						}
						else{
							customerarray[customerID].car.deposit(amount);
							JOptionPane.showMessageDialog(null,"Deposited $" + amount + " to car loan account");
						}
						break;
					
					//withdraw case
					case "W":
						
						double amount1 = Double.parseDouble(elements[2]);
						if (elements[3].equals("C")){
							//special withdraw for the checking account
							double newBalance = customerarray[customerID].checking.getBalance() - amount1;
							double checkingBalance;
							double savingsBalance;
							if (newBalance > -1){
								customerarray[customerID].checking.withdraw(amount1);
								checkingBalance = amount1;
								savingsBalance = 0;
							}
							else{
								checkingBalance = customerarray[customerID].checking.getBalance();
								customerarray[customerID].checking.withdraw(amount1);
								newBalance = newBalance * -1;
								newBalance = newBalance + 20;
								customerarray[customerID].savings.withdraw(newBalance);
								savingsBalance  = newBalance;
							}
							JOptionPane.showMessageDialog(null,"Wtihdrew $" + checkingBalance + " from checking account" + "\n" + "Wtihdrew $" + savingsBalance + " from savings account");
							
						}
						else if (elements[3].equals("S")){
							customerarray[customerID].savings.withdraw(amount1);
							JOptionPane.showMessageDialog(null,"Wtihdrew $" + amount1 + " from savings account");
						}
						else if (elements[3].equals("L")){
							customerarray[customerID].student.withdraw(amount1);
							JOptionPane.showMessageDialog(null,"Wtihdrew $" + amount1 + " from student loan account");
						}
						else{
							customerarray[customerID].car.withdraw(amount1);
							JOptionPane.showMessageDialog(null,"Wtihdrew $" + amount1 + " from car loan account");
						}
						break;
					
					//interest case
					case "I":
						
						if (elements[2].equals("C")){
							JOptionPane.showMessageDialog(null,"Can't calculate interest for a checking account");
						}
						else if (elements[2].equals("S")){
							customerarray[customerID].savings.interest();
							JOptionPane.showMessageDialog(null,"New balance in savings account: $" + customerarray[customerID].savings.getBalance() );
						}
						else if (elements[2].equals("L")){
							customerarray[customerID].student.interest();
							JOptionPane.showMessageDialog(null,"New balance in student loan account: $" + customerarray[customerID].student.getBalance() );
						}
						else{
							customerarray[customerID].car.interest();
							JOptionPane.showMessageDialog(null,"New balance in car loan account: $" + customerarray[customerID].car.getBalance() );
						}
						break;
	
					//transfer case
					case "T":
						
						double amount2 = Double.parseDouble(elements[2]);
						//since there are 2 accounts for this transaction there are 16 different possible outcomes
						if (elements[3].equals("C")){
							double check = customerarray[customerID].checking.getBalance() - amount2;
							if (check > -1){
								
								if (elements[4].equals("C")){
									customerarray[customerID].checking.deposit(amount2);
									customerarray[customerID].checking.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from checking to checking" );
								}
								else if (elements[4].equals("S")){
									customerarray[customerID].savings.deposit(amount2);
									customerarray[customerID].checking.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from checking to savings" );
								}
								else if (elements[4].equals("L")){
									customerarray[customerID].student.deposit(amount2);
									customerarray[customerID].checking.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from checking to student laons" );
								}
								else{
									customerarray[customerID].car.deposit(amount2);
									customerarray[customerID].checking.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from checking to car loans" );
								}
							}
							else{
								//not enough money to transfer
								JOptionPane.showMessageDialog(null,"Not enough money to transfer. Please try again.");
							}
						}
						
						else if (elements[3].equals("S")){						
							double check = customerarray[customerID].savings.getBalance() - amount2;
							if (check > -1){
								
								if (elements[4].equals("C")){
									customerarray[customerID].checking.deposit(amount2);
									customerarray[customerID].savings.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from savings to checking" );
								}
								else if (elements[4].equals("S")){
									customerarray[customerID].savings.deposit(amount2);
									customerarray[customerID].savings.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from savings to savings" );
								}
								else if (elements[4].equals("L")){
									customerarray[customerID].student.deposit(amount2);
									customerarray[customerID].savings.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from savings to student loans" );
								}
								else{
									customerarray[customerID].car.deposit(amount2);
									customerarray[customerID].savings.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from savings to car loans" );
								}
							}
							else{
								//not enough money to transfer
								JOptionPane.showMessageDialog(null,"Not enough money to transfer. Please try again.");
							}
						}
						
						else if (elements[3].equals("L")){
							double check = customerarray[customerID].student.getBalance() - amount2;
							if (check > -1){
								
								if (elements[4].equals("C")){
									customerarray[customerID].checking.deposit(amount2);
									customerarray[customerID].student.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from student loans to checking" );
								}
								else if (elements[4].equals("S")){
									customerarray[customerID].savings.deposit(amount2);
									customerarray[customerID].student.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from student loans to savings" );
								}
								else if (elements[4].equals("L")){
									customerarray[customerID].student.deposit(amount2);
									customerarray[customerID].student.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from student loans to student loans" );
								}
								else{								
									customerarray[customerID].car.deposit(amount2);
									customerarray[customerID].student.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from student loans to car loans" );
								}
							}
							else{
								//not enough money to transfer
								JOptionPane.showMessageDialog(null,"Not enough money to transfer. Please try again.");
							}
						}
						else{
							double check = customerarray[customerID].car.getBalance() - amount2;
							if (check > -1){
								
								if (elements[4].equals("C")){
									customerarray[customerID].checking.deposit(amount2);
									customerarray[customerID].car.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from car loans to checking" );
								}
								else if (elements[4].equals("S")){
									customerarray[customerID].savings.deposit(amount2);
									customerarray[customerID].car.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from car loans to savings" );
								}
								else if (elements[4].equals("L")){
									customerarray[customerID].student.deposit(amount2);
									customerarray[customerID].car.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from car loans to student loans" );
								}
								else{								
									customerarray[customerID].car.deposit(amount2);
									customerarray[customerID].car.withdraw(amount2);
									JOptionPane.showMessageDialog(null,"Transfered $" + amount2 + " from car loans to car loans" );
								}
							}
							else{
								//not enough money to transfer
								JOptionPane.showMessageDialog(null,"Not enough money to transfer. Please try again.");
							}
						}
						break;
	
					//shows current balance
					case "G":
						
						if (elements[2].equals("C")){
							double balance = customerarray[customerID].checking.getBalance();
							JOptionPane.showMessageDialog(null,"Checking account balance: $" + balance);
						}
						else if (elements[2].equals("S")){
							double balance = customerarray[customerID].savings.getBalance();
							JOptionPane.showMessageDialog(null,"Savings account balance: $" + balance);
						}
						else if (elements[2].equals("L")){
							double balance = customerarray[customerID].student.getBalance();
							JOptionPane.showMessageDialog(null,"Student loan account balance: $" + balance);
						}
						else{
							double balance = customerarray[customerID].car.getBalance();
							JOptionPane.showMessageDialog(null,"Car loan account balance: $" + balance);
						}
						break;
	
					}					
				}
				//end transactions joptionpane
				end = JOptionPane.showInputDialog("Would you like to end your transactions? (Y/N)");
				if ((end == null) || (!(end.length() > 0))) {
					end = "Y";
				}
			}
		}
		
		//prints out the final values of the instance values for every customer
		for (int i = 0; i<10;i++){
			String checking = "Checking account: $" + customerarray[i].checking.getBalance();
			String savings = "Savings account: $" + customerarray[i].savings.getBalance();
			String student = "Student loan account: $" + customerarray[i].student.getBalance();
			String car = "Car loan account: $" + customerarray[i].car.getBalance();
			JOptionPane.showMessageDialog(null,"Name: " + customerarray[i].getName()+ "\n" + "Address: " + customerarray[i].getAddress() + "\n" + "Number: " + customerarray[i].getNumber() + "\n" + checking+ "\n" + savings+ "\n" + student+ "\n" + car);
		}
	}
	
	/******************************************************************************
	* Method Name: checkError                                                     *
	* Purpose: Checks if the transaction String has any errors in it. If it does  *
	*          it outputs an error using joptionpane and returns false.           *
	* Returns: True of false                                                      *
	******************************************************************************/
	public static boolean checkError(String[] transaction){
		if (transaction.length > 2){
			int customerID = Integer.parseInt(transaction[0]);
			//checks whether the customer id is valid
			if (customerID>-1 && customerID < 10){
				//checks if the transaction is valid
				if (transaction[1].equals("D")||transaction[1].equals("W")||transaction[1].equals("I")||transaction[1].equals("T")||transaction[1].equals("G")){
					//if the 3rd element in the string is a letter, it checks whether the letter is a valid account type
					if(transaction[2].matches("^[-a-zA-Z]+")){
						if(transaction[2].equals("S")||transaction[2].equals("C")||transaction[2].equals("L")||transaction[2].equals("A")){
							return true;
						}
						else{
							//illegal account type
							JOptionPane.showMessageDialog(null,"Illegal account type. Please try again.");
							return false;
						}
					}
					//if the 3rd element is a number, it checks if the number is positive
					else{
						double amount = Double.parseDouble(transaction[2]);
						if(amount>0){
							//checks if the 4th element is a valid account type
							if(transaction[3].equals("S")||transaction[3].equals("C")||transaction[3].equals("L")||transaction[3].equals("A")){
								//if there is a 5th element it checks whether it is a valid account type
								if(transaction.length == 5){
									if(transaction[4].equals("S")||transaction[4].equals("C")||transaction[4].equals("L")||transaction[4].equals("A")){
										return true;
									}
									else{
										//illegal account type
										JOptionPane.showMessageDialog(null,"Illegal account type. Please try again.");
										return false;
									}
								}
								return true;
							}
							else{
								//illegal account type
								JOptionPane.showMessageDialog(null,"Illegal account type. Please try again.");
								return false;
							}
	
						}
						else{
							//negative amount error
							JOptionPane.showMessageDialog(null,"Negative ammount error. Please try again.");
							return false;
						}
					}
				}
				else{
					//illegal transaction error
					JOptionPane.showMessageDialog(null,"Illegal transaction type. Please try again.");
					return false;
				}
			}
			else{
				//illegal id error
				JOptionPane.showMessageDialog(null,"Illegal ID number. Please try again.");
				return false;
			}
		}
		else{
			//less than 3 inputs
			JOptionPane.showMessageDialog(null,"Wrong input. Please try again.");
			return false;
		}		
	}
}
