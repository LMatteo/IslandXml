package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class TagBuilder {
    protected JSONObject request;
    protected JSONObject answer;
    private String name;
    protected int id;

    public TagBuilder(JSONObject request, JSONObject answer, String name, int id) {
        this.request = request;
        this.answer = answer;
        this.name = name;
        this.id = (id-1)/2;
    }

    public Element getActionXml(Document doc){
        Element element = doc.createElement(Constant.request);
        Element data = doc.createElement(Constant.data);
        Element action = doc.createElement(Constant.action);
        action.appendChild(doc.createTextNode(name));
        data.appendChild(action);
        element.appendChild(data);

        Element time = doc.createElement(Constant.time);
        time.appendChild(doc.createTextNode(String.valueOf(request.getLong(Constant.time))));
        element.appendChild(time);

        element.setAttribute(Constant.part,Constant.explorer);
        element.setAttribute(Constant.step,String.valueOf(id));

        return element;
    }
    public Element getAnswerXml(Document doc) {
        Element element = doc.createElement(Constant.answer);

        Element data = doc.createElement(Constant.data);

        JSONObject dataJson = answer.getJSONObject(Constant.data);

        Element cost = doc.createElement(Constant.cost);
        cost.appendChild(doc.createTextNode(String.valueOf(dataJson.getInt(Constant.cost))));
        data.appendChild(cost);

        Element extras = doc.createElement(Constant.extras);
        data.appendChild(extras);

        Element status = doc.createElement(Constant.status);
        status.appendChild(doc.createTextNode(dataJson.getString(Constant.status)));
        data.appendChild(status);

        element.appendChild(data);

        Element time = doc.createElement(Constant.time);
        time.appendChild(doc.createTextNode(String.valueOf(answer.getLong(Constant.time))));
        element.appendChild(time);

        element.setAttribute(Constant.part,Constant.engine);
        element.setAttribute(Constant.step,String.valueOf(id));

        return element;


    }
}
