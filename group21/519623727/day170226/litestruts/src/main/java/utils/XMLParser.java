package utils;

import com.google.common.collect.Lists;
import domain.Action;
import domain.Result;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.*;

/**
 * Created by Fan on 2017/3/4.
 */
public class XMLParser {

    public Map<String, Action> parse(String xmlPath) throws DocumentException {
        URL url = getClass().getResource(xmlPath);
        Map<String, Action> actionMap = new HashMap<String, Action>(5);
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        Element rootElement = document.getRootElement();
        Iterator actionsIterator = rootElement.elementIterator();
        while(actionsIterator.hasNext()) {
            Element actionElement = (Element)actionsIterator.next();
            Action action = new Action();
            String actionName = actionElement.valueOf("@name");
            action.setName(actionName);
            action.setClassPath(actionElement.valueOf("@class"));
            Iterator resultsIterator = actionElement.elementIterator();
            while (resultsIterator.hasNext()) {
                Element resultElement = (Element) resultsIterator.next();
                Result result = new Result();
                String name = resultElement.valueOf("@name");
                result.setName(name);
                result.setValue(resultElement.getStringValue());
                action.setResult(name, result);
            }
            actionMap.put(actionName, action);
        }
        return actionMap;
    }
}
