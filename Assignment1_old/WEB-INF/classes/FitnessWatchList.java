import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FitnessWatchList")

public class FitnessWatchList extends HttpServlet {

	/* Trending Page Displays all the Fitness watch and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Fitness watch type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.fws);
			name = "";
		} 
		else 
		{
			if (CategoryName.equals("apple"))
			{
				for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("apple"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Apple Watch";
			} 
			else if(CategoryName.equals("garmin")) 
			{	
				for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Garmin"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Garmin";
			} 
			
			else if (CategoryName.equals("samsung")) 
			{
				for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Samsung";
			}
			else if (CategoryName.equals("fitbit"))
			{
				for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Fitbit"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Fitbit";
			} 
			else if (CategoryName.equals("mi")) 
			{
				for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fws.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("MI"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "MI";
			}
	    }

		/* Header, Left Navigation Bar are Printed.

		All the FitnessWatch and its information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Fitness Watch</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		
		for (Map.Entry<String, FitnessWatch> entry : hm.entrySet()) {
			FitnessWatch fw = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + fw.getName() + "</h3>");
			pw.print("<strong>" + fw.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/fitnesswatch/"
					+ fw.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='fws'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='fws'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+fw.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='fws'>"+
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
