package Assignment2;

public class BankAccount {
	
	protected double balance;
	
	//constructor only has one parameter
	public BankAccount(double initBalance){
		balance = initBalance;
	}
	
	//basic deposit function
    public void deposit(double amount) {  
       balance = balance + amount; 
    }
	
    public void withdraw(double amount) {  
        if (balance >=  amount)
            balance = balance - amount; 
    }
    
    //get method
	public double getBalance() {
		return balance;
	}

	//set method
	public void setBalance(double balance) {
		this.balance = balance;
	}
    
    
	
}
