package parser.tagBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import parser.Tag;

public class TagExplore extends TagBuilder {

    public TagExplore(JSONObject request, JSONObject answer, int id) {
        super(request, answer, Tag.EXPLORE.getName(), id);
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element =  super.getAnswerXml(doc);

        JSONObject extrasJson = answer.getJSONObject(Constant.data).getJSONObject(Constant.extras);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName(Constant.extras).item(0);

        Element resource = doc.createElement(Constant.resource);
        extras.appendChild(resource);

        buildExplorations(doc,extrasJson.getJSONArray(Constant.resources),resource);

        Element pois = doc.createElement(Constant.pois);
        extras.appendChild(pois);

        buildPois(doc,extrasJson.getJSONArray(Constant.pois),pois);

        return element;
    }

    private void buildExplorations(Document doc, JSONArray array,Element explorations){
        for(int i = 0;i<array.length();i++){

            Element exploration = doc.createElement(Constant.exploration);

            JSONObject current = array.getJSONObject(i);

            Element amount = doc.createElement(Constant.amount);
            amount.appendChild(doc.createTextNode(current.getString(Constant.amount)));
            exploration.appendChild(amount);

            Element resource = doc.createElement(Constant.resource);
            resource.appendChild(doc.createTextNode(current.getString(Constant.resource)));
            exploration.appendChild(resource);

            Element cond = doc.createElement(Constant.cond);
            cond.appendChild(doc.createTextNode(Constant.cond));
            exploration.appendChild(cond);

            explorations.appendChild(exploration);

        }
    }

    private void buildPois(Document doc,JSONArray array,Element pois){
        for(int i = 0;i<array.length();i++){
            JSONObject current = array.getJSONObject(i);

            Element zone = doc.createElement(Constant.zone);

            Element kind = doc.createElement(Constant.kind);
            kind.appendChild(doc.createTextNode(current.getString(Constant.kind)));
            zone.appendChild(kind);

            Element id = doc.createElement(Constant.id);
            id.appendChild(doc.createTextNode(current.getString(Constant.id)));
            zone.appendChild(id);

            zone.appendChild(pois);
        }
    }
}
