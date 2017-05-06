package parser.tagBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TagScan extends TagBuilder {
    public TagScan(JSONObject request, JSONObject answer) {
        super(request, answer, "scan");
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element = super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName("data");
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName("extras").item(0);

        JSONObject extrasJson = answer.getJSONObject("data").getJSONObject("extras");

        Element biomes = doc.createElement("biomes");
        arrayToXml(biomes,extrasJson.getJSONArray("biomes"),doc,"biome");

        Element creeks = doc.createElement("creeks");
        arrayToXml(creeks,extrasJson.getJSONArray("creeks"),doc,"creek");

        Element sites = doc.createElement("sites");
        arrayToXml(sites,extrasJson.getJSONArray("sites"),doc,"site");

        extras.appendChild(biomes);
        extras.appendChild(creeks);
        extras.appendChild(sites);


        return element;
    }

    private void arrayToXml(Element element, JSONArray array,Document doc,String name){
        for(int i = 0;i<array.length();i++){
            Element tag = doc.createElement(name);
            tag.appendChild(doc.createTextNode(array.getString(i)));
            element.appendChild(tag);
        }
    }

    /*
    {"cost": 2,
     "extras": { "biomes": ["BEACH"], "creeks": [], "sites": ["id"]},
     "status": "OK"}
     */
}
