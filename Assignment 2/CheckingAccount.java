package Assignment2;

public class CheckingAccount extends BankAccount {

	public CheckingAccount(double initBalance) {
		super(initBalance);
	}
	
	//overloading the withdraw method from the bankaccount class
	//if the balance is greater than the amount balance goes to zero
	public void withdraw(double amount){
		if (balance >=  amount){
            balance = balance - amount;
            
		}
		else{
			balance = 0;
		}
	}
		

}
