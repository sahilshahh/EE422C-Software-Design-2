package Assignment2;

public class Customer {
	
	protected String name;
	protected int number;
	protected String address;
	protected CheckingAccount checking;
	protected SavingsAccount savings;
	protected CarAccount car;
	protected StudentAccount student;

	
	public Customer(String customerName, int accountNumber, String customerAddress, CheckingAccount check, SavingsAccount save, CarAccount cars, StudentAccount loan){
		name = customerName;
		number = accountNumber;
		address = customerAddress;
		checking = check;
		savings = save;
		car = cars;
		student= loan;
	}

	//get method
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}
	
	//set method
	public void setNumber(int number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
