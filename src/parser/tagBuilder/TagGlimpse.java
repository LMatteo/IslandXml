package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import parser.Tag;

public class TagGlimpse extends TagBuilderDirected {
    public TagGlimpse(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.GLIMPSE.getName(),id);
    }

    @Override
    public Element getActionXml(Document doc) {
        Element element = super.getActionXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);

        NodeList dataList = data.getElementsByTagName(Constant.parameters);
        Element parameters = (Element) dataList.item(0);

        Element range = doc.createElement(Constant.range);
        range.appendChild(doc.createTextNode(String.valueOf(request.getJSONObject(Constant.data).
                getJSONObject(Constant.parameters).
                getInt(Constant.range))));
        parameters.appendChild(range);

        return  element;
    }
    /*
    <element>
		<data>
			<cost>3</cost>
			<extras>
				<asked_range>4</asked_range>
				<report>
					<tile>
						<resource>BEACH</resource>
						<percentage>59.35</percentage>
					</tile>
					<tile>
						<resource>OCEAN</resource>
					</tile>
				</report>
			</extras>
			<status>OK</status>
		</data>
		<part>Engine</part>
		<time>16815132</time>
	</element>
     */

    @Override
    public Element getAnswerXml(Document doc) {
        Element element = super.getActionXml(doc);

        NodeList list = element.getElementsByTagName(Constant.data);
        Element data =(Element) list.item(0);



        return element;
    }
}
