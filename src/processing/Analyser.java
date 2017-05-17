package processing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.tagBuilder.Constant;
import processing.results.Results;

/**
 * Created by Josué on 16/05/2017.
 */
public enum Analyser {
    NUMBER_OF_MEN{
        @Override
        public void execute(Document doc, Results result){
            Node init = doc.getElementsByTagName(Constant.men).item(0);
            result.writeln("Number of men : "+init.getTextContent());
        }
    },
    RESOURCE_NEEDED{
        @Override
        public void execute(Document doc, Results result){
            NodeList contracts = doc.getElementsByTagName(Constant.contract);
            result.write(Integer.toString(contracts.getLength())+" resources needed : ");
            for (int i = 0; i < contracts.getLength(); i++) {
                Element contract = (Element) contracts.item(i);
                String res = contract.getElementsByTagName(Constant.resource).item(0).getTextContent();
                String qte = contract.getElementsByTagName(Constant.amount).item(0).getTextContent();
                result.write(res + "("+qte+")");
            }

        }
    },
    INITIAL_BUDGET{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Initial budget");
        }
    },
    COST_OF_AERIAL{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Cost of aerial exploration");
        }
    },
    LENGTH_TRAVELED_AERIAL{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Length traveled in aerial");
        }
    },
    COST_PER_ACTION_AERIAL{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Cost per action in aerial");
        }
    },
    COST_TERRESTRIAL{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Cost of terrestrial exploration");
        }
    },
    LENGTH_TRAVELED_TERRESTRIAL{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Length traveled in terrestrial");
        }
    },
    COST_PER_ACTION_TERRESTRIAL{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Cost per action in terrestrial");
        }
    },
    REMAINING_BUDGET_{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Remaining budget");
        }
    },
    COLLECTED_RESOURCES{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Collected resources");
        }
    },
    COST_PER_ACTION{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Total cost per action");
        }
    },
    RANKING_OF_ACTIONS{
        @Override
        public void execute(Document doc, Results result){
            result.writeln("Ranking of actions");
        }
    };

    Analyser() {
    }

    public abstract void execute(Document doc, Results result);

}
