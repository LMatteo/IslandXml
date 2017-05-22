package processing;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Josué on 16/05/2017.
 */
public class DataProcessing {

    /**
     * This program enables to translate a XML file
     * into serious data processing. This is the best
     * way to get a designed feed-back and a strong
     * analyse of your run.
     */

    public static void main(String[] args) throws Exception {

        String fileName = args[0];


        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);



        DataMeter meter = new DataMeter();

        for(Analyser a : Analyser.values()){
            a.execute(document,meter);
        }


        try{fileName = fileName.split("\\.")[0];}
        catch (Exception e){}

        meter.print(fileName+".html");

    }
}
