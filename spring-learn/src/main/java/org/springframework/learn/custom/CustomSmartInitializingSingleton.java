package org.springframework.learn.custom;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * 这个接口中只有一个方法afterSingletonsInstantiated，
 * 其作用是是 在spring容器管理的所有单例对象（非懒加载对象）初始化完成之后调用的回调接口。
 * 其触发时机为postProcessAfterInitialization之后。
 *
 * @author chenwei
 * @version CustomSmartInitializingSingleton, v2.0 2021/2/1 15:08
 **/
public class CustomSmartInitializingSingleton implements SmartInitializingSingleton {

	/**
	 * 扩展此接口在对所有单例对象初始化完毕后，做一些后置的业务处理。
	 */
	@Override
	public void afterSingletonsInstantiated(){
		System.out.println("[CustomSmartInitializingSingleton]");
	}
}
