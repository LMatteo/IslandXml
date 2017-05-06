package parser;

import org.json.JSONObject;
import parser.tagBuilder.TagBuilder;
import parser.tagBuilder.TagFly;
import parser.tagBuilder.TagHeading;
import parser.tagBuilder.TagStop;

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
    },
    STOP {
        @Override
        public String getName() {
            return "stop";
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagStop(action,answer);
        }
    },
    HEADING {
        @Override
        public String getName() {
            return "heading";
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagHeading(action,answer);
        }
    };

    public abstract String getName();
    public abstract TagBuilder getBuilder(JSONObject action, JSONObject answer);
}
