package parser;

import org.json.JSONObject;
import parser.tagBuilder.*;

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
    },
    MOVETO {
        @Override
        public String getName() {
            return "move_to";
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagMoveTo(action,answer);
        }
    },
    ECHO {
        @Override
        public String getName() {
            return "echo";
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagEcho(action,answer);
        }
    },
    SCAN {
        @Override
        public String getName() {
            return "scan";
        }

        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagScan(action,answer);
        }
    };

    public abstract String getName();
    public abstract TagBuilder getBuilder(JSONObject action, JSONObject answer);
}
