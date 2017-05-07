package parser.tagBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TagScout extends TagBuilderDirected {
    public TagScout(JSONObject request, JSONObject answer) {
        super(request, answer, "scout");
    }

    @Override
    public Element getAnswerXml(Document doc) {
        Element element =  super.getAnswerXml(doc);

        NodeList list = element.getElementsByTagName("data");
        Element data =(Element) list.item(0);

        Element extras = (Element) data.getElementsByTagName("extras").item(0);

        JSONObject extrasJson = answer.getJSONObject("data").getJSONObject("extras");

        Element altitutde = doc.createElement("altitude");
        altitutde.appendChild(doc.createTextNode(String.valueOf(extrasJson.getInt("altitude"))));
        extras.appendChild(altitutde);

        buildResArray(doc,extras,extrasJson.getJSONArray("resources"));

        return element;
    }

    private void buildResArray(Document doc, Element extras, JSONArray array){
        Element resources = doc.createElement("resources");

        for(int i = 0;i<array.length();i++){
            Element resource = doc.createElement("resource");
            resource.appendChild(doc.createTextNode(array.getString(i)));
            resources.appendChild(resource);
        }

        extras.appendChild(resources);
    }

    /*
    { "cost": 5, "extras": { "altitude": 1, "resources": ["FUR", "WOOD"] }, "status": "OK" }
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
