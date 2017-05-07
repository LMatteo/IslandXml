package parser.tagBuilder;

import org.json.JSONObject;
import parser.Tag;

public class TagStop extends TagBuilder{
    public TagStop(JSONObject request, JSONObject answer) {
        super(request, answer, Tag.STOP.getName());
    }
}
