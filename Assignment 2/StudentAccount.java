package Assignment2;

public class StudentAccount extends BankAccount {

	public StudentAccount(double initBalance) {
		super(initBalance);
		// TODO Auto-generated constructor stub
	}

	//creates an interest method that is unique to the StudentAccount class
	public void interest(){
		if (this.balance > 1000){
			double interest = this.balance * .04;
			balance = balance + interest;
		}
		return;
	}
}
