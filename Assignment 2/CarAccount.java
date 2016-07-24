package Assignment2;

public class CarAccount extends BankAccount {

	public CarAccount(double initBalance) {
		super(initBalance);
		// TODO Auto-generated constructor stub
	}

	//creates an interest method that is unique to the CarAccount class
	public void interest(){
		if (this.balance > 1000){
			double interest = this.balance * .04;
			balance = balance + interest;
		}
		return;
	}
}
