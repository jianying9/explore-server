package com.explore.user;

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
public class InquirePointServiceImplJUnitTest extends AbstractExploreTest {

    public InquirePointServiceImplJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    //

    @Test
    public void testInqurePoint() {
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        String result = this.testHandler.execute(ActionNames.INQUIRE_POINT, parameterMap);
        System.out.println(result);
    }
}