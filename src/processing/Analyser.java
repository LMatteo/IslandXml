package processing;

import org.w3c.dom.Document;
import processing.results.Results;

/**
 * Created by Josué on 16/05/2017.
 */
public enum Analyser {
    NUMBER_OF_MEN{
        @Override
        public void execute(Document doc, Results result){
            result.write("Number of men");
        }
    },
    RESOURCE_NEEDED{
        @Override
        public void execute(Document doc, Results result){
            result.write("Resource needed");
        }
    },
    INITIAL_BUDGET{
        @Override
        public void execute(Document doc, Results result){
            result.write("Initial budget");
        }
    },
    COST_OF_AERIAL{
        @Override
        public void execute(Document doc, Results result){
            result.write("Cost of aerial exploration");
        }
    },
    LENGTH_TRAVELED_AERIAL{
        @Override
        public void execute(Document doc, Results result){
            result.write("Length traveled in aerial");
        }
    },
    COST_PER_ACTION_AERIAL{
        @Override
        public void execute(Document doc, Results result){
            result.write("Cost per action in aerial");
        }
    },
    COST_TERRESTRIAL{
        @Override
        public void execute(Document doc, Results result){
            result.write("Cost of terrestrial exploration");
        }
    },
    LENGTH_TRAVELED_TERRESTRIAL{
        @Override
        public void execute(Document doc, Results result){
            result.write("Length traveled in terrestrial");
        }
    },
    COST_PER_ACTION_TERRESTRIAL{
        @Override
        public void execute(Document doc, Results result){
            result.write("Cost per action in terrestrial");
        }
    },
    REMAINING_BUDGET_{
        @Override
        public void execute(Document doc, Results result){
            result.write("Remaining budget");
        }
    },
    COLLECTED_RESOURCES{
        @Override
        public void execute(Document doc, Results result){
            result.write("Collected resources");
        }
    },
    COST_PER_ACTION{
        @Override
        public void execute(Document doc, Results result){
            result.write("Total cost per action");
        }
    },
    RANKING_OF_ACTIONS{
        @Override
        public void execute(Document doc, Results result){
            result.write("Ranking of actions");
        }
    };

    Analyser() {
    }

    public abstract void execute(Document doc, Results result);

}
