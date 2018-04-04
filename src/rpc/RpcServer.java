package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class RpcServer {

	private static HashMap<Class, Class> localMap = new HashMap<Class, Class>();

	private static final String serverHost = "127.0.0.1";

	private static ArrayList<Integer> portListenList = new ArrayList<Integer>();

	public static void publish(Class interfaceClass, Class implClass, int port) {
		boolean isListen = portListenList.contains(port);
		localMap.put(interfaceClass, implClass);
		RegisterCenterInterface registerCenterInterface = (RegisterCenterInterface) Proxy.newProxyInstance(
				RegisterCenterInterface.class.getClassLoader(), new Class<?>[] { RegisterCenterInterface.class },
				new RegisterInvocationHandler(RegisterCenter.registerCenterHost, RegisterCenter.registerCenterPort));
		registerCenterInterface.register(interfaceClass, serverHost, port);
		if (!isListen) {
			portListenList.add(port);
			new Thread(new Runnable() {
				@Override
				public void run() {
					ServerSocket server = null;
					try {
						server = new ServerSocket(port);
						while (true) {
							final Socket socket = server.accept();
							new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
										ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
										try {
											Class interfaceClass = (Class) input.readObject();
											String methodName = (String) input.readObject();
											Class<?>[] methodParameterTypes = (Class<?>[]) input.readObject();
											Object[] args = (Object[]) input.readObject();
											try {
												Class implClass = localMap.get(interfaceClass);
												if (implClass == null) {
													throw new ClassNotFoundException(
															interfaceClass + " is not publish");
												}
												Method implMethod = implClass.getMethod(methodName,
														methodParameterTypes);
												Object result = implMethod.invoke(implClass.newInstance(), args);
												output.writeObject(result);
											} catch (Throwable t) {
												output.writeObject(t);
											}
										} finally {
											if (input != null) {
												input.close();
											}
											if (output != null) {
												output.close();
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										try {
											socket.close();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}).start();
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (server != null) {
								server.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		System.out.println("server publish success, interfaceClass:" + interfaceClass.getName() + ", server-addr->"
				+ serverHost + ":" + port);
	}

}
