import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartWatchList")

public class SmartWatchList extends HttpServlet {

	/* Trending Page Displays all the SmartWatch and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the SmartWatch type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.sws);
			name = "";
		} 
		else 
		{
			if (CategoryName.equals("mk"))
			{
				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Michael Kors"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Michael Kors";
			} 
			else if(CategoryName.equals("apple")) 
			{	
				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Apple"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Apple";
			} 
			else if (CategoryName.equals("fossil"))
			{
				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Fossil"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Fossil";
			} 
			else if (CategoryName.equals("movado")) 
			{
				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Movado"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Movado";
			}
			else if (CategoryName.equals("skagen")) 
			{
				for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Skagen"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Skagen";
			}
	    }

		/* Header, Left Navigation Bar are Printed.

		All the SmartWatch and its information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Smart Watch</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		
		for (Map.Entry<String, SmartWatch> entry : hm.entrySet()) {
			SmartWatch sw = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + sw.getName() + "</h3>");
			pw.print("<strong>" + sw.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/smartWatch/"+ sw.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sws'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sws'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+sw.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sws'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
