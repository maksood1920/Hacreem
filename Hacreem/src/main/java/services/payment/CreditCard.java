package main.java.services.payment;

public class CreditCard implements Payment{

	@Override
	public boolean payment(int amount) {
     System.out.println("payment done through Credit Card");
		return true;
	}
	

}
