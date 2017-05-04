package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TagFly extends TagBuilder {
    private String name = "fly";

    public TagFly(JSONObject request, JSONObject answer) {
        super(request, answer);
    }

    @Override
    public void getActionXml(Element championship, Document doc){
        Element element = doc.createElement("element");
        Element data = doc.createElement("data");
        Element action = doc.createElement("action");
        action.appendChild(doc.createTextNode(name));
        data.appendChild(action);
        element.appendChild(data);

        Element part = doc.createElement("part");
        part.appendChild(doc.createTextNode("engine"));
        element.appendChild(part);

        Element time = doc.createElement("time");
        time.appendChild(doc.createTextNode(String.valueOf(request.getLong("time"))));
        element.appendChild(time);

        championship.appendChild(element);

    }

    @Override
    public void getAnswerXml(Element element, Document document) {

    }
}
