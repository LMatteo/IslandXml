package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class TagBuilderDirected extends TagBuilder{
    public TagBuilderDirected(JSONObject request, JSONObject answer, String name,int id) {
        super(request, answer, name,id);
    }

    @Override
    public Element getActionXml(Document doc){
        Element element = super.getActionXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        Element parameters = doc.createElement(Constant.parameters);


        Element direction = doc.createElement(Constant.direction);
        direction.appendChild(doc.createTextNode(request.getJSONObject(Constant.data).
                getJSONObject(Constant.parameters).
                getString(Constant.direction)));

        parameters.appendChild(direction);

        data.appendChild(parameters);


        return element;
    }
}
