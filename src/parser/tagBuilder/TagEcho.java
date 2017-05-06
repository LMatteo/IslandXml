package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TagEcho extends TagBuilderDirected {
    public TagEcho(JSONObject request, JSONObject answer) {
        super(request, answer,"echo");
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element = super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName("data");
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName("extras").item(0);

        JSONObject extrasJson = answer.getJSONObject("data").getJSONObject("extras");

        Element range = doc.createElement("range");
        range.appendChild(doc.createTextNode(String.valueOf(extrasJson.getInt("range"))));

        Element found = doc.createElement("found");
        found.appendChild(doc.createTextNode(extrasJson.getString("found")));

        extras.appendChild(found);
        extras.appendChild(range);

        return element;
    }
}
