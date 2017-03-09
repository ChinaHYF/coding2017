package utils;

import domain.Action;
import org.dom4j.DocumentException;

import java.util.Map;

/**
 * Created by Fan on 2017/3/4.
 */
public class ActionContainer {
    private Map<String, Action> actionMap;

    public ActionContainer() {
        try {
            actionMap = new XMLParser().parse("/struts/struts.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Action getAction(String actionName) {
        return actionMap.get(actionName);
    }
}
