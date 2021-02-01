package org.springframework.learn.custom;

import org.springframework.beans.factory.InitializingBean;
 
/**
 * 初始化相关
 * @author chenwei
 * @version TestBean, v2.0 2019/12/13 9:19
 **/
public class CustomBean implements InitializingBean {

    public CustomBean() {
        System.out.println("=====================构造方法执行========================");
    }

	/**
	 * @PostConstruct这个并不算一个扩展点
	 * 其作用是在bean的初始化阶段，如果对一个方法标注了@PostConstruct，会先调用这个方法。
	 * 这里重点是要关注下这个触发点是在postProcessBeforeInitialization之后，InitializingBean.afterPropertiesSet之前。
	 *
	 * 使用场景：用户可以对某一方法进行标注，来进行初始化某一个属性
	 */
	//@PostConstruct
	public void postConstruct(){
		System.out.println("[PostConstruct] CustomBean");
	}

	/**
	 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，
	 * 凡是继承该接口的类，在初始化bean的时候都会执行该方法。
	 * 这个扩展点的触发时机在postProcessAfterInitialization之前。
	 *
	 * 使用场景：用户实现此接口，来进行系统启动的时候一些业务指标的初始化工作。
	 */
	@Override
	public void afterPropertiesSet() {
		System.out.println("[InitializingBean] CustomBean");
	}
}