package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import parser.Tag;

public class TagEcho extends TagBuilderDirected {
    public TagEcho(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.ECHO.getName(),id);
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element = super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName(Constant.extras).item(0);

        JSONObject extrasJson = answer.getJSONObject(Constant.data).getJSONObject(Constant.extras);

        Element range = doc.createElement(Constant.range);
        range.appendChild(doc.createTextNode(String.valueOf(extrasJson.getInt(Constant.range))));

        Element found = doc.createElement(Constant.found);
        found.appendChild(doc.createTextNode(extrasJson.getString(Constant.found)));

        extras.appendChild(found);
        extras.appendChild(range);

        return element;
    }
}
