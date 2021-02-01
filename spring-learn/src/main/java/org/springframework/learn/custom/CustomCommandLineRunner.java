package org.springframework.learn.custom;

/**
 * 这个接口也只有一个方法：run(String… args)，触发时机为整个springboot项目启动完毕后，自动执行。
 * 如果有多个CommandLineRunner，可以利用@Order来进行排序。
 *
 * 扩展此接口，进行启动项目之后一些业务的预处理。
 * @author chenwei
 * @version CustomCommandLineRunner, v2.0 2021/2/1 15:11
 **/
//public class CustomCommandLineRunner implements CommandLineRunner{
//	@Override
//	public void run(String... args)throws Exception{
//		System.out.println("[CustomCommandLineRunner]");
//	}
//}