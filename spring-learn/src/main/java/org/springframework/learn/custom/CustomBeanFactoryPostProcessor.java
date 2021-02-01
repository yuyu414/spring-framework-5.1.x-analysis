package org.springframework.learn.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * beanFactory的扩展接口，调用时机在spring在读取beanDefinition信息之后，实例化bean之前
 * @author chenwei
 * @version CustomBeanFactoryPostProcessor, v2.0 2021/2/1 11:42
 **/
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	/**
	 * 用户可以通过实现这个扩展接口来自行处理一些东西，比如修改已经注册的beanDefinition的元信息。
	 * @throws BeansException
	 */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[BeanFactoryPostProcessor]");
    }

}
