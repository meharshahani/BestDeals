import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","ashu8901");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try
	{
		
		
		getConnection();
		
		
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		int quantity = 15;
				
		
		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,prodOnSale,manuRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
		{   
			TV tv = entry.getValue();
			String name = "tvs";
			System.out.println("hello");
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,tv.getId());
			pst.setString(3,tv.getName());
			pst.setDouble(4,tv.getPrice());
			pst.setString(5,tv.getImage());
			pst.setString(6,tv.getRetailer());
			pst.setString(7,tv.getCondition());
			pst.setDouble(8,tv.getDiscount());
			pst.setInt(9,25);
			pst.setString(10,"yes");
			pst.setString(11,"no");
			pst.execute();
			
			//System.out.print(tv.getId() + tv.getName() + tv.getPrice() + tv.getImage() + tv.getRetailer() + tv.getCondition() + tv.getDiscount());
			
			// try{
			// //HashMap<String,String> acc = tv.getAccessories();
			// String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			// "VALUES (?,?);";
			// for(Map.Entry<String,String> accentry : acc.entrySet())
			// {
			// 	PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
			// 	pstacc.setString(1,tv.getId());
			// 	pstacc.setString(2,accentry.getValue());
			// 	pstacc.executeUpdate();
			// }
			// }catch(Exception et){
			// 	et.printStackTrace();
			// }
		}

		for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
		{   
			String name = "laptops";
	        Laptop laptop = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,laptop.getId());
			pst.setString(3,laptop.getName());
			pst.setDouble(4,laptop.getPrice());
			pst.setString(5,laptop.getImage());
			pst.setString(6,laptop.getRetailer());
			pst.setString(7,laptop.getCondition());
			pst.setDouble(8,laptop.getDiscount());
			pst.setInt(9,10);
			pst.setString(10,"yes");
			pst.setString(11,"no");
			pst.executeUpdate();

		}
		
		for(Map.Entry<String,Headphone> entry : SaxParserDataStore.headphones.entrySet())
		{   
			String name = "headphones";
	        Headphone headphone = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,headphone.getId());
			pst.setString(3,headphone.getName());
			pst.setDouble(4,headphone.getPrice());
			pst.setString(5,headphone.getImage());
			pst.setString(6,headphone.getRetailer());
			pst.setString(7,headphone.getCondition());
			pst.setDouble(8,headphone.getDiscount());
			pst.setInt(9,40);
			pst.setString(10,"no");
			pst.setString(11,"yes");
			pst.executeUpdate();
		}

		for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.vas.entrySet())
		{   
			String name = "voiceassistants";
	        VoiceAssistant va = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,va.getId());
			pst.setString(3,va.getName());
			pst.setDouble(4,va.getPrice());
			pst.setString(5,va.getImage());
			pst.setString(6,va.getRetailer());
			pst.setString(7,va.getCondition());
			pst.setDouble(8,va.getDiscount());
			pst.setInt(9,9);
			pst.setString(10,"yes");
			pst.setString(11,"no");
			pst.executeUpdate();
		}

		for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.sss.entrySet())
		{   
			String name = "soundsystem";
	        SoundSystem ss = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,ss.getId());
			pst.setString(3,ss.getName());
			pst.setDouble(4,ss.getPrice());
			pst.setString(5,ss.getImage());
			pst.setString(6,ss.getRetailer());
			pst.setString(7,ss.getCondition());
			pst.setDouble(8,ss.getDiscount());
			pst.setInt(9,15);
			pst.setString(10,"no");
			pst.setString(11,"yes");
			pst.executeUpdate();
		}

		for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fws.entrySet())
		{   
			String name = "fitnesswatch";
	        FitnessWatch fw = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,fw.getId());
			pst.setString(3,fw.getName());
			pst.setDouble(4,fw.getPrice());
			pst.setString(5,fw.getImage());
			pst.setString(6,fw.getRetailer());
			pst.setString(7,fw.getCondition());
			pst.setDouble(8,fw.getDiscount());
			pst.setInt(9,quantity);
			pst.setString(10,"no");
			pst.setString(11,"yes");
			pst.executeUpdate();
		}

		for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.sws.entrySet())
		{   
			String name = "smartwatch";
	        SmartWatch sw = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,sw.getId());
			pst.setString(3,sw.getName());
			pst.setDouble(4,sw.getPrice());
			pst.setString(5,sw.getImage());
			pst.setString(6,sw.getRetailer());
			pst.setString(7,sw.getCondition());
			pst.setDouble(8,sw.getDiscount());
			pst.setInt(9,12);
			pst.setString(10,"yes");
			pst.setString(11,"no");
			pst.executeUpdate();
		}

		for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
		{   
			String name = "tablet";
	        Tablet tablet = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,tablet.getId());
			pst.setString(3,tablet.getName());
			pst.setDouble(4,tablet.getPrice());
			pst.setString(5,tablet.getImage());
			pst.setString(6,tablet.getRetailer());
			pst.setString(7,tablet.getCondition());
			pst.setDouble(8,tablet.getDiscount());
			pst.setInt(9,30);
			pst.setString(10,"yes");
			pst.setString(11,"no");
			pst.executeUpdate();
		}

		for(Map.Entry<String,WirelessPlan> entry : SaxParserDataStore.wps.entrySet())
		{   
			String name = "wirelessplans";
	        WirelessPlan wp = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,wp.getId());
			pst.setString(3,wp.getName());
			pst.setDouble(4,wp.getPrice());
			pst.setString(5,wp.getImage());
			pst.setString(6,wp.getRetailer());
			pst.setString(7,wp.getCondition());
			pst.setDouble(8,wp.getDiscount());
			pst.setInt(9,20);
			pst.setString(10,"no");
			pst.setString(11,"no");
			pst.executeUpdate();
		}
	}
	catch(Exception e)
	{
  		e.printStackTrace();
	}
} 



// public static HashMap<String,TV> getTvs()
// {	
// 	HashMap<String,TV> hm=new HashMap<String,TV>();
// 	try 
// 	{
// 		getConnection();
		
// 		String selectTv="select * from  Productdetails where ProductType=?";
// 		PreparedStatement pst = conn.prepareStatement(selectTv);
// 		pst.setString(1,"tvs");
// 		ResultSet rs = pst.executeQuery();
	
// 		while(rs.next())
// 		{	TV tv = new TV(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
// 				hm.put(rs.getString("Id"), tv);
// 				tv.setId(rs.getString("Id"));
				
// 				try
// 				{
// 				String selectaccessory = "Select * from Product_accessories where productName=?";
// 				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
// 				pstacc.setString(1,rs.getString("Id"));
// 				ResultSet rsacc = pstacc.executeQuery();
				
// 				HashMap<String,String> acchashmap = new HashMap<String,String>();
// 				while(rsacc.next())
// 				{    
// 					if (rsacc.getString("accessoriesName") != null){
					
// 					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
// 					 tv.setAccessories(acchashmap);
// 					}
					 
// 				}					
// 				}catch(Exception e)
// 				{
// 						e.printStackTrace();
// 				}
// 		}
// 	}
// 	catch(Exception e)
// 	{
// 	}
// 	return hm;			
// }

public static HashMap<String,TV> getTvs()
{	
	HashMap<String,TV> hm=new HashMap<String,TV>();
	try 
	{
		getConnection();
		
		String selectTv="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTv);
		pst.setString(1,"tvs");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			TV tv = new TV(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tv);
				tv.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static HashMap<String,Laptop> getLaptops()
{	
	HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
	try 
	{
		getConnection();
		
		String selectLaptop="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectLaptop);
		pst.setString(1,"laptops");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			Laptop laptop = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), laptop);
				laptop.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Headphone> getHeadphones()
{	
	HashMap<String,Headphone> hm=new HashMap<String,Headphone>();
	try 
	{
		getConnection();
		
		String selectHeadphone="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectHeadphone);
		pst.setString(1,"headphones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Headphone headphone = new Headphone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), headphone);
				headphone.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,VoiceAssistant> getVoiceAssistants()
{	
	HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
	try 
	{
		getConnection();
		
		String selectVoiceAssistant="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
		pst.setString(1,"voiceassistants");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	VoiceAssistant va = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), va);
				va.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,SoundSystem> getSoundSystems()
{	
	HashMap<String,SoundSystem> hm=new HashMap<String,SoundSystem>();
	try 
	{
		getConnection();
		
		String selectSoundSystem="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectSoundSystem);
		pst.setString(1,"soundsystem");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	SoundSystem ss = new SoundSystem(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), ss);
				ss.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,FitnessWatch> getFitnessWatch()
{	
	HashMap<String,FitnessWatch> hm=new HashMap<String,FitnessWatch>();
	try 
	{
		getConnection();
		
		String selectFitnessWatch="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
		pst.setString(1,"fitnesswatch");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	FitnessWatch fw = new FitnessWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), fw);
				fw.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,SmartWatch> getSmartWatch()
{	
	HashMap<String,SmartWatch> hm=new HashMap<String,SmartWatch>();
	try 
	{
		getConnection();
		
		String selectSmartWatch="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
		pst.setString(1,"smartwatch");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	SmartWatch sw = new SmartWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), sw);
				sw.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Tablet> getTablets()
{	
	HashMap<String,Tablet> hm=new HashMap<String,Tablet>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"tablets");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tablet tab = new Tablet(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tab);
				tab.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,WirelessPlan> getWirelessPlans()
{	
	HashMap<String,WirelessPlan> hm=new HashMap<String,WirelessPlan>();
	try 
	{
		getConnection();
		
		String selectwp="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectwp);
		pst.setString(1,"wirelessplans");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	WirelessPlan wp = new WirelessPlan(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), wp);
				wp.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

// public static HashMap<String,Accessory> getAccessories()
// {	
// 	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
// 	try 
// 	{
// 		getConnection();
		
// 		String selectAcc="select * from  Productdetails where ProductType=?";
// 		PreparedStatement pst = conn.prepareStatement(selectAcc);
// 		pst.setString(1,"accessories");
// 		ResultSet rs = pst.executeQuery();
	
// 		while(rs.next())
// 		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
// 				hm.put(rs.getString("Id"), acc);
// 				acc.setId(rs.getString("Id"));

// 		}
// 	}
// 	catch(Exception e)
// 	{
// 	}
// 	return hm;			
// }






public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
		"VALUES (?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Error while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setString(7,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteproducts(String productId)
{   
	String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Product cannot be deleted";
	}
	return msg;
}

public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,String date_place)
{
	try
	{
	
		getConnection();
		
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo,date_place) "
		+ "VALUES (?,?,?,?,?,?,?);";	

		System.out.print(orderId+ userName+ orderName+ orderPrice + userAddress + creditCardNo + date_place);
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.setString(7,date_place);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getString("date_place"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

 public HashMap<String,Product> getProductCatalog(){
            Product prod;
            HashMap<String,Product> productList = new HashMap<String,Product>();
            try{
                getConnection();
                
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery("select * from exampledatabase.productdetails");

                while(rs.next()){
                    prod = new Product();
                    
                    prod.setName(rs.getString("productName"));
                    prod.setPrice(rs.getDouble("productPrice"));
                    prod.setId(rs.getString("id"));
					prod.setQuantity(rs.getInt("quantity"));
					prod.setProdOnSale(rs.getString("prodOnSale"));
					prod.setManuRebate(rs.getString("manuRebate"));
					
                    productList.put(prod.getId(),prod);
                }
                rs.close();
                stmt.close();
            }catch(SQLException exception){
                exception.printStackTrace();
            }
            

            return productList;
       }

	    public ArrayList<Product> getProductSalesStat(){
						 Product prod;
						 ArrayList<Product> prodSoldList = new ArrayList<Product>();
						 try{
								 getConnection();
								
								 Statement stmt=conn.createStatement();
								 ResultSet rs=stmt.executeQuery("select orderName,orderPrice,count(orderName) as items_sold,(orderPrice * count(orderName)) as total_sales from exampledatabase.customerorders group by orderName");
								 while(rs.next()){
										 prod = new Product();
										 prod.setName(rs.getString("orderName"));
										 prod.setPrice(rs.getDouble("orderPrice"));                                    
										 prod.setItems_Sold(rs.getInt("items_sold"));
										 prod.setTotal_Sales(rs.getDouble("total_sales"));
										 prodSoldList.add(prod);
								 }
								 rs.close();
								 stmt.close();
						 }catch(SQLException exception){
								 exception.printStackTrace();
						 }
						 
						 return prodSoldList;
			 }


			 public ArrayList<Product> getDailySalesTransaction(){
						 Product prod;
						 ArrayList<Product> salesTransList = new ArrayList<Product>();
						 try{
								 getConnection();
								 
								 Statement stmt=conn.createStatement();
								 ResultSet rs=stmt.executeQuery("select date_place,sum(orderPrice) as total_sales from exampledatabase.customerorders group by date_place");
								 while(rs.next()){
										 prod = new Product();
										 prod.setDate_Place(rs.getString("date_place"));
										 prod.setTotal_Sales(rs.getDouble("total_sales"));
										 salesTransList.add(prod);
								 }
								 rs.close();
								 stmt.close();
						 }catch(SQLException exception){
								 exception.printStackTrace();
						 }
						 
						 return salesTransList;
			 }

	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}

	
}		