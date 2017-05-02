package parser;
import org.json.*;
import java.io.*;
/*
    ON UTILISE PAS MAVEN ON OUBLIE PAS D'AJOUTER LE JAR AU PROJET
    http://central.maven.org/maven2/org/json/json/20160810/json-20160810.jar
*/

public class Parser {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        //on crée le json à partir du fichier
        JSONArray json = new JSONArray(readFile(fileName));

        //on instancie le writer
        PrintWriter writer = new PrintWriter("toXml.xml");

        //TODO : gérer l'ecriture de l'initialisation

        //on parcourt le tableau 2 à 2
        //l'action et la réponse sont traitées ensemble pour plus de facilité
        for(int i = 1;i<json.length();i += 2){
            write(json,i,writer);
        }

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

    public static void write(JSONArray arr,int i,PrintWriter writer){
        //à changer
        String currentAction = arr.getJSONObject(i).getJSONObject("data").getString("action");

        //on cherche quelle action correspond au Json donné
        for(Element elem : Element.values()){
            //TODO : rempli l'enum et implémenter les classes TagBuilder
            if(elem.getName().equals(currentAction)){

                TagBuilder builder = elem.getBuilder(arr.getJSONObject(i),arr.getJSONObject(i+1));
                writer.write(builder.getActionXml());
                writer.write(builder.getAnswerXml());
            }
        }
    }
}
