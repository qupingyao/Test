package proxy.staticProxy;

/**
 * 代理类
 */
public class AProxy implements AInterface {

	private AInterface target;

	public AProxy(AInterface target) {
		this.target = target;
	}

	@Override
	public void say(String word) {
		System.out.println("proxy method say start");
		target.say(word);
		System.out.println("proxy method say end");
	}

	@Override
	public void cal(int num) {
		System.out.println("proxy method cal start");
		target.cal(num);
		System.out.println("proxy method cal end");
	}

	@Override
	public AInterface getObject() {
		System.out.println("proxy method getObject start");
		AInterface object = target.getObject();
		System.out.println("proxy method getObject end");
		return this;
	}
}
