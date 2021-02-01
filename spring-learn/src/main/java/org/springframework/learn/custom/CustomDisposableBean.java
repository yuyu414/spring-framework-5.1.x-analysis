package org.springframework.learn.custom;

import org.springframework.beans.factory.DisposableBean;

/**
 * @author chenwei
 * @version CustomDisposableBean, v2.0 2021/2/1 15:15
 **/
public class CustomDisposableBean implements DisposableBean {

	/**
	 * 其触发时机为当此对象销毁时，会自动执行这个方法。
	 * 比如说运行applicationContext.registerShutdownHook时，就会触发这个方法。
	 */
	@Override
	public void destroy() throws Exception{
		System.out.println("[DisposableBean] CustomDisposableBean");
	}
}