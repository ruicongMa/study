package cn.rpc;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mark
 * @date 2020/11/1 17:46
 */
public class PublishService {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish(Object service, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(new ServiceHandle(service, socket));
        }
    }
}
