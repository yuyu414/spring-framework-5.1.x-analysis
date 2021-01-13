package org.springframework.learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *
 * @author chenwei
 * @version MyClassPathXmlApplicationContext, v2.0 2021/1/12 10:38
 **/
public class InitPropertySourcesMethodAnnotationConfigApplicationContextTest extends AnnotationConfigApplicationContext {

	@Override
	protected void initPropertySources() {
		super.initPropertySources();
		System.out.println("扩展initPropertySource()，向Environment中添加了一个属性值。");
		//这里添加了一个name属性到Environment里面，以方便我们在后面用到
		getEnvironment().getSystemProperties().put("name","张三");
	}

	public static void main(String[] args) {
		InitPropertySourcesMethodAnnotationConfigApplicationContextTest applicationContext = new InitPropertySourcesMethodAnnotationConfigApplicationContextTest();
		applicationContext.register(UserServiceImpl.class);//注册
		applicationContext.refresh();
		UserService userService = applicationContext.getBean(UserService.class);
		userService.sayHello();
	}
}
