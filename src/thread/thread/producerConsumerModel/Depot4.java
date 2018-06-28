package thread.thread.producerConsumerModel;

/**官方队列拷贝+官方代码拷贝版本**/
public class Depot4 extends Depot{

	public Depot4(int maxNum) {
		super.myBlockingQueue = new MyLinkedBlockingQueue1<Thing>(maxNum);
	}
	
	@Override
	public void product(String productorName, Thing thing) {
		myBlockingQueue.put(thing);
		System.out.println(productorName + " product, thingId: " + thing.getId() + ", remain:" + myBlockingQueue.size());
	}
	
	@Override
	public void consume(String consumerName) {
		Thing thing = myBlockingQueue.take();
		System.out.println(consumerName + " consume, thingId: " + thing.getId() + ", remain: " + myBlockingQueue.size());
	}
	
	@Override
	public void add(Thing thing){
		myBlockingQueue.add(thing);
	}

	@Override
	public int getProductorNotEmptySignalCount() {
		return myBlockingQueue.getProductorNotEmptySignalCount();
	}

	@Override
	public int getConsumerNotEmptySignalCount() {
		return myBlockingQueue.getConsumerNotEmptySignalCount();
	}

	@Override
	public int getProductorNotFullSignalCount() {
		return myBlockingQueue.getProductorNotFullSignalCount();
	}

	@Override
	public int getConsumerNotFullSignalCount() {
		return myBlockingQueue.getConsumerNotFullSignalCount();
	}
	
	@Override
	public void clear(){
		myBlockingQueue.clear();
	}
	
	@Override
	public int getCount(){
		return myBlockingQueue.size();
	}
	
}