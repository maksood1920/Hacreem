package main.java.services.payment;

public class PayTm implements Payment {

	@Override
	public boolean payment(int amount) {
		int discount=50; // 50 percent discount with Paytm
		amount=amount-(amount*50/100);
		System.out.println("Payment done via paytm");
		return true;
	}
	
	

}
