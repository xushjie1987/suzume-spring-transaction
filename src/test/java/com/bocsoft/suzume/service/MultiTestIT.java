package com.bocsoft.suzume.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:multiTransaction.xml")
public class MultiTestIT {
    
    @Autowired
    private TestService  testService;
    
    @Autowired
    private InnerService innerService;
    
    /**
     * 测试Service内部方法之间直接调用，不会走事务切面，因为是对象内部方法之间的调用，因此达不到效果 <br>
     */
    @Test
    public void test01() {
        testService.requiredSuccess01();
    }
    
    /**
     * 测试Service之间事务切面包装的方法之间调用 <br>
     * RuntimeException异常即可回滚 <br>
     */
    @Test
    public void test02() {
        testService.requiredSuccess02();
    }
    
    /**
     * 必检Exception异常不回滚 <br>
     */
    @Test
    public void test03() {
        testService.requiredSuccess03();
    }
    
    /**
     * 正常回滚 <br>
     * console无异常信息 <br>
     * junit测试失败 <br>
     * junit测试有异常信息 <br>
     * org.springframework.transaction.UnexpectedRollbackException: <br>
     * Transaction rolled back because it has been marked as rollback-only <br>
     */
    @Test
    public void case_01() {
        testService.case_01();
    }
    
    /**
     * testService正常提交 <br>
     * innerService正常回滚 <br>
     * console无异常 <br>
     * junit测试成功 <br>
     */
    @Test
    public void case_02() {
        testService.case_02();
    }
    
    /**
     * 回滚 <br>
     * TransactionInterceptor事务层的传播性为：PROPAGATION_REQUIRED <br>
     * 符合预期 <br>
     */
    @Test
    public void multi_01() {
        testService.multi_01();
    }
    
    /**
     * 部分提交 <br>
     * TransactionInterceptor事务层的传播性为：PROPAGATION_REQUIRES_NEW <br>
     * 符合预期 <br>
     */
    @Test
    public void multi_02() {
        testService.multi_02();
    }
    
}
