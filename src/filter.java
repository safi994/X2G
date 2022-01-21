import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
 
public class filter 
{
    public static void main(String[] args) throws Exception 
    {
    	
        /************************************************ X2G *************************************************/
        /*
         We need to read the xml files to build the graphs.
         The graph will be represented in 2 files, nodes.csv and edges.csv.
         What are the nodes? Stories - People - 
         What are the edges? 
         
         We have 99999k files
         
         The tool can answer:
         Step1:Filtering
         *wich xml files will be included to make the process
         *the Filter is based on the User-input
         *Like i want all storys that have three lang: DEU, NDS and ENG! || all storys that have  places!
         *maybe we can merge the following step also in Filter??!!
         *
         The tool can also answer:
         * What stories did a specific author tell? Answer through the graph
         * What stories were told in a specific place?
         * 
         * 
         * 
         Let's create an adjancency list:
          List<List<Node>> graph;
          { {1, 2, 3}, // list of index 0
          	{0}, // list of index 1
          	{0}, // list of index 2
          	{0}, // list of index 3
          	
          }
          
          story1----(title)---- 'Ein Bettler wird erschlagen'
          |
          |
          (
          
          ######## Project description ########
          1. Input: (let's go with xpath)
          	* We can input xpath or ask user yes/no questions to filter by story/place etc...
          2. Make nodes and edges from the filtered files. 
          
          ######## Filtering ######## option to dicuss?
          1. Title Yes/No. Take files with titles if yes, no otherwise.
          2. Story Yes/No.
          3. Place Yes/No
          4. Person Yes/No
          
          ######## Needed ########
          ## TODO ##
		  * Filter by Title yes/no
		  * Filter by Story yes/no
		  * Filter by Place yes/no
		  * Filter by person yes/no
          ## DONE ##
          *
         */
    	
    	
    	/******************************* The Input of The XPATHS ******************************/
    	
        @SuppressWarnings("resource")
		Scanner keyboard= new Scanner(System.in);
        String path=null;
        ArrayList<String> XpathExpression= new ArrayList<String>(); //made an ArrayList to save the expression
        int i = 1;
        System.out.println("Please Type your Xpath's and When you are done please type 'stop'!");
        do{
            System.out.print("Type your Xpath Number "+i+": ");
            path=keyboard.next();
            if(path.equalsIgnoreCase("stop")) break; //when gonna our code stop and start with propcess ? with the word 'stop'
            XpathExpression.add(path);
            i++;
        }while(true);


        System.out.println();
        System.out.println("You typed The follwoing Xpath's: ");
       // System.out.println();
        for(String w: XpathExpression){
            System.out.println(w+",");
           // System.out.println();
        }
        System.out.println();
    	System.out.println("Please note that your XML files will be filtered according to the XPathes you entered");
    	System.out.println();
    	
    	
    	//File[] FilterdFiles = new File[100000];
    	
    	
    	/**************************** ArrayList to save filterd files ******************************/
    	
    	ArrayList<File> FilterdFiles= new ArrayList<File>(); //List of the filtered files
    	
    	System.out.println("Log:");
    	
    	/******* Get XMl Folder location ******/
    	
    	File folder = new File("C:\\Users\\Safi\\Desktop\\XML PROBEN");
        File[] files = folder.listFiles();
        for (File file : files) {
        	boolean test = false;
        	if(file.isFile() && file.getName().endsWith(".xml")) { // to make sure that all files that gonna be processed are XML files
        		Document document = getDocument(file);//call the parss method getDocument
        		
        		
        		/*******Get attribute values using xpath******/
        		
        		for(String xpathExp: XpathExpression) {

        			if(evaluateXPath(document, xpathExp).toString() != "[]") {
        				
        				test=true;
        				 System.out.println("From XML-File Number "+file.getName()+" We got: "+evaluateXPath(document, xpathExp));
        				
        			}
        			
    
        			else if(evaluateXPath(document, xpathExp).toString() == "[]") {
        				
        				System.out.println("From XML-File Number "+file.getName()+" We got: ⚠ERROR⚠ found nothing to return --> FILE: "+file.getName()+" WILL BE IGNORE");
        				test =false;
        				break;
        				
        			}
        				} 
        		
        		/*************** THE FILTER RESULTS *************/
        		
        		if(test == true)
        		FilterdFiles.add(file);
        		
        /*******  XpathExpression Examples for HELPING and TESTING !!??  ******/
        		
        		/** For The Main titels **/
        		//"//isebel:story/dc:title/text()
        		
        		/** For Storys IN ALL **/
        		//"//isebel:story/isebel:contents/isebel:content/text()

        		
        		/** For P-Names **/
                //"//isebel:story/isebel:persons/isebel:person/isebel:name/text()
        		
        		
        		/** For Places title **/
        		//"//isebel:story/isebel:places/isebel:place/dc:title/text()
        		}
        	
        	}
        
        
        //Check if the filter has worked?
        System.out.println();
        System.out.println("The filtered files that match your criteria: "+"Count: "+FilterdFiles.size());
        for(File filtered: FilterdFiles) {
        	System.out.print(filtered.getName()+"||");
        }
        
    /*now after we have the files filtered we have to move to the next step
    *in the next step the user also define the rules about how to get the graph in the end results!!
    *thats mean he will also give Xpathes to choose the specific nodes
    *
    */
    }
    
     
    private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception 
    {
        // Create XPathFactory object
        XPathFactory xpathFactory = XPathFactory.newInstance();
         
        // Create XPath object
        XPath xpath = xpathFactory.newXPath();
       // xpath.setNamespaceContext(new NamespaceResolver(doc));
        @SuppressWarnings("hiding")
		class NamespaceResolver implements NamespaceContext 
        {
            //Store the source document to search the namespaces
            private Document sourceDocument;
         
            public NamespaceResolver(Document document) {
                sourceDocument = document;
            }
         
            //The lookup for the namespace uris is delegated to the stored document.
            public String getNamespaceURI(String prefix) {
                if (prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
                    return sourceDocument.lookupNamespaceURI(null);
                } else {
                    return sourceDocument.lookupNamespaceURI(prefix);
                }
            }
         
            public String getPrefix(String namespaceURI) {
                return sourceDocument.lookupPrefix(namespaceURI);
            }
         
            @SuppressWarnings({ "rawtypes", "unchecked" })
            public Iterator getPrefixes(String namespaceURI) {
                return null;
            }
        }
        
        xpath.setNamespaceContext(new NamespaceResolver(document));
        
        List<String> values = new ArrayList<>();
        try
        {
            // Create XPathExpression object
            XPathExpression expr = xpath.compile(xpathExpression);
             
            // Evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
            
            for (int i = 0; i < nodes.getLength(); i++) {
                values.add(nodes.item(i).getNodeValue());
            }
                 
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return values;
    }
 
    private static Document getDocument(File fileName) throws Exception 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fileName);
        return doc;
    }
    class NamespaceResolver implements NamespaceContext 
    {
        //Store the source document to search the namespaces
        private Document sourceDocument;
     
        public NamespaceResolver(Document document) {
            sourceDocument = document;
        }
     
        //The lookup for the namespace uris is delegated to the stored document.
        public String getNamespaceURI(String prefix) {
            if (prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
                return sourceDocument.lookupNamespaceURI(null);
            } else {
                return sourceDocument.lookupNamespaceURI(prefix);
            }
        }
     
        public String getPrefix(String namespaceURI) {
            return sourceDocument.lookupPrefix(namespaceURI);
        }
     
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Iterator getPrefixes(String namespaceURI) {
            return null;
        }
    }
}