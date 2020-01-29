import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    TV tv;
    Laptop laptop;
    Headphone headphone;
    VoiceAssistant va;
    SoundSystem ss;
    FitnessWatch fw;
    SmartWatch sw;
    Tablet tablet;
	WirelessPlan wp;
	
	String ProductType;
	String Id;
	String productName;
	Double productPrice;
	String productImage;
	String productManufacturer;
	String productCondition;
	Double productDiscount;
	String manufacturerRebate;
	String quantity = "10"; 

    
    static HashMap<String,TV> tvs;
    static HashMap<String,Laptop> laptops;
	static HashMap<String,Headphone> headphones;
	static HashMap<String,VoiceAssistant> vas;
	static HashMap<String,SoundSystem> sss;
	static HashMap<String,FitnessWatch> fws;
	static HashMap<String,SmartWatch> sws;
    static HashMap<String,Tablet> tablets;
    static HashMap<String,WirelessPlan> wps;
    
    String tvXmlFileName;

    String elementValueRead;
	String currentElement="";

    public SaxParserDataStore()
	{
	}

	public SaxParserDataStore(String tvXmlFileName) 
	{
    	this.tvXmlFileName = tvXmlFileName;

    	tvs = new HashMap<String, TV>();
		laptops=new  HashMap<String, Laptop>();
		headphones=new  HashMap<String, Headphone>();
		vas=new  HashMap<String, VoiceAssistant>();
		sss=new  HashMap<String, SoundSystem>();
		fws=new  HashMap<String, FitnessWatch>();
		sws=new  HashMap<String, SmartWatch>();
		tablets=new HashMap<String, Tablet>();
		wps=new HashMap<String, WirelessPlan>();

		parseDocument();
    }

   //parse the xml using sax parser to get the data
 //    private void parseDocument() 
	// {
 //        SAXParserFactory factory = SAXParserFactory.newInstance();
        
 //        try 
	// 	{
	//     SAXParser parser = factory.newSAXParser();
	//     parser.parse(tvXmlFileName, this);
 //        } catch (ParserConfigurationException e) {
 //            System.out.println("ParserConfig error");
 //        } catch (SAXException e) {
 //            System.out.println("SAXException : xml not well formed");
 //        } catch (IOException e) {
 //            System.out.println("IO error");
 //        }
	// }
	private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(tvXmlFileName, this);
		
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("tv")) 
		{
			currentElement="tv";
			tv = new TV();
			tv.setId(attributes.getValue("id"));
			ProductType = "TV";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("laptop")) 
		{
			currentElement="laptop";
			laptop = new Laptop();
			laptop.setId(attributes.getValue("id"));
			ProductType = "laptop";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("headphone")) 
		{
			currentElement="headphone";
			headphone = new Headphone();
			headphone.setId(attributes.getValue("id"));
			ProductType = "headphone";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("va")) 
		{
			currentElement="va";
			va = new VoiceAssistant();
			va.setId(attributes.getValue("id"));
			ProductType = "va";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("ss")) 
		{
			currentElement="ss";
			ss = new SoundSystem();
			ss.setId(attributes.getValue("id"));
			ProductType = "ss";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("fw")) 
		{
			currentElement="fw";
			fw = new FitnessWatch();
			fw.setId(attributes.getValue("id"));
			ProductType = "fw";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("sw")) 
		{
			currentElement="sw";
			sw = new SmartWatch();
			sw.setId(attributes.getValue("id"));
			ProductType = "sw";
			Id = attributes.getValue("id");
		}

        if (elementName.equalsIgnoreCase("tablet"))
		{
			currentElement="tablet";
			tablet = new Tablet();
			tablet.setId(attributes.getValue("id"));
			ProductType = "tablet";
			Id = attributes.getValue("id");
        }
        if (elementName.equalsIgnoreCase("wp")) 
		{
			currentElement="wp";
			wp = new WirelessPlan();
			wp.setId(attributes.getValue("id"));
			ProductType = "wp";
			Id = attributes.getValue("id");
		}
        
        


    }
	// when xml end element is parsed store the data into respective hashmap for ,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException 
    {
 
        if (element.equals("tv")) 
        {
			tvs.put(tv.getId(),tv);
			return;
        }
        if (element.equals("laptop")) 
        {
			laptops.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("headphone")) 
        {
			headphones.put(headphone.getId(),headphone);
			return;
        }
        if (element.equals("va")) 
        {
			vas.put(va.getId(),va);
			return;
        }
        if (element.equals("ss")) 
        {
			sss.put(ss.getId(),ss);
			return;
        }
        if (element.equals("fw")) 
        {
			fws.put(fw.getId(),fw);
			return;
        }
        if (element.equals("sw")) 
        {
			sws.put(sw.getId(),sw);
			return;
        }
        if (element.equals("tablet")) 
        {	
			tablets.put(tablet.getId(),tablet);
			return;
        }
        if (element.equals("wp")) 
        {	  
			wps.put(wp.getId(),wp);
			return;
        }
        
        if (element.equalsIgnoreCase("image")) 
        {
		    if(currentElement.equals("tv")){
				tv.setImage(elementValueRead);
				productImage = elementValueRead;
			}
				
			if(currentElement.equals("laptop"))
			{
				laptop.setImage(elementValueRead);
				productImage = elementValueRead;

			}
			if(currentElement.equals("headphone"))
			{
				headphone.setImage(elementValueRead);
				productImage = elementValueRead;

			}
			if(currentElement.equals("va"))
			{
				va.setImage(elementValueRead);
					productImage = elementValueRead;
			}
			if(currentElement.equals("ss"))
				ss.setImage(elementValueRead);
        	if(currentElement.equals("fw"))
				fw.setImage(elementValueRead);
			if(currentElement.equals("sw"))
				sw.setImage(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setImage(elementValueRead);
            if(currentElement.equals("wp"))
				wp.setImage(elementValueRead);          
			return;
        }


		if (element.equalsIgnoreCase("condition"))
		 {
            if(currentElement.equals("tv")){
				tv.setCondition(elementValueRead);
				productCondition = elementValueRead;
			}
        	if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setCondition(elementValueRead);
			if(currentElement.equals("va"))
				va.setCondition(elementValueRead);
			if(currentElement.equals("ss"))
				ss.setCondition(elementValueRead);
        	if(currentElement.equals("fw"))
				fw.setCondition(elementValueRead);
			if(currentElement.equals("sw"))
				sw.setCondition(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setCondition(elementValueRead);
			if(currentElement.equals("wp"))
				wp.setCondition(elementValueRead);
                  
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer"))
		 {
			if(currentElement.equals("tv"))
			{
				tv.setRetailer(elementValueRead);
				productManufacturer = elementValueRead;
			}
        	if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setRetailer(elementValueRead);
			if(currentElement.equals("va"))
				va.setRetailer(elementValueRead);
			if(currentElement.equals("ss"))
				ss.setRetailer(elementValueRead);
        	if(currentElement.equals("fw"))
				fw.setRetailer(elementValueRead);
			if(currentElement.equals("sw"))
				sw.setRetailer(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setRetailer(elementValueRead);
            if(currentElement.equals("wp"))
				wp.setRetailer(elementValueRead);

			return;
		}

        if (element.equalsIgnoreCase("name")) 
        {
			if(currentElement.equals("tv"))
			{
				tv.setName(elementValueRead);
				productName = elementValueRead;
			}
        	if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setName(elementValueRead);
			if(currentElement.equals("va"))
				va.setName(elementValueRead);
			if(currentElement.equals("ss"))
				ss.setName(elementValueRead);
        	if(currentElement.equals("fw"))
				fw.setName(elementValueRead);
			if(currentElement.equals("sw"))
				sw.setName(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setName(elementValueRead);
            if(currentElement.equals("wp"))
				wp.setName(elementValueRead); 

			return;
	    }
	
        if(element.equalsIgnoreCase("price"))
        {
			if(currentElement.equals("tv"))
			{
				tv.setPrice(Double.parseDouble(elementValueRead));
				productPrice = Double.parseDouble(elementValueRead);
			}
        	if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("va"))
				va.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("ss"))
				ss.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fw"))
				fw.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("sw"))
				sw.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tablet"))
				tablet.setPrice(Double.parseDouble(elementValueRead)); 
            if(currentElement.equals("wp"))
				wp.setPrice(Double.parseDouble(elementValueRead));          
			return;
		}

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("tv")){
				tv.setDiscount(Double.parseDouble(elementValueRead));
				productDiscount = Double.parseDouble(elementValueRead);
			}
				
			if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("va"))
				va.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("ss"))
				ss.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fw"))
				fw.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("sw"))
				sw.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wp"))
				wp.setDiscount(Double.parseDouble(elementValueRead));
                      
			return;
		}
		
		// MySqlDataStoreUtilities.Insertproducts(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity);

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Assignment1_old\\ProductCatalog.xml");
    } 
}
