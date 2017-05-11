package parser.tagBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import parser.Tag;

public class TagScan extends TagBuilder {
    public TagScan(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.SCAN.getName(),id);
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element = super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName(Constant.extras).item(0);

        JSONObject extrasJson = answer.getJSONObject(Constant.data).getJSONObject(Constant.extras);

        Element biomes = doc.createElement(Constant.biomes);
        arrayToXml(biomes,extrasJson.getJSONArray(Constant.biomes),doc,Constant.biome);

        Element creeks = doc.createElement(Constant.creeks);
        arrayToXml(creeks,extrasJson.getJSONArray(Constant.creeks),doc,Constant.creek);

        Element sites = doc.createElement(Constant.sites);
        arrayToXml(sites,extrasJson.getJSONArray(Constant.sites),doc,Constant.site);

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
    {Constant.cost: 2,
     Constant.extras: { Constant.biomes: ["BEACH"], Constant.creeks: [], Constant.sites: ["id"]},
     Constant.status: "OK"}
     */
}
