package com.lin.app.utils;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

/**
 * WebScoketClient
 *
 * @author linzhongsheng
 */

public class WebSocketServerUtils extends WebSocketServer {

    public WebSocketServerUtils(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //断开连接时候触发代码
        userLeave(conn);
        System.out.println(reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        //ws连接的时候触发的代码
        System.out.println("message:" + message);
        String userName = message;//用户名
        userJoin(conn, userName);//用户加入
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        //错误时候触发的代码
        System.out.println("on error");
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
    }

    /**
     * 去除掉失效的websocket链接
     * @param conn
     */
    private void userLeave(WebSocket conn){
        WsPool.removeUser(conn);
    }
    /**
     * 将websocket加入用户池
     * @param conn
     * @param userName
     */
    private void userJoin(WebSocket conn,String userName){
        WsPool.addUser(userName, conn);
    }


    public static void main(String args[]){
        WebSocketImpl.DEBUG = false;
        int port = 8887; // 端口
        WebSocketServerUtils s = new WebSocketServerUtils(port);
        s.start();

        while(true) {
            WsPool.sendMessageToAll("hello");
        }
    }
}
