package Assignment2;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(double initBalance) {
		super(initBalance);
	}
	
	//creates an interest method that is unique to the SavingsAccount class
	public void interest(){
		if (this.balance > 1000){
			double interest = this.balance * .04;
			balance = balance + interest;
		}
		return;
	}

}
