package org.springframework.learn.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 发生在bean的实例化之后，注入属性之前。
 * 这个类的扩展点方法为setBeanFactory，可以拿到BeanFactory这个属性。
 * @author chenwei
 * @version CustomBeanFactoryAware, v2.0 2021/2/1 14:18
 **/
public class CustomBeanFactoryAware implements BeanFactoryAware {

	/**
	 * 在bean实例化之后，但还未初始化之前，拿到BeanFactory，
	 * 在这个时候，可以对每个bean作特殊化的定制。也或者可以把BeanFactory拿到进行缓存，日后使用。
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("[TestBeanFactoryAware] "+ beanFactory.getBean(CustomBeanFactoryAware.class).getClass().getSimpleName());
	}
}