package com.atguigu.spring5.test;

import com.atguigu.spring5.config.TxConfig;
import com.atguigu.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TestBook {

    @Test
    public void testAccount1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }

    /**
     * 执行这个测试前要注释掉TxConfig，否则会报错：
     * expected single matching bean but found 2: transactionManager,getDataSourceTransactionManager
     */
    @Test
    public void testAccount2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }

    @Test
    public void testAccount3() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }

    //函数式风格创建对象，交给spring进行管理
    @Test
    public void testGenericApplicationContext() {
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh();
        context.registerBean("user1", User.class, () -> new User());
        //获取在spring注册的对象
//        User user = (User) context.getBean("com.atguigu.spring5.test.User");
        User user = (User) context.getBean("user1");
        System.out.println(user);
    }

}
