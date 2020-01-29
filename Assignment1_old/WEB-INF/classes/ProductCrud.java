import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",prod = "";
			double productPrice=0.0,productDiscount = 0.0;
			//HashMap<String,Console> allconsoles = new HashMap<String,Console> ();
			HashMap<String,TV> alltvs = new HashMap<String,TV> ();			
			HashMap<String,Tablet> alltablets = new HashMap<String,Tablet> ();
			HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();
			HashMap<String,Headphone> allheadphones = new HashMap<String,Headphone> ();
			HashMap<String,VoiceAssistant> allvas = new HashMap<String,VoiceAssistant> ();
			HashMap<String,SoundSystem> allsss = new HashMap<String,SoundSystem> ();
			HashMap<String,FitnessWatch> allfws = new HashMap<String,FitnessWatch> ();
			HashMap<String,SmartWatch> allsws = new HashMap<String,SmartWatch> ();
			HashMap<String,WirelessPlan> allwps = new HashMap<String,WirelessPlan> ();


			//HashMap<String,Game> allgames = new HashMap<String,Game> ();
			//HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
			if (action.equals("add") || action.equals("update"))
			{	
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 productImage = request.getParameter("productImage");
				 productManufacturer = request.getParameter("productManufacturer");
				 productCondition = request.getParameter("productCondition");
				 productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
				 
			}
			else
			{
				productId   = request.getParameter("productId");
			}	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");

			if(action.equals("add"))
			{
			  if(producttype.equals("tvs"))
			  {
				  alltvs = MySqlDataStoreUtilities.getTvs();
				  if(alltvs.containsKey(productId))
				  {
					  msg = "Product already available";
					  
				  }	  
			  }
			  else if (producttype.equals("tablets"))
			  {
				  alltablets = MySqlDataStoreUtilities.getTablets();
				  if(alltablets.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("laptops"))
			  {
				  alllaptops = MySqlDataStoreUtilities.getLaptops();
				  if(alllaptops.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("headphones"))
			  {
				  allheadphones = MySqlDataStoreUtilities.getHeadphones();
				  if(allheadphones.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if(producttype.equals("voiceassistants"))
			  {
				  allvas = MySqlDataStoreUtilities.getVoiceAssistants();
				  if(allvas.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("soundsystem"))
			  {
				  allsss = MySqlDataStoreUtilities.getSoundSystems();
				  if(allsss.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("fitnesswatch"))
			  {
				  allfws = MySqlDataStoreUtilities.getFitnessWatch();
				  if(allfws.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("smartwatch"))
			  {
				  allsws = MySqlDataStoreUtilities.getSmartWatch();
				  if(allsws.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("wirelessplans"))
			  {
				  allwps = MySqlDataStoreUtilities.getWirelessPlans();
				  if(allwps.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }

			  // else if (producttype.equals("accessories"))
			  // {  
					// if(!request.getParameter("product").isEmpty())
					// 	{
					// 		prod = request.getParameter("product");
					// 		allconsoles = MySqlDataStoreUtilities.getConsoles();
					// 		if(allconsoles.containsKey(prod))
					// 		{
					// 			allaccessories = MySqlDataStoreUtilities.getAccessories();
					// 			if(allaccessories.containsKey(productId)){
					// 				msg = "Product already available";
					// 			}
					// 		}else{
					// 			msg = "The product related to accessories is not available";
					// 		}
						
						
					// 	}else{
					// 		msg = "Please add the prodcut name";
					// 	}
				  
			  // }	
			  if (msg.equals("good"))
			  {  
				  try
				  {
					  msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,prod);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be inserted";
				  }
				  msg = "Product has been successfully added";
			  }					
			}
			else if(action.equals("update"))
			{
				
			  if(producttype.equals("tvs"))
			  {
				  alltvs = MySqlDataStoreUtilities.getTvs();
				  if(!alltvs.containsKey(productId)){
					  msg = "Product not available";
				  }
					  
			  }
			  else if(producttype.equals("laptops"))
			  {
				  alllaptops = MySqlDataStoreUtilities.getLaptops();
				  if(!alllaptops.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if(producttype.equals("headphones"))
			  {
				  allheadphones = MySqlDataStoreUtilities.getHeadphones();
				  if(!allheadphones.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if (producttype.equals("voiceassistants"))
			  {
				  allvas = MySqlDataStoreUtilities.getVoiceAssistants();
				  if(!allvas.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if (producttype.equals("soundsystem"))
			  {
				  allsss = MySqlDataStoreUtilities.getSoundSystems();
				  if(!allsss.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if (producttype.equals("fitnesswatch"))
			  {
				  allfws = MySqlDataStoreUtilities.getFitnessWatch();
				  if(!allfws.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if (producttype.equals("smartwatch"))
			  {
				  allsws = MySqlDataStoreUtilities.getSmartWatch();
				  if(!allsws.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if (producttype.equals("tablets"))
			  {
				  alltablets = MySqlDataStoreUtilities.getTablets();
				  if(!alltablets.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if (producttype.equals("wirelessplans"))
			  {
				  allwps = MySqlDataStoreUtilities.getWirelessPlans();
				  if(!allwps.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }



			 //  else if (producttype.equals("accessories"))
			 //  {
				//   allaccessories = MySqlDataStoreUtilities.getAccessories();
				//   if(!allaccessories.containsKey(productId)){
				// 	  msg = "Product not available";
				// }
			 //  }	

			  if (msg.equals("good"))
			  {		
				
				  try
				  {
					msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be updated";
				  }
				  msg = "Product has been successfully updated";
			  } 
			}
			else if(action.equals("delete"))
			{
				  msg = "bad";

				  alltvs = MySqlDataStoreUtilities.getTvs();
				  if(alltvs.containsKey(productId))
				  {
					  msg = "good";	 
				  }
			  
				  alllaptops = MySqlDataStoreUtilities.getLaptops();
				  if(alllaptops.containsKey(productId)){
					  msg = "good";
				  }
			  
				  allheadphones = MySqlDataStoreUtilities.getHeadphones();
				  if(allheadphones.containsKey(productId)){
					  msg = "good";
				  }

				  allvas = MySqlDataStoreUtilities.getVoiceAssistants();
				  if(allvas.containsKey(productId)){
					  msg = "good";
				  }

				  allsss = MySqlDataStoreUtilities.getSoundSystems();
				  if(allsss.containsKey(productId)){
					  msg = "good";
				  }

				  allfws = MySqlDataStoreUtilities.getFitnessWatch();
				  if(allfws.containsKey(productId)){
					  msg = "good";
				  }

				  allsws = MySqlDataStoreUtilities.getSmartWatch();
				  if(allsws.containsKey(productId)){
					  msg = "good";
				  }

				  alltablets = MySqlDataStoreUtilities.getTablets();
				  if(alltablets.containsKey(productId)){
					  msg = "good";
				  }

				  allwps = MySqlDataStoreUtilities.getWirelessPlans();
				  if(allwps.containsKey(productId)){
					  msg = "good";
				  }
			  
				//   allaccessories = MySqlDataStoreUtilities.getAccessories();
				//   if(allaccessories.containsKey(productId)){
				// 	  msg = "good";
				// }
		       		
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						
						 msg = MySqlDataStoreUtilities.deleteproducts(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Product cannot be deleted";
					  }
					   msg = "Product has been successfully deleted";
				  }else{
					  msg = "Product not available";
				  }
			}	
				
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
			
	}
}