package load;

/**
 * Exm1类在加载时,jvm为保证Float是Number的子类不得不先装载这两个类,但不初始化
 */
public class Exm1 {

	private Number number;

	public Exm1(Float f) {
		number = f;
	}

	public static void main(String[] args) {

	}

}
