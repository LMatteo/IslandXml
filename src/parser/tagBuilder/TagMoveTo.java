package parser.tagBuilder;

import org.json.JSONObject;
import parser.Tag;

public class TagMoveTo extends TagBuilderDirected {
    public TagMoveTo(JSONObject request, JSONObject answer) {
        super(request, answer, Tag.MOVETO.getName());
    }
}
