package com.explore.item;

import com.explore.AbstractExploreTest;
import com.explore.config.ActionNames;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aladdin
 */
public class InsertIemServiceJUnitTest extends AbstractExploreTest {

    public InsertIemServiceJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

    @Test
    public void test() {
        Map<String, String> parameterMap = new HashMap<String, String>(4, 1);
        parameterMap.put("itemName", "充值卡");
        parameterMap.put("dataUrl", "test");
        parameterMap.put("desc", "100元充值卡");
        parameterMap.put("point", "1000");
        String result = this.testHandler.execute(ActionNames.INSERT_ITEM, parameterMap);
        System.out.println(result);
    }
}