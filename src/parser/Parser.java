package parser;
import org.json.*;
import parser.tagBuilder.Constant;
import parser.tagBuilder.TagBuilder;

import org.w3c.dom.*;
import parser.tagBuilder.TagInitialization;

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
    To compute json object we are using this library:
    http://central.maven.org/maven2/org/json/json/20160810/json-20160810.jar
*/

public class Parser {
    public static void main(String[] args) throws Exception {
        //the input file is the first argument
        String fileName = args[0];

        /*
        initialising the xml document
         */
        Document document;
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        document = docBuilder.newDocument();

        org.w3c.dom.Element rootElement = document.createElement(Constant.championship);
        document.appendChild(rootElement);


        //ceating json from input file
        JSONArray json = new JSONArray(readFile(fileName));

        //comptuion the only initialization tag
        TagInitialization init = new TagInitialization(json.getJSONObject(0));
        rootElement.appendChild(init.getInitXml(document));


        /*
           we are computing the action and the answer together
           (i+=2 instead of i+=1)
         */

        for(int i = 1;i<json.length();i += 2){
            createDoc(json,i,rootElement,document);
        }

        printXml(document,fileName);


    }

    /**
     *
     * @param filename the file to be read
     * @return a string containing the file content
     * @throws IOException
     */
    private static String readFile(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        StringBuilder fileContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            fileContent.append(line);
        }


        return fileContent.toString();
    }

    /**
     * convert given json to xml and append it to the xml element
     * @param arr jsonarray to be converted
     * @param i index of the jsonObject to be converted
     * @param element xml tag to which the tag will be appended
     * @param doc document used to create xml
     */
    private static void createDoc(JSONArray arr, int i, org.w3c.dom.Element element, Document doc){
        //looking for the action name
        String currentAction = arr.getJSONObject(i).getJSONObject(Constant.data).getString(Constant.action);

        //looking for the builder corresponding to the current action
        for(Tag elem : Tag.values()){
                if(elem.getName().equals(currentAction)){
                //once the right builder is found, it generates the xml and append it to the given element
                TagBuilder builder = elem.getBuilder(arr.getJSONObject(i),arr.getJSONObject(i+1),i);
                element.appendChild(builder.getActionXml(doc));
                element.appendChild(builder.getAnswerXml(doc));
            }
        }
    }

    /**
     * writing given doc into a file
     * @param doc xml document to be written
     * @param name name of the output file(the extension is removed)
     * @throws IOException
     * @throws TransformerException
     */
    private static void printXml(Document doc,String name) throws IOException,TransformerException{

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "island.dtd");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        try{name = name.split("\\.")[0];}
        catch (Exception e){}

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(new FileOutputStream("./"+name+".xml"), "UTF-8")));

    }
}
