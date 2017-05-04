package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class TagBuilder {
    protected JSONObject request;
    protected JSONObject answer;

    public TagBuilder(JSONObject request, JSONObject answer) {
        this.request = request;
        this.answer = answer;
    }

    public abstract void getActionXml(Element element, Document doc);
    public abstract void getAnswerXml(Element element, Document document);
}
