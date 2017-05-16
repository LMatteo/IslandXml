package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import parser.Tag;

import java.util.Iterator;

/**
 * Created by Josué on 16/05/2017.
 */
public class TagTransform extends TagBuilder {
    public TagTransform(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.TRANSFORM.getName(), id);
    }

    @Override
    public Element getActionXml(Document doc){
        Element element = super.getActionXml(doc);

        JSONObject param = request.getJSONObject(Constant.data).getJSONObject(Constant.parameters);

        Iterator<String> keys = param.keys();

        Element para = doc.createElement(Constant.parameters);

        while( keys.hasNext() ) {
            String key = keys.next();

            Element unit = doc.createElement(Constant.unit);
            Element resource = doc.createElement(Constant.resource);
            Element quantity = doc.createElement(Constant.quantity);
            resource.appendChild(doc.createTextNode(key));
            quantity.appendChild(doc.createTextNode(Integer.toString(param.getInt(key))));
            unit.appendChild(resource);
            unit.appendChild(quantity);
            para.appendChild(unit);
        }


        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);
        data.appendChild(para);
        return element;
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element = super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        NodeList extraList = data.getElementsByTagName(Constant.extras);
        Element extra =(Element) extraList.item(0);

        JSONObject extrasJson = answer.getJSONObject(Constant.data).getJSONObject(Constant.extras);

        Element production = doc.createElement(Constant.production);
        production.appendChild(doc.createTextNode(String.valueOf(extrasJson.getInt(Constant.production))));

        Element kind = doc.createElement(Constant.kind);
        kind.appendChild(doc.createTextNode(String.valueOf(extrasJson.getString(Constant.kind))));

        extra.appendChild(production);
        extra.appendChild(kind);
        return element;
    }
}
