package parser;

import org.json.JSONObject;
import parser.tagBuilder.*;

public enum Tag {
    FLY("fly") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagFly(action,answer);
        }
    },
    STOP("stop") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagStop(action,answer);
        }
    },
    HEADING("heading") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagHeading(action,answer);
        }
    },
    MOVETO("move_to") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagMoveTo(action,answer);
        }
    },
    ECHO("echo") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagEcho(action,answer);
        }
    },
    SCAN("scan") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagScan(action,answer);
        }
    },
    LAND("land") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagLand(action,answer);
        }
    },
    SCOUT("scout") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagScout(action,answer);
        }
    },
    GLIMPSE("glimpse") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer) {
            return new TagGlimpse(action,answer);
        }
    };

    private String name;

    Tag(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public abstract TagBuilder getBuilder(JSONObject action, JSONObject answer);
}
