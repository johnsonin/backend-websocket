package com.lin.app.utils;

import com.lin.app.runner.WebSocketClientRunner;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;

/**
 * WsClient
 *
 * @author linzhongsheng
 */
public class WebSocketClientUtils extends WebSocketClient {

    public WebSocketClientUtils(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public WebSocketClientUtils(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("连接成功");
        WebSocketClientRunner.heartbeat = 1;
        this.send(String.valueOf(UUID.randomUUID()));
    }

    @Override
    public void onMessage(String message) {
        System.out.println("收到消息" + message);
    }

    @Override
    public void onError(Exception e) {
        //e.printStackTrace();
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        WebSocketClientRunner.heartbeat = 0;
//        System.out.println("连接已关闭,状态为:" + this.getReadyState());
//        while(!this.getReadyState().equals(READYSTATE.OPEN)) {
//            System.out.println("等待连接中...");
//            try {
//                WebSocketClientUtils client = new WebSocketClientUtils(new URI("ws://localhost:8887"),new Draft_17());
//                client.connect();
//                client.send("mymy");
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        try {
            System.out.println(new String(bytes.array(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
