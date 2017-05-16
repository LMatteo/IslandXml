package parser;

import org.json.JSONObject;
import parser.tagBuilder.*;

public enum Tag {
    FLY("fly") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagFly(action,answer,id);
        }
    },
    STOP("stop") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagStop(action,answer,id);
        }
    },
    HEADING("heading") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagHeading(action,answer,id);
        }
    },
    MOVETO("move_to") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagMoveTo(action,answer,id);
        }
    },
    ECHO("echo") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagEcho(action,answer,id);
        }
    },
    SCAN("scan") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagScan(action,answer,id);
        }
    },
    LAND("land") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagLand(action,answer,id);
        }
    },
    SCOUT("scout") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagScout(action,answer,id);
        }
    },
    GLIMPSE("glimpse") {
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) {
            return new TagGlimpse(action,answer,id);
        }
    },
    EXPLOIT("exploit"){
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) { return new TagExploit(action,answer, id);}
    },
    TRANSFORM("transform"){
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) { return new TagTransform(action,answer, id);}
    },
    EXPLORE("explore"){
        @Override
        public TagBuilder getBuilder(JSONObject action, JSONObject answer, int id) { return new TagExplore(action,answer, id);}
    }
    ;

    private String name;

    Tag(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public abstract TagBuilder getBuilder(JSONObject action, JSONObject answer, int id);
}
