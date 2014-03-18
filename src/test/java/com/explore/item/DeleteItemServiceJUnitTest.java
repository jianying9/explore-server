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
public class DeleteItemServiceJUnitTest extends AbstractExploreTest {

    public DeleteItemServiceJUnitTest() {
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
        parameterMap.put("itemId", "100001");
        String result = this.testHandler.execute(ActionNames.DELETE_ITEM, parameterMap);
        System.out.println(result);
    }
}