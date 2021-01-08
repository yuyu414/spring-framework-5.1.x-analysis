package org.springframework.learn;


public class UserServiceImpl implements UserService {

    @Override
    public void sayHello() {
    	System.out.println("Hello spring!");
    }
}