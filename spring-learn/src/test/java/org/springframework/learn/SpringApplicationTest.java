package org.springframework.learn;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationTest {

    public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		//自定义监听器
		applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("自定义事件监听器~~~~~。要放在refresh()，之前哦");
			}
		});
        applicationContext.register(UserServiceImpl.class, //
				//自定义属性编辑器配置类,它实现了BeanFactoryPostProcessor接口
				CustomEditorConfigurer.class);//注册
        applicationContext.refresh();
        UserService userService = applicationContext.getBean(UserService.class);
        userService.sayHello();
    }
}