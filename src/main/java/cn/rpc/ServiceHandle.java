package cn.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author Mark
 * @date 2020/11/1 17:51
 */
public class ServiceHandle implements Runnable {

    private Object service;
    private Socket socket;

    public ServiceHandle(Object service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) ois.readObject();
            Method method = service.getClass().getMethod(request.getMethodName(), request.getTypes());
            Object result = method.invoke(service, request.getArgs());
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
