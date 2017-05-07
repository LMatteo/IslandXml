package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TagLand extends TagBuilder {
    public TagLand(JSONObject request, JSONObject answer) {
        super(request, answer, "land");
    }

    @Override
    public Element getActionXml(Document doc){
        Element element = super.getActionXml(doc);

        JSONObject param = request.getJSONObject("data").getJSONObject("parameters");

        NodeList list = element.getElementsByTagName("data");
        Element data =(Element) list.item(0);

        Element parameters = doc.createElement("parameters");

        Element creek = doc.createElement("creek");
        creek.appendChild(doc.createTextNode(param.getString("creek")));
        parameters.appendChild(creek);

        Element people = doc.createElement("people");
        people.appendChild(doc.createTextNode(String.valueOf(param.getInt("people"))));
        parameters.appendChild(people);

        data.appendChild(parameters);

        return element;
    }
    /*
    <element>
		<data>
			<action>land</action>
			<parameters>
				<creek>id</creek>
				<people>42</people>
			</parameters>
		</data>
		<part>Engine</part>
		<time>16815132</time>
		<meth>takeDecision</meth>
	</element>

     */
}
