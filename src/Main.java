import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        //Want to read 
        ArrayList<String> titles  = new ArrayList<String>();
        
 
        //read alle xml dateien // test for bestimmte nodes
        /*
         We need to read the xml files to build the graphs.
         The graph will be represented in 2 files, nodes.csv and edges.csv.
         What are the nodes? Stories - People - 
         What are the edges? 
         
         We have 15k files
         
         The tool can answer:
         * What stories did a specific author tell? Answer through the graph
         * What stories were told in a specific place?
         * 
         */
        File folder = new File("C:\\Users\\Safi\\Desktop\\XML PROBEN");
        File[] files = folder.listFiles();

        for (File file : files) {
        	if(file.isFile() && file.getName().endsWith(".xml")) {
                //parse the XML files
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true); 
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(file);        
                
                 
                //Get XPath expression what we want to get // (Title , Story , Names ...)
                XPathFactory xpathfactory = XPathFactory.newInstance();
                XPath xpath = xpathfactory.newXPath();
                xpath.setNamespaceContext(new NamespaceResolver(doc));
                XPathExpression expr = xpath.compile("//isebel:story/dc:title/text()"); 
                
                //Search XPath expression to get Title or story
                Object result = expr.evaluate(doc, XPathConstants.NODESET);
                 
                
              //Iterate 
                NodeList nodes = (NodeList) result;
                for (int i = 0; i < nodes.getLength(); i++) {
                    titles.add(nodes.item(i).getNodeValue());
                    
                }  
                
        	}
        	
        }
     
         
        //Verify dass it worked?!
        System.out.println("The titles are: \n"+titles);
		
		/* Exporting to unrefined files. 
		BufferedWriter nodeBW = new BufferedWriter(new FileWriter(new File(args[0]+"C:\\\\Users\\\\Safi\\\\Desktop\\\\_nodes.csv")));
		BufferedWriter edgeBW = new BufferedWriter(new FileWriter(new File(args[0]+"C:\\\\Users\\\\Safi\\\\Desktop\\\\_edges.csv")));
		//nodeBW.write(nodes.size() + System.getProperty("line.separator")); // you must comment out this statement, if it needs not to write the number of nodes at the top of output file.

		nodeBW.close();
		edgeBW.close();
*/
	}
}