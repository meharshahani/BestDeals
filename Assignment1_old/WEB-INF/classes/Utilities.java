import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				String usertype = session.getAttribute("usertype").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);

				if(session.getAttribute("usertype").equals("manager"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>"
						+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a></li>"
						+"<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a></li>"
						+"<li><a href='DataVisualization'><span class='glyphicon'>Trending</span></a></li>"
						+"<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>"
						+"<li><a href='InventoryReport'><span class='glyphicon'>Inventory</span></a></li>"
						+"<li><a href='SalesReport'><span class='glyphicon'>Sales Report</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				
				else if(session.getAttribute("usertype").equals("retailer")){
					result = result + "<li><a href='Registration'><span class='glyphicon'>Create Customer</span></a></li>"
						+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				else
				{
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			    }
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				hm=MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
		int size=0;
			try
			{
				orderPayments =MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			// int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 // size=size + 1;
			size=entry.getKey();		 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String,TV> alltvs = new HashMap<String,TV> ();
		HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();
		HashMap<String,Headphone> allheadphones = new HashMap<String,Headphone> ();
		HashMap<String,VoiceAssistant> allvas=new HashMap<String,VoiceAssistant>();
		HashMap<String,SoundSystem> allsss=new HashMap<String,SoundSystem>();
		HashMap<String,Tablet> alltablets=new HashMap<String,Tablet>();
		HashMap<String,FitnessWatch> allfws=new HashMap<String,FitnessWatch>();
		HashMap<String,SmartWatch> allsws=new HashMap<String,SmartWatch>();
		HashMap<String,WirelessPlan> allwps=new HashMap<String,WirelessPlan>();


		if(type.equals("tvs")){
			TV tv;
			tv = SaxParserDataStore.tvs.get(name);
			OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(), tv.getImage(), tv.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptop laptop = null;
			laptop = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("headphones")){
			Headphone headphone = null;
			headphone = SaxParserDataStore.headphones.get(name);
			OrderItem orderitem = new OrderItem(headphone.getName(), headphone.getPrice(), headphone.getImage(), headphone.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("vas")){
			VoiceAssistant va = null;
			va = SaxParserDataStore.vas.get(name);
			OrderItem orderitem = new OrderItem(va.getName(), va.getPrice(), va.getImage(), va.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("sss")){
			SoundSystem ss = null;
			ss = SaxParserDataStore.sss.get(name);
			OrderItem orderitem = new OrderItem(ss.getName(), ss.getPrice(), ss.getImage(), ss.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("fws")){
			FitnessWatch fw = null;
			fw = SaxParserDataStore.fws.get(name);
			OrderItem orderitem = new OrderItem(fw.getName(), fw.getPrice(), fw.getImage(), fw.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("sws")){
			SmartWatch sw = null;
			sw = SaxParserDataStore.sws.get(name);
			OrderItem orderitem = new OrderItem(sw.getName(), sw.getPrice(), sw.getImage(), sw.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("tablets")){
			Tablet tablet = null;
			tablet = SaxParserDataStore.tablets.get(name);
			OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("wps")){
			WirelessPlan wp = null;
			wp = SaxParserDataStore.wps.get(name);
			OrderItem orderitem = new OrderItem(wp.getName(), wp.getPrice(), wp.getImage(), wp.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo,String date_place, String customer){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				orderPayments=MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,date_place);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file
			try
		{	if(session.getAttribute("usertype").equals("retailer"))
			{
				MySqlDataStoreUtilities.insertOrder(orderId,customer,orderName,orderPrice,userAddress,creditCardNo,date_place);
			}else
				
				{MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,date_place);}
		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
	}


	public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String reatilerpin,String price,String city)
     {
	String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}

	
	/* get Functions returns the Hashmap with all particular product in the store.*/

	public HashMap<String, TV> getTV(){
			HashMap<String, TV> hm = new HashMap<String, TV>();
			hm.putAll(SaxParserDataStore.tvs);
			return hm;
	}

	public HashMap<String, Laptop> getLaptop(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}

	public HashMap<String, Headphone> getHeadphone(){
			HashMap<String, Headphone> hm = new HashMap<String, Headphone>();
			hm.putAll(SaxParserDataStore.headphones);
			return hm;
	}

	public HashMap<String, VoiceAssistant> getVoiceAssistant(){
			HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
			hm.putAll(SaxParserDataStore.vas);
			return hm;
	}

	public HashMap<String, SoundSystem> getSoundSystem(){
			HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
			hm.putAll(SaxParserDataStore.sss);
			return hm;
	}

	public HashMap<String, FitnessWatch> getFitnessWatch(){
			HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();
			hm.putAll(SaxParserDataStore.fws);
			return hm;
	}
	
	public HashMap<String, SmartWatch> getSmartWatch(){
			HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
			hm.putAll(SaxParserDataStore.sws);
			return hm;
	}
	

	public HashMap<String, Tablet> getTablet(){
			HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
			hm.putAll(SaxParserDataStore.tablets);
			return hm;
	}

	public HashMap<String, WirelessPlan> getWirelessPlan(){
			HashMap<String, WirelessPlan> hm = new HashMap<String, WirelessPlan>();
			hm.putAll(SaxParserDataStore.wps);
			return hm;
	}


	/* getProducts Functions returns the Arraylist of products in the store.*/

	public ArrayList<String> getProductsTV(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, TV> entry : getTV().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsLaptop(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptop().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsHeadphone(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Headphone> entry : getHeadphone().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsVoiceAssistant(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistant().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsSoundSystem(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SoundSystem> entry : getSoundSystem().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsFitnessWatch(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, FitnessWatch> entry : getFitnessWatch().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsSmartWatch(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartWatch> entry : getSmartWatch().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsTablet(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Tablet> entry : getTablet().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsWirelessPlan(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, WirelessPlan> entry : getWirelessPlan().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	 public HashMap<String,String> readOutputFile(){
String csvFile = "C:/test3/output.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
HashMap<String,String> prodRecmMap = new HashMap<String,String>();
try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }

} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
}

return prodRecmMap;
}

}


