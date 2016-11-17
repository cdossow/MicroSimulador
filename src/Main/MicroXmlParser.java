package Main;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class MicroXmlParser {
	/*
    public static void main(String[] args) {
        String nombre_archivo = "geekyxml";
        ArrayList key = new ArrayList();
        ArrayList value = new ArrayList();

        key.add("opcion1");
        value.add("22");

        key.add("opcion2");
        value.add("22");

        key.add("opcion3");
        value.add("22");

        key.add("opcion4");
        value.add("25");

        try { 
            //generate(nombre_archivo, key, value);
        } catch (Exception e) {}
    }*/

    public static void generate(Linea linea) throws Exception{
    	
    	String name = "NMICRO";
    	
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, name, null);
        document.setXmlVersion("1.0");

        //Main Node
        Element raiz = document.getDocumentElement();
        //Por cada key creamos un item que contendrá la key y el value
        Element itemLINEA = document.createElement("LINEA"); 
        
        Element NameNode = document.createElement("NOMBRE"); 
        Text nodeValue = document.createTextNode(linea.nombre);
        NameNode.appendChild(nodeValue);
        itemLINEA.appendChild(NameNode);
        
    	
    	for (Ruta ruta : linea.getRutas()){
    	//Element itemRUTA = document.createElement("RUTA"); 
            
            for(Micro micro : linea.micro){
            	if(micro.ruta.id==ruta.id){
                    //Item Node
                    Element itemNode = document.createElement("MICRO"); 
                    //Key Node
                    Element idNode = document.createElement("ID"); 
                    nodeValue = document.createTextNode(""+micro.id);
    				idNode.appendChild(nodeValue);
    				
                    Element patNode = document.createElement("PATENTE"); 
                    nodeValue = document.createTextNode(""+micro.patente);
                    patNode.appendChild(nodeValue);
                    
    				Element idRutaNode = document.createElement("ID_RUTA"); 
                    nodeValue = document.createTextNode(""+micro.ruta.id);                
                    idRutaNode.appendChild(nodeValue);
                    
                    Element lateNode = document.createElement("LATITUDE"); 
                    nodeValue = document.createTextNode(""+micro.lat);                
                    lateNode.appendChild(nodeValue);
                    
                    Element lngNode = document.createElement("LONGITUDE"); 
                    nodeValue = document.createTextNode(""+micro.lng);                
                    lngNode.appendChild(nodeValue);
                    
                    Element idLineaNode = document.createElement("ID_LINEA"); 
                    nodeValue = document.createTextNode(""+micro.id_linea);                
                    idLineaNode.appendChild(nodeValue);
                    
                    itemNode.appendChild(idNode);
                    itemNode.appendChild(patNode);
                    itemNode.appendChild(idRutaNode);
                    itemNode.appendChild(lateNode);
                    itemNode.appendChild(lngNode);
                    itemNode.appendChild(idLineaNode);
                    itemLINEA.appendChild(itemNode);
            	}
            }
            //itemLINEA.appendChild(itemRUTA);
    	}

    	//append itemNode to raiz
    	raiz.appendChild(itemLINEA); //pegamos el elemento a la raiz "Documento"
        //Generate XML
    	document.normalizeDocument();
        Source source = new DOMSource(document);
        
        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new java.io.File(linea.nombre+".xml")); //nombre del archivo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
    }

}
