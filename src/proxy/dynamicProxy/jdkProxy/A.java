package proxy.dynamicProxy.jdkProxy;

import proxy.dynamicProxy.jdkProxy.AInterface;

/**
 * 目标类
 */
public class A implements AInterface {

	@Override
	public void say(String word) {
		System.out.println("I say:" + word);
	}

	@Override
	public void cal(int num) {
		System.out.println("I cal:" + num);
	}

	@Override
	public A getObject() {
		System.out.println("I get Object");
		return this;
	}

}
