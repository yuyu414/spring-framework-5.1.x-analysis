/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Registers an auto proxy creator against the current {@link BeanDefinitionRegistry}
 * as appropriate based on an {@code @Enable*} annotation having {@code mode} and
 * {@code proxyTargetClass} attributes set to the correct values.
 *
 * @author Chris Beams
 * @since 3.1
 * @see org.springframework.cache.annotation.EnableCaching
 * @see org.springframework.transaction.annotation.EnableTransactionManagement
 */
public class AutoProxyRegistrar implements ImportBeanDefinitionRegistrar {

	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * Register, escalate, and configure the standard auto proxy creator (APC) against the
	 * given registry. Works by finding the nearest annotation declared on the importing
	 * {@code @Configuration} class that has both {@code mode} and {@code proxyTargetClass}
	 * attributes. If {@code mode} is set to {@code PROXY}, the APC is registered; if
	 * {@code proxyTargetClass} is set to {@code true}, then the APC is forced to use
	 * subclass (CGLIB) proxying.
	 * <p>Several {@code @Enable*} annotations expose both {@code mode} and
	 * {@code proxyTargetClass} attributes. It is important to note that most of these
	 * capabilities end up sharing a {@linkplain AopConfigUtils#AUTO_PROXY_CREATOR_BEAN_NAME
	 * single APC}. For this reason, this implementation doesn't "care" exactly which
	 * annotation it finds -- as long as it exposes the right {@code mode} and
	 * {@code proxyTargetClass} attributes, the APC can be registered and configured all
	 * the same.
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean candidateFound = false;

		// 这里面需要特别注意的是：这里是拿到所有的注解类型~~~而不是只拿@EnableAspectJAutoProxy这个类型的
		// 原因：因为mode、proxyTargetClass等属性会直接影响到代理的方式，而拥有这些属性的注解至少有： @EnableTransactionManagement、@EnableAsync、@EnableCaching等~~~~
		// 甚至还有启用AOP的注解：@EnableAspectJAutoProxy它也能设置`proxyTargetClass`这个属性的值，因此也会产生关联影响~
		Set<String> annTypes = importingClassMetadata.getAnnotationTypes();
		for (String annType : annTypes) {
			AnnotationAttributes candidate = AnnotationConfigUtils.attributesFor(importingClassMetadata, annType);
			if (candidate == null) {
				continue;
			}
			Object mode = candidate.get("mode");
			Object proxyTargetClass = candidate.get("proxyTargetClass");
			// 如果存在mode且存在proxyTargetClass属性，并且两个属性的class类型也是对的，
			if (mode != null && proxyTargetClass != null && AdviceMode.class == mode.getClass() && Boolean.class == proxyTargetClass.getClass()) {
				candidateFound = true;
				if (mode == AdviceMode.PROXY) {
					// 它主要是注册了一个`internalAutoProxyCreator`，但是若出现多次的话，这里不是覆盖的形式，而是以优先级的形式
					AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry);
					//看要不要强制使用CGLIB的方式(由此可以发现  这个属性若出现多次，是会是覆盖的形式)
					if ((Boolean) proxyTargetClass) {
						AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
						return;
					}
				}
			}
		}
		if (!candidateFound && logger.isInfoEnabled()) {
			String name = getClass().getSimpleName();
			logger.info(String.format("%s was imported but no annotations were found " +
					"having both 'mode' and 'proxyTargetClass' attributes of type " +
					"AdviceMode and boolean respectively. This means that auto proxy " +
					"creator registration and configuration may not have occurred as " +
					"intended, and components may not be proxied as expected. Check to " +
					"ensure that %s has been @Import'ed on the same class where these " +
					"annotations are declared; otherwise remove the import of %s " +
					"altogether.", name, name, name));
		}
	}

}
