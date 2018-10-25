package org.woo.service.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static HashMap<String, Class> serviceRegister = new HashMap<String, Class>();

    public void register(Class serviceInterface, Class impl) {
        serviceRegister.put(serviceInterface.getName(), impl);
    }

    public void start(int port) throws IOException {

        final ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("服务已启动");
        while (true) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    ObjectInputStream inputStream = null;
                    ObjectOutputStream objectOutputStream = null;

                    try {
                        socket = serverSocket.accept();
                        inputStream = new ObjectInputStream(socket.getInputStream());
                        String serviceName = inputStream.readUTF();
                        String methodName = inputStream.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                        Object[] arguments = (Object[]) inputStream.readObject();
                        Class serviceClass = serviceRegister.get(serviceName);
                        if (serviceClass == null) {
                            throw new ClassNotFoundException(serviceName + "未找到");
                        }
                        Method method = serviceClass.getMethod(methodName, parameterTypes);
                        Object result = method.invoke(serviceClass.newInstance(), arguments);
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (socket != null) {
                                socket.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
