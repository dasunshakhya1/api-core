package unit;

import model.core.HeaderData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestHeaderData {

    HeaderData headerData;

    @BeforeClass
    public void setUp() {
        headerData = new HeaderData();

    }


    @Test
    public void testSettingHeaderData() {
        headerData.addHeaderValues("header1", "key1").addHeaderValues("header2", 100).addHeaderValues("header3", false);
    }


    @Test(dependsOnMethods = {"testSettingHeaderData"})
    public void testHeaderDataRetrieval() {
        headerData.getRequestHeaders().keySet()
                .forEach(k -> System.out.println(k+"  "+headerData.getRequestHeaders().get(k)));
    }

}
