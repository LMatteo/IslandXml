package parser;

import org.json.JSONObject;
import parser.tagBuilder.TagBuilder;
import parser.tagBuilder.TagFly;

public enum Element {
    FLY {
        @Override
        public String getName() {
            return "fly";
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagFly(action,answer);
        }
    };

    public abstract String getName();
    public abstract TagBuilder getBuilder(JSONObject action, JSONObject answer);
}
