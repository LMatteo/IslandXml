package parser.tagBuilder;

import org.json.JSONObject;
import parser.Tag;

public class TagMoveTo extends TagBuilderDirected {
    public TagMoveTo(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.MOVETO.getName(),id);
    }
}
