package parser;

import org.w3c.dom.*;

import org.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Parser {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        JSONArray json = new JSONArray(readFile(fileName));

        for(int i = 1;i<json.length();i++){
            for(Element elem : Element.values()){

            }
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
}
