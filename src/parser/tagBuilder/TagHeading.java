package parser.tagBuilder;

import org.json.JSONObject;
import parser.Tag;

public class TagHeading extends TagBuilderDirected{
    public TagHeading(JSONObject request, JSONObject answer) {
        super(request, answer, Tag.HEADING.getName());
    }
}
