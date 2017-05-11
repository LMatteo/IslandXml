package parser.tagBuilder;

import org.json.JSONObject;
import parser.Tag;

public class TagStop extends TagBuilder{
    public TagStop(JSONObject request, JSONObject answer,int id) {
        super(request, answer, Tag.STOP.getName(),id);
    }
}
