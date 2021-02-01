package org.springframework.learn.custom;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 这个类也是Aware扩展的一种，触发点在bean的初始化之前，
 * 也就是postProcessBeforeInitialization之前，这个类的触发点方法只有一个：setBeanName
 *
 * @author chenwei
 * @version CustomApplicationContextAwareProcessor, v2.0 2021/2/1 14:30
 **/
public class CustomBeanNameAware implements BeanNameAware {

	/**
	 * 用户可以扩展这个点，在初始化bean之前拿到spring容器中注册的的beanName，
	 * 来自行修改这个beanName的值。
	 */

	@Override
	public void setBeanName(String name){
		System.out.println("[BeanNameAware] "+ name);
	}
}
