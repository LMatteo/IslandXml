package parser;

import org.json.JSONObject;

public enum Element {
    FLY {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return null;
        }
    };

    public abstract String getName();
    public abstract TagBuilder getBuilder(JSONObject action, JSONObject answer);
}
