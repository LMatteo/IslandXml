import parser.Parser;
import processing.DataProcessing;

public class Main {
    public static void main(String[] args) throws Exception{
        String action = args[0];

        if(args.length<2){
            System.err.println("missing arguments");
            System.exit(1);
        }

        String[] param = new String[args.length-1];
        param[0] = args[1];

        if(action.equals("data")){
            DataProcessing.main(param);
        }else if(action.equals("parse")){
            Parser.main(param);
        }
    }
}
