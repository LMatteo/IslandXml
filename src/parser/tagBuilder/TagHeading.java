package parser.tagBuilder;

import org.json.JSONObject;

public class TagHeading extends TagBuilderDirected{
    public TagHeading(JSONObject request, JSONObject answer) {
        super(request, answer, "heading");
    }
}
