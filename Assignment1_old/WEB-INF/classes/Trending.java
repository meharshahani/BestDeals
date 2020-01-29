// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.HashMap;
// import java.util.Map;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// @WebServlet("/Trending")

// public class Trending extends HttpServlet {

// 	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

// 	protected void doGet(HttpServletRequest request,
// 		HttpServletResponse response) throws ServletException, IOException {
// 		response.setContentType("text/html");
// 		PrintWriter pw = response.getWriter();

// 		/* Checks the Consoles type whether it is microsft or sony or nintendo then add products to hashmap*/

// 		String name = "Trending";
// 		String CategoryName = request.getParameter("maker");
// 		HashMap<String, TV> hm = new HashMap<String, TV>();
// 		HashMap<String, Laptop> hml = new HashMap<String, Laptop>();
// 		HashMap<String, SoundSystem> hms = new HashMap<String, SoundSystem>();
// 		HashMap<String, SmartWatch> hmsw = new HashMap<String, SmartWatch>();
// 		HashMap<String, Tablet> hmt = new HashMap<String, Tablet>();


// 		if(CategoryName==null)
// 		{
// 			hm.putAll(SaxParserDataStore.tvs);
// 		}
// 		else
// 		{
// 			if(CategoryName.equals("samsung"))
// 			{
// 				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Samsung")) 
// 				  {
// 					 hm.put(entry.getValue().getId(),entry.getValue());
// 				  }
// 				}
// 				for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
// 				{
// 				if(entry.getValue().getRetailer().equals("Apple"))
// 				 {
// 					 hml.put(entry.getValue().getId(),entry.getValue());
// 				 }
// 				}
// 				for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.sss.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Vizio"))
// 				  {
// 					 hms.put(entry.getValue().getId(),entry.getValue());
// 				  }
// 				}
// 				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Michael Kors"))
// 				  {
// 					 hmsw.put(entry.getValue().getId(),entry.getValue());
// 				  }
// 				}
// 				for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Amazon Kindle"))
// 				 {
// 					 hmt.put(entry.getValue().getId(),entry.getValue());
// 				 }
// 				}	
// 			}
// 			else if(CategoryName.equals("sony"))
// 			{
// 				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Sony"))
// 				  {
// 					 hm.put(entry.getValue().getId(),entry.getValue());
// 				  }
// 				}
// 			}
// 			else if(CategoryName.equals("lg"))
// 			{
// 				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
// 				{ 
// 			      if(entry.getValue().getRetailer().equals("LG"))
// 				  {
// 					 hm.put(entry.getValue().getId(),entry.getValue());
// 				  }
// 				}
// 				for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
// 				{
// 				if(entry.getValue().getRetailer().equals("Lenovo"))
// 				 {
// 					 hml.put(entry.getValue().getId(),entry.getValue());
// 				 }
// 				}
// 				for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.sss.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Vizio"))
// 				  {
// 					 hms.put(entry.getValue().getId(),entry.getValue());
// 				  }
// 				}
// 				for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.sss.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Bose"))
// 				 {
// 					 hms.put(entry.getValue().getId(),entry.getValue());
// 				 }
// 				}
// 				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
// 				{
// 				  if(entry.getValue().getRetailer().equals("Movado"))
// 				 {
// 					 hmsw.put(entry.getValue().getId(),entry.getValue());
// 				 }
// 				}
// 			}
// 		}
		

// 		/* Header, Left Navigation Bar are Printed.

// 		All the consoles and Console information are dispalyed in the Content Section

// 		and then Footer is Printed*/

// 		Utilities utility = new Utilities(request, pw);
// 		utility.printHtml("Header.html");
// 		utility.printHtml("LeftNavigationBar.html");
// 		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
// 		pw.print("<a style='font-size: 24px;'>"+name+" Products</a>");
// 		pw.print("</h2><div class='entry'><table id='bestseller'>");
// 		int i = 1; int size= hm.size();
// 		for(Map.Entry<String, TV> entry : hm.entrySet()){
// 			TV tv = entry.getValue();
// 			if(i%3==1) pw.print("<tr>");
// 			pw.print("<td><div id='shop_item'>");
// 			pw.print("<h3>"+tv.getName()+"</h3>");
// 			pw.print("<strong>$"+tv.getPrice()+"</strong><ul>");
// 			pw.print("<li id='item'><img src='images/tv/"+tv.getImage()+"' alt='' /></li>");
			
// 			pw.print("</ul></div></td>");
			
// 			if(i%3==0 || i == size) pw.print("</tr>");
// 			i++;
// 		}
// 		for(Map.Entry<String, Laptop> entry : hml.entrySet()){
// 			Laptop laptop = entry.getValue();
// 			if(i%3==1) pw.print("<tr>");
// 			pw.print("<td><div id='shop_item'>");
// 			pw.print("<h3>"+laptop.getName()+"</h3>");
// 			pw.print("<strong>$"+laptop.getPrice()+"</strong><ul>");
// 			pw.print("<li id='item'><img src='images/laptops/"+laptop.getImage()+"' alt='' /></li>");
			
// 			pw.print("</ul></div></td>");
			
// 			if(i%3==0 || i == size) pw.print("</tr>");
// 			i++;
// 		}	
// 		for(Map.Entry<String, SoundSystem> entry : hms.entrySet()){
// 			SoundSystem ss = entry.getValue();
// 			if(i%3==1) pw.print("<tr>");
// 			pw.print("<td><div id='shop_item'>");
// 			pw.print("<h3>"+ss.getName()+"</h3>");
// 			pw.print("<strong>$"+ss.getPrice()+"</strong><ul>");
// 			pw.print("<li id='item'><img src='images/soundSystem/"+ss.getImage()+"' alt='' /></li>");
			
// 			pw.print("</ul></div></td>");
			
// 			if(i%3==0 || i == size) pw.print("</tr>");
// 			i++;
// 		}	
// 		for(Map.Entry<String, SmartWatch> entry : hmsw.entrySet()){
// 			SmartWatch sw = entry.getValue();
// 			if(i%3==1) pw.print("<tr>");
// 			pw.print("<td><div id='shop_item'>");
// 			pw.print("<h3>"+sw.getName()+"</h3>");
// 			pw.print("<strong>$"+sw.getPrice()+"</strong><ul>");
// 			pw.print("<li id='item'><img src='images/smartWatch/"+sw.getImage()+"' alt='' /></li>");
			
// 			pw.print("</ul></div></td>");
			
// 			if(i%3==0 || i == size) pw.print("</tr>");
// 			i++;
// 		}
// 		for(Map.Entry<String, Tablet> entry : hmt.entrySet()){
// 			Tablet tablet = entry.getValue();
// 			if(i%3==1) pw.print("<tr>");
// 			pw.print("<td><div id='shop_item'>");
// 			pw.print("<h3>"+tablet.getName()+"</h3>");
// 			pw.print("<strong>$"+tablet.getPrice()+"</strong><ul>");
// 			pw.print("<li id='item'><img src='images/tablets/"+tablet.getImage()+"' alt='' /></li>");
			
// 			pw.print("</ul></div></td>");
			
// 			if(i%3==0 || i == size) pw.print("</tr>");
// 			i++;
// 		}	
// 		pw.print("</table></div></div></div>");	
// 		utility.printHtml("Footer.html");
// 	}

// 	protected void doPost(HttpServletRequest request,
// 			HttpServletResponse response) throws ServletException, IOException {

// 	}

// }

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
    ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
	ArrayList <Bestrating> bestrated = new ArrayList <Bestrating> ();

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		mostsold = MongoDBDataStoreUtilities.mostsoldProducts();

		mostsoldzip = MongoDBDataStoreUtilities.mostsoldZip();
		bestrated = MongoDBDataStoreUtilities.topProducts();

		

		String name = "Trending";
		

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Best Products</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		Iterator itr2 = bestrated.iterator();
        while(itr2.hasNext()) {
         Bestrating best = (Bestrating)itr2.next();
 		pw.print("<tr>");
		pw.print("<td>");
		pw.print(best.getProductname());
		pw.print("</td>");
		pw.print("<td>");
		pw.print(best.getRating());
		pw.print("</td>");
		pw.print("</tr>");
        }
		pw.print("</table></div></div></div>");	
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Most Sold Products by Zipcode</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		Iterator itr1 = mostsoldzip.iterator();
         while(itr1.hasNext()) {
         Mostsoldzip mostzip = (Mostsoldzip)itr1.next();
 		pw.print("<tr>");
		pw.println("<td border: 1px >");

		pw.println(mostzip.getZipcode());
		pw.println("</td>");
		pw.println("<td border: 1px >");
		pw.println(mostzip.getCount());
		pw.println("</td>");
		pw.println("</tr>");
        }
		pw.print("</table></div></div></div>");	
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Most Sold Products</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		
         Iterator itr = mostsold.iterator();
        while(itr.hasNext()) {
         Mostsold most = (Mostsold)itr.next();
 		pw.println("<tr>");
		pw.println("<td border: 1px >");
		pw.println(most.getProductname());
		pw.println("</td>");
		pw.println("<td border: 1px >");
		pw.println(most.getCount());
		pw.println("</td>");
		pw.println("</tr>");
        }
		pw.print("</table></div></div></div>");
		
	//	pw.print("</table></div></div></div>");	
	
		utility.printHtml("Footer.html");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}

