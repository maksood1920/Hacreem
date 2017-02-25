package main.java.servlet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;

import main.java.dao.InfDao;
import main.java.dao.InfoDao;
import main.java.pathfinding.ExampleFactory;
import main.java.pathfinding.ExampleNode;
import main.java.pathfinding.Map;
import main.java.services.feedback.CustomerFeedback;
import main.java.services.feedback.DriverFeedback;
import main.java.services.feedback.Feedback;
import main.java.services.payment.CreditCard;
import main.java.services.payment.Payment;


public class MainServlet {
	
	
// implented for AStart alogorithm
	List<ExampleNode> path=path(50, 50, 40, 40);// location of customer and driver x=50, y=50; x=40,y=40;
	int amount=cost(path);
	boolean payment=payment(amount, "CreditCard");
	String custFeedback=FeedBack("Customer");
	String driverFeedback=FeedBack("Driver");
	int custrating=rating("Customer");
	int driverRating=rating("Driver");	
	boolean transaction=saveInfo();
	// we will get all these data asynchronously but I am considering here synchronous
	
	private boolean saveInfo() {
		InfDao dao=new InfoDao();
		int pk=dao.savePath(path);
		dao.cost(amount, pk);
		dao.custFeedback(custFeedback, pk);
		dao.CustomeRating(custrating, pk);
		dao.driveRating(driverRating, pk);
		dao.drvierFeedback(driverFeedback, pk);
		return true;
		// TODO Auto-generated method stub

	}
	 
	// save all remaining info based on primary key;

	// Send noitification to the user 
	
	private void notification() {
		// send mail to the user having all the information of the transaction.

	}
	
	
	
	
	
	

	private String FeedBack(String entity) {
		Feedback fd=null;
		String f=null;
		if(entity.equals("Customer"))
		{
			fd=new CustomerFeedback();
			f= fd.Feedback();
		}
		if(entity.equals("Driver"))
		{
			fd=new DriverFeedback();
			f= fd.Feedback();
		}
            return f;
	}
	
	private int rating(String entity)
	{
		Feedback fd=null;
		int  f=0;
		if(entity.equals("Customer"))
		{
			fd=new CustomerFeedback();
			f= fd.rating();
		}
		if(entity.equals("Driver"))
		{
			fd=new DriverFeedback();
			f= fd.rating();
		}
            return f;
	}
	
	
	public boolean payment(int cost,String type)
	{
		Payment pmt=null;
		
		if (type.equals("CreditCard")) {
			pmt = new CreditCard();
			pmt.payment(cost);
			return true;
		}
		if (type.equals("PayTm")) {
			pmt = new CreditCard();
			pmt.payment(cost);
			return true;
		}
		return false;
	}
	
	
	
	 private int cost(List<ExampleNode> path) {
		 int cost=0;
	        // TODO currently, this is done by going through the whole openList!
		 Iterator it=path.iterator();
		 while (it.hasNext()) {
			ExampleNode type = (ExampleNode) it.next();
			cost+=type.getfCosts();
				
		}
	       
	        return cost;
	    }
	 
	 
	
	
	 public static List<ExampleNode> path(int custLocX, int custLocY, int drivLocX,int drivLocY) {
	        Map<ExampleNode> myMap = new Map<ExampleNode>( custLocX, custLocY, new ExampleFactory());
	        List<ExampleNode> path = myMap.findPath(0, 0, drivLocX, drivLocX);

	        return path;
	    }
	
	/** EMAN  monitoring */
	public static boolean testDFI() {
	    
		 //changing for EMAN monitoring , only DB monitoring is used 08 Dec 2016
	    //  if (testDBConnection() && testTIMS() && testTIPS()) 

	            return testDBConnection();
	     
	       }
	
	private static boolean testDBConnection(){
     try{
    	 // getting Database Connection
    	 Connection conn =null;
         
         conn.createStatement().executeQuery("select 1 from dual");
         conn.close();
       
        }catch (Exception ex){
            System.out.println("%%%%%%%%%%%% ***************** EMAN exception" +ex);
                return false;
        }
        return true;
    }

}
