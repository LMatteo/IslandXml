package processing;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Josué on 16/05/2017.
 */
public class DataProcessing {

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

        meter.print();

    }
}
