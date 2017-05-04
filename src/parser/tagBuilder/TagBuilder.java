package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class TagBuilder {
    protected JSONObject request;
    protected JSONObject answer;
    protected String name;

    public TagBuilder(JSONObject request, JSONObject answer, String name) {
        this.request = request;
        this.answer = answer;
        this.name = name;
    }

    public void getActionXml(Element championship, Document doc){
        Element element = doc.createElement("element");
        Element data = doc.createElement("data");
        Element action = doc.createElement("action");
        action.appendChild(doc.createTextNode(name));
        data.appendChild(action);
        element.appendChild(data);

        Element part = doc.createElement("part");
        part.appendChild(doc.createTextNode("Explorer"));
        element.appendChild(part);

        Element time = doc.createElement("time");
        time.appendChild(doc.createTextNode(String.valueOf(request.getLong("time"))));
        element.appendChild(time);

        championship.appendChild(element);
    }
    public void getAnswerXml(Element championship, Document doc) {
        Element element = doc.createElement("element");

        Element data = doc.createElement("data");

        JSONObject dataJson = answer.getJSONObject("data");

        Element cost = doc.createElement("cost");
        cost.appendChild(doc.createTextNode(String.valueOf(dataJson.getInt("cost"))));
        data.appendChild(cost);

        Element extras = doc.createElement("extras");
        data.appendChild(extras);

        Element status = doc.createElement("status");
        status.appendChild(doc.createTextNode(dataJson.getString("status")));
        data.appendChild(status);

        element.appendChild(data);

        Element part = doc.createElement("part");
        part.appendChild(doc.createTextNode("Engine"));
        element.appendChild(part);

        Element time = doc.createElement("time");
        time.appendChild(doc.createTextNode(String.valueOf(answer.getLong("time"))));
        element.appendChild(time);

        championship.appendChild(element);


    }
}
