package com.lin.app.service.impl;

import com.lin.app.exception.EnvironmentCtlException;
import com.lin.app.service.EnvironmentService;
import com.lin.app.utils.OSSClientUtil;
import com.lin.app.utils.WsPool;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * EnvironmentServiceImpl
 *
 * @author linzhongsheng
 */
@Service
public class EnvironmentServiceImpl implements EnvironmentService {
    private Logger logger = LoggerFactory.getLogger(EnvironmentServiceImpl.class);

    @Override
    public boolean sendCommand(String mechineId, String type, String command) {
//        WebSocket conn = WsPool.getWsByUser(clientName);
//        if (conn == null) {
//            return false;
//        }

        WsPool.sendMessageToAll(mechineId + "," + type + "," + command);
        //WsPool.sendMessageToUser(conn, type + "," + command);
        return true;
    }


    @Override
    public String saveData(String data) {
        try {
            String filePath = OSSClientUtil.uploadContent(data);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new EnvironmentCtlException("数据保存失败，请稍后重试。");
        }
    }
}
