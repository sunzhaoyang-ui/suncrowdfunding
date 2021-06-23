package com.sun.crowd.test;

import com.sun.crowd.util.CrowdUtil;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void testMd5(){
        String source = "123123";
        String encoded = CrowdUtil.md5(source);
        System.out.println(encoded);
    }
}
