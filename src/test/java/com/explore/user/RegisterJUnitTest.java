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
public class RegisterJUnitTest extends AbstractExploreTest {

    public RegisterJUnitTest() {
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
        Map<String, String> parameterMap = new HashMap<String, String>(2, 1);
        parameterMap.put("nickName", "aladdin");
        parameterMap.put("userEmail", "aladdin@explore.com");
        parameterMap.put("password", "670b14728ad9902aecba32e22fa4f6bd");
        String result = this.testHandler.execute(ActionNames.REGISTER, parameterMap);
        System.out.println(result);
    }
}