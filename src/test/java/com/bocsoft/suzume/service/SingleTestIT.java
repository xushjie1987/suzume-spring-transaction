package com.bocsoft.suzume.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:singleTransaction.xml")
public class SingleTestIT {
    
    @Autowired
    private TestService  testService;
    
    @Autowired
    private InnerService innerService;
    
    /**
     * 部分提交 <br>
     * 符合预期 <br>
     */
    @Test
    public void single_01() {
        testService.single_01();
    }
    
}
