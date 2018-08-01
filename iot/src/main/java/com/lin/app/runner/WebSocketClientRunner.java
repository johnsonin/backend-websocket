package com.lin.app.runner;

import com.lin.app.utils.WebSocketClientUtils;
import org.java_websocket.drafts.Draft_17;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * WebSocketClient
 *
 * @author linzhongsheng
 */
@Component
public class WebSocketClientRunner implements CommandLineRunner {

    // 0：未连接，1：已连接，2：连接中
    public static Integer heartbeat = 0;

    @Override
    public void run(String... strings) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

        scheduledThreadPool.scheduleAtFixedRate(() -> {
            try {
                System.out.println("心跳检测:" + ((heartbeat == 0)?"未连接":"已连接或连接中"));
                if (heartbeat == 0) {
                    connect();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }, 1, 5, TimeUnit.SECONDS);
    }

    private void connect() throws Exception {
        heartbeat = 2;
        WebSocketClientUtils client = new WebSocketClientUtils(new URI("ws://localhost:8887"), new Draft_17(), null, 0);
        //WebSocketClientUtils client = new WebSocketClientUtils(new URI("ws://172.18.90.10:8887"), new Draft_17(), null, 0);
        client.connectBlocking();
    }
}
