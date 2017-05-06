package parser.tagBuilder;

import org.json.JSONObject;

public class TagMoveTo extends TagBuilderDirected {
    public TagMoveTo(JSONObject request, JSONObject answer) {
        super(request, answer, "move_to");
    }
}
