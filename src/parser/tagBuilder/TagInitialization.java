package parser.tagBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TagInitialization {
    private JSONObject init;

    public TagInitialization(JSONObject init) {
        this.init = init;
    }

    public Element getInitXml(Document doc){
        Element initialization = doc.createElement(Constant.initialization);

        JSONObject dataJson = init.getJSONObject(Constant.data);

        Element data = doc.createElement(Constant.data);

        Element heading = doc.createElement(Constant.heading);
        heading.appendChild(doc.createTextNode(dataJson.getString(Constant.heading)));
        data.appendChild(heading);

        Element men = doc.createElement(Constant.men);
        men.appendChild(doc.createTextNode(String.valueOf(dataJson.getInt(Constant.men))));
        data.appendChild(men);

        Element contracts = doc.createElement(Constant.contracts);
        data.appendChild(contracts);

        buildContracts(doc,contracts,dataJson.getJSONArray(Constant.contracts));

        Element time = doc.createElement(Constant.time);
        time.appendChild(doc.createTextNode(String.valueOf(init.getLong(Constant.time))));
        initialization.appendChild(time);

        Element budget = doc.createElement(Constant.budget);
        budget.appendChild(doc.createTextNode(String.valueOf(dataJson.getInt(Constant.budget))));
        data.appendChild(budget);

        initialization.appendChild(data);
        return initialization;
    }

    private void buildContracts(Document doc, Element contracts, JSONArray array){
        for(int i = 0;i<array.length();i++){

            Element contract = doc.createElement(Constant.contract);
            JSONObject current = array.getJSONObject(i);

            Element amount = doc.createElement(Constant.amount);
            amount.appendChild(doc.createTextNode(String.valueOf(current.getInt(Constant.amount))));
            contract.appendChild(amount);

            Element resource = doc.createElement(Constant.resource);
            resource.appendChild(doc.createTextNode(current.getString(Constant.resource)));
            contract.appendChild(resource);

            contracts.appendChild(contract);
        }
    }
}
