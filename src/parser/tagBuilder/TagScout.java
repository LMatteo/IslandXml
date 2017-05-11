package parser.tagBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import parser.Tag;

public class TagScout extends TagBuilderDirected {
    public TagScout(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.SCOUT.getName(),id);
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element =  super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName(Constant.extras).item(0);

        JSONObject extrasJson = answer.getJSONObject(Constant.data).getJSONObject(Constant.extras);

        Element altitutde = doc.createElement(Constant.altitude);
        altitutde.appendChild(doc.createTextNode(String.valueOf(extrasJson.getInt(Constant.altitude))));
        extras.appendChild(altitutde);

        buildResArray(doc,extras,extrasJson.getJSONArray(Constant.resources));

        return element;
    }

    private void buildResArray(Document doc, Element extras, JSONArray array){
        Element resources = doc.createElement(Constant.resources);

        for(int i = 0;i<array.length();i++){
            Element resource = doc.createElement(Constant.resource);
            resource.appendChild(doc.createTextNode(array.getString(i)));
            resources.appendChild(resource);
        }

        extras.appendChild(resources);
    }

    /*
    { Constant.cost: 5, Constant.extras: { Constant.altitude: 1, Constant.resources: ["FUR", "WOOD"] }, Constant.status: "OK" }
     */
    /*
    <element>
		<data>
			<cost>2</cost>
			<extras>
				<altitude>1</altitude>
				<resources>
					<resource>FUR</resource>
					<resource>WOOD</resource>
				</resources>
			</extras>
			<status>OK</status>
		</data>
		<part>Engine</part>
		<time>16815132</time>
	</element>
     */
}
