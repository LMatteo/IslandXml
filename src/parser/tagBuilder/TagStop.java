package parser.tagBuilder;

import org.json.JSONObject;

public class TagStop extends TagBuilder{
    public TagStop(JSONObject request, JSONObject answer) {
        super(request, answer, "stop");
    }
}
