package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class TagBuilderDirected extends TagBuilder{
    public TagBuilderDirected(JSONObject request, JSONObject answer, String name) {
        super(request, answer, name);
    }

    @Override
    public Element getActionXml(Document doc){
        Element element = super.getActionXml(doc);

        NodeList list = element.getElementsByTagName("data");
        Element data =(Element) list.item(0);

        Element parameters = doc.createElement("parameters");


        Element direction = doc.createElement("direction");
        direction.appendChild(doc.createTextNode(request.getJSONObject("data").
                getJSONObject("parameters").
                getString("direction")));

        parameters.appendChild(direction);

        data.appendChild(parameters);


        return element;
    }
}
