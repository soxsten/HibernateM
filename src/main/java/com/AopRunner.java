package com;

import com.util.DataUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AopRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DataUtil util = context.getBean(DataUtil.class);
        util.task21();
        util.task24();
        util.task2223();
    }
}
