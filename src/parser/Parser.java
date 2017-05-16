package parser;
import org.json.*;
import parser.tagBuilder.Constant;
import parser.tagBuilder.TagBuilder;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
/*
    ON UTILISE PAS MAVEN ON OUBLIE PAS D'AJOUTER LE JAR AU PROJET
    http://central.maven.org/maven2/org/json/json/20160810/json-20160810.jar
*/

public class Parser {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];

        Document document;
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        document = docBuilder.newDocument();

        org.w3c.dom.Element rootElement = document.createElement(Constant.championship);
        document.appendChild(rootElement);


        //on crée le json à partir du fichier
        JSONArray json = new JSONArray(readFile(fileName));

        //TODO : gérer l'ecriture de l'initialisation

        //on parcourt le tableau 2 à 2
        //l'action et la réponse sont traitées ensemble pour plus de facilité
        for(int i = 1;i<json.length();i += 2){
            createDoc(json,i,rootElement,document);
        }

        printXml(document);


    }

    private static String readFile(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        StringBuilder fileContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            fileContent.append(line);
        }

        return fileContent.toString();
    }

    private static void createDoc(JSONArray arr, int i, org.w3c.dom.Element element, Document doc){
        //à changer
        String currentAction = arr.getJSONObject(i).getJSONObject(Constant.data).getString(Constant.action);

        //on cherche quelle action correspond au Json donné
        for(Tag elem : Tag.values()){
            //TODO : rempli l'enum et implémenter les classes TagBuilder
            if(elem.getName().equals(currentAction)){

                TagBuilder builder = elem.getBuilder(arr.getJSONObject(i),arr.getJSONObject(i+1),i);
                element.appendChild(builder.getActionXml(doc));
                element.appendChild(builder.getAnswerXml(doc));
            }
        }
    }

    private static void printXml(Document doc) throws IOException,TransformerException{
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(new FileOutputStream("./fermeLaJosué.xml"), "UTF-8")));

    }
}
