package config;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/*
 *  To parse the XML file for the required input specification.
 */
public class XMLConfig {

    public Generator_Specs parse_input_xml() {
        Generator_Specs g = new Generator_Specs();
        try {
            File inputFile = new File("src/main/java/config/input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getChildNodes();
            //System.out.println("----------------------------");
            NodeList nodeList = doc.getElementsByTagName("allowed types");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    g.number_of_classes = Integer.parseInt(eElement.getElementsByTagName("noOfClasses").item(0).getTextContent());
                    g.max_class_field=Integer.parseInt(eElement.getElementsByTagName("maxNoOfClassFields").item(0).getTextContent());
                    g.max_constructors=Integer.parseInt(eElement.getElementsByTagName("ConstructorDeclaration").item(0).getTextContent());
                    g.max_methods_class = Integer.parseInt(eElement.getElementsByTagName("maxNoOfMethodsPerClass").item(0).getTextContent());
                    g.recur_probab = Integer.parseInt(eElement.getElementsByTagName("maxNoofRecursiveMethods").item(0).getTextContent());
                    g.number_of_interfaces = Integer.parseInt(eElement.getElementsByTagName("noOfInterfaces").item(0).getTextContent());
                    g.max_number_of_interfaces=Integer.parseInt(eElement.getElementsByTagName("maxNoOfMethodsPerInterface").item(0).getTextContent());
                    g.max_para_method=Integer.parseInt(eElement.getElementsByTagName("maxNoOfParametersPerMethod").item(0).getTextContent());
                    g.max_stmts_methods= Integer.parseInt(eElement.getElementsByTagName("StatementsPerMethod").item(0).getTextContent());
                    g.maxloops= Integer.parseInt(eElement.getElementsByTagName("maxLoops").item(0).getTextContent());
                    g.maxifstatement= Integer.parseInt(eElement.getElementsByTagName("maxifstatement").item(0).getTextContent());


                    //System.out.println(g.toString());
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return g;
    }
}





