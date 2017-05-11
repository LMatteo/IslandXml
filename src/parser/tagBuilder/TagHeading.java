package parser.tagBuilder;

import org.json.JSONObject;
import parser.Tag;

public class TagHeading extends TagBuilderDirected{
    public TagHeading(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.HEADING.getName(),id);
    }
}
