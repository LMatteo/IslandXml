package processing.results;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Josué on 17/05/2017.
 */
public class ResultsToTxt implements Results{

    private PrintWriter writer;

    public ResultsToTxt() {
        try{
            writer = new PrintWriter("process.txt", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String s){
        writer.println(s);
    }

    public void close(){
        writer.close();
    }

}
