package parser.tagBuilder;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import parser.Tag;

public class TagFly extends TagBuilder {

    public TagFly(JSONObject request, JSONObject answer) {
        super(request, answer, Tag.FLY.getName());
    }

}
