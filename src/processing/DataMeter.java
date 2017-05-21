package processing;

import processing.results.ResultsToHtml;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Josué on 18/05/2017.
 */
public class DataMeter {

    private String men;
    private Map<String, Integer> neededResources;
    private Map<String, Integer> collectedResources;
    private String initialBudget;
    private int currentBudget;
    private int lengthTraveledAerial;
    private int lengthTraveledTerrestrial;
    private int aerialCost;
    private int terrestrialCost;
    private Map<String, Integer> ActionsCost;
    //TODO
    private String creek;

    public DataMeter() {
        men = "";
        neededResources = new HashMap<>();
        collectedResources = new HashMap<>();
        initialBudget = "";
        currentBudget = 0;
        lengthTraveledAerial= 0;
        lengthTraveledTerrestrial= 0;
        ActionsCost = new HashMap<>();
        aerialCost = 0;
        terrestrialCost = 0;
    }


    public void print() {
        ResultsToHtml results = new ResultsToHtml();
        //
        /*
        results.writeElementStart("initialisation");
        results.writeTitle("Initialisation");
        //
        results.writeln("Number of men : " + men);
        results.writeElementEnd();
        //
        */

        /**
         * Budget
         */
        results.writeCssElement("third");
        results.writeElementStart("budget");
        results.writeTitle("Budget");
        results.writeln("Initial budget : <span>" + initialBudget+"</span");
        //
        results.writeln("Remaining budget : <span>" + currentBudget+"</span>");
        results.writeEnd();
        //


        /**
         * Travel
         */
        results.writeElementStart("travel");
        results.writeTitle("Travel");
        //
        results.writeln("Length traveled : <span>" + (lengthTraveledAerial+lengthTraveledTerrestrial) + " tiles </span>");
        //
        results.writeln("Aerial length traveled : <span>" + lengthTraveledAerial + " tiles </span>");
        //
        results.writeln("Terrestrial length traveled : <span>" + lengthTraveledTerrestrial + " tiles </span>");
        results.writeEnd();
        results.writeEnd();
        //


        /**
         * Resources
         */
        results.writeCssElement("third");
        results.writeElementStart("resources");
        results.writeTitle("Resources");
        //
        results.writeln("Needed resources : ");
        results.writeUl();
        for (Map.Entry<String, Integer> entry : neededResources.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            results.writeInLi(key + " <span>" + value + "</span> ",key);
        }
        results.writeUlEnd();
        results.write("Collected resources : ");
        results.writeUl();
        for (Map.Entry<String, Integer> entry : collectedResources.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            results.writeInLi(key + " <span>" + value + "</span>",key);
        }
        results.writeUlEnd();
        results.writeEnd();
        results.writeEnd();


        /**
         * Costs
         */
        results.writeCssElement("third");
        results.writeElementStart("costs");
        results.writeTitle("Costs");
        int init = Integer.parseInt(initialBudget);
        float aerialpart = round((float)aerialCost/init*100,2);
        results.writeln("Part of aerial cost :<span> "+aerialpart+"%</span>");
        //
        float terrpart = round((float)terrestrialCost/init*100,2);
        results.writeln("Part of terrestrial cost : <span>"+terrpart+"% </span>");
        //
        int total = 0;
        ActionsCost = sortByValue(ActionsCost);
        for (Map.Entry<String, Integer> entry : ActionsCost.entrySet()) {
            total += entry.getValue();
        }
        results.writeln("Cost per action : ");
        results.writeUl();
        for (Map.Entry<String, Integer> entry : ActionsCost.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            float ratio = round((float)value/total*100,2);
            StringBuilder sb= new StringBuilder(key);
            results.writeInLi(sb.toString().toUpperCase() + " : <span>" + value + " ("+ratio+"%)</span>",key);
        }
        results.writeUlEnd();
        //
        results.writeEnd();
        results.writeEnd();

        results.close();
    }

    public void gatherResource(String res, int qte) {
        int newQty;
        if (collectedResources.containsKey(res)) {
            newQty = collectedResources.get(res) + qte;
        } else {
            newQty = qte;
        }
        collectedResources.put(res, newQty);
    }

    public void removeResource(String res, int qte) {
        int newQty = collectedResources.get(res) - qte;
        collectedResources.put(res, newQty);
    }

    public void actionPlus(String res, int qte) {
        int q;
        if (ActionsCost.containsKey(res)) {
            q = ActionsCost.get(res) + qte;
        } else {
            q = qte;
        }
        ActionsCost.put(res, q);
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Iterator<Map.Entry<K, V>> it = list.iterator(); it.hasNext();) {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public void setInitialBudget(String initialBudget) {
        this.initialBudget = initialBudget;
        this.currentBudget = Integer.parseInt(initialBudget);
    }

    public void lengthAerialPlus(int n){
        lengthTraveledAerial += n;
    }

    public void lengthTerrestrialPlus(int n){
        lengthTraveledTerrestrial += n;
    }


    public Map<String, Integer> getNeededResources() {
        return neededResources;
    }


    public void budgetMinus(int n) {
        currentBudget -= n;
    }

    public void aerialCostPlus(int n){
        aerialCost+=n;
    }

    public void terrestrialCostPlus(int n){
        terrestrialCost+=n;
    }

    public Map<String,Float> contractCompletion(){
        Map<String,Float> completion = new HashMap<>();

        List<Map.Entry<String,Float>> list = new LinkedList(neededResources.entrySet());

        for (Map.Entry entry : list){
            if(collectedResources.containsKey(entry.getKey())){
                int collected = collectedResources.get(entry.getKey());
                float percentage = ((float)collected/(int)entry.getValue())*100;
                completion.put((String)entry.getKey(),percentage);
            }else {
                completion.put((String) entry.getKey(), new Float("0.0"));
            }
        }

        return completion;


    }

}
