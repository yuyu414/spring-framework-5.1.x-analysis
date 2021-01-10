package org.springframework.learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(UserServiceImpl.class);//注册
        applicationContext.refresh();
        UserService userService = applicationContext.getBean(UserService.class);
        userService.sayHello();
    }
}