package Assignment3;

public class Grocery extends Item {

	//variables, constructor here
	protected boolean perishable;
	
	protected Grocery(String itemname, double itemprice, int itemquantity, int itemweight, String itemperish) {
		super(itemname, itemprice, itemquantity, itemweight);
		if (itemperish.equals("P")){
			perishable = true;
		}
		else{
			perishable = false;
		}
	}
	
	public Grocery() {
		// TODO Auto-generated constructor stub
	}

	double calculatePrice(){
		double final_price = 0;
		double shipping = 0;
		if (perishable = true){
			shipping = (.2*weight*quantity)*1.2;
		}
		else{
			shipping = (.2*weight*quantity);
		}
		double totalprice = price * quantity;
		final_price = totalprice + shipping;
		return final_price;
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
		System.out.print("Name: " + name + "\n");
		System.out.print("Price: $" + price + "\n");
		System.out.print("Weight: " + weight + "\n");
		System.out.print("Quantity: " + quantity + "\n");
		System.out.print("Perishable: " + perishable + "\n");
	}

	public boolean isPerishable() {
		return perishable;
	}

	public void setPerishable(boolean perishable) {
		this.perishable = perishable;
	}
	
	
	
}
