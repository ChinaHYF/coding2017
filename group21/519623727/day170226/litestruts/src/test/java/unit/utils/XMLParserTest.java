package utils;

import domain.Action;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Fan on 2017/3/4.
 */
public class XMLParserTest {
    @Test
    public void parse() throws Exception {
        Map<String, Action> actionMap = new XMLParser().parse("/struts/struts.xml");
        Assert.assertEquals("success", actionMap.get("login").getSuccess().getName());
        Assert.assertEquals("logout", actionMap.get("logout").getName());
    }
}