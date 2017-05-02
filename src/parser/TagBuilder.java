package parser;

import org.json.JSONObject;

public interface TagBuilder {

    String getActionXml(JSONObject json);
    String getAnswerXml(JSONObject json);
}
