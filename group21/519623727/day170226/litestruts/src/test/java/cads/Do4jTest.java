import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.net.URL;

/**
 * Created by Fan on 2017/3/4.
 */
public class Do4jTest {

    @Test
    public void test() throws Exception {
        SAXReader reader = new SAXReader();
        URL url = getClass().getResource("/struts/struts.xml");
        Document document = reader.read(url);
        String s = document.valueOf("//action[@name='login']/@class");
        System.out.println(s);
    }

    @Test
    public void test1() throws Exception {
        URL resource = getClass().getResource("/");
        System.out.println(resource.getFile());
    }
}
