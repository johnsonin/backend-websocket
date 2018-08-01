package com.lin.iot;

import com.lin.app.utils.WebSocketClientUtils;
import org.java_websocket.drafts.Draft_17;

import java.net.URI;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * WebSocketClientTest
 *
 * @author linzhongsheng
 */
public class WebSocketClientTest {

    public static Integer heartbeat = 0;

    public static void main(String[] args) {
        Timer timer = new Timer();
        Task task = new Task();
        timer.schedule(task, new Date(), 5000);
    }

    public static void connect() throws Exception {
        heartbeat = 2;
        WebSocketClientUtils client = new WebSocketClientUtils(new URI("ws://localhost:8887"), new Draft_17(), null, 0);
        client.connectBlocking();
    }

}

class Task extends TimerTask {

    @Override
    public void run() {
        try {
            System.out.println("心跳检测:" + ((WebSocketClientTest.heartbeat == 0)?"未连接":"正在连接或已连接"));
            if (WebSocketClientTest.heartbeat == 0) {
                WebSocketClientTest.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}