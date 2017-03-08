package utils;

import domain.Action;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fan on 2017/3/4.
 */
public class ActionContainerTest {

    @Test
    public void get() throws Exception {
        Action action = new ActionContainer().getAction("logout");
        Assert.assertEquals("com.coderising.action.LogoutAction", action.getClassPath());

    }
}