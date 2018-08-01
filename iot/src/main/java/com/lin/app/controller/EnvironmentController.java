package com.lin.app.controller;

import com.lin.app.constant.ErrorCode;
import com.lin.app.dto.RestResponse;
import com.lin.app.exception.EnvironmentCtlException;
import com.lin.app.service.EnvironmentService;
import com.lin.app.utils.OSSClientUtil;
import com.lin.app.utils.WsPool;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * EnvController
 *
 * @author linzhongsheng
 */

@RestController
@RequestMapping(value = "/iot")
public class EnvironmentController {
    private Logger logger = LoggerFactory.getLogger(EnvironmentController.class);

    @Autowired
    private EnvironmentService environmentService;

    @RequestMapping("send-command")
    public RestResponse sendCommand(@RequestParam(value = "mechine_id") String mechineId,
                                    @RequestParam(value = "type") String type,
                                    @RequestParam(value = "command") String command) {
        logger.info("mechine id:" + mechineId);

        try {
            boolean result = environmentService.sendCommand(mechineId, type, command);
            if (result) {
                return RestResponse.success("指令发送成功");
            } else {
                return RestResponse.failure(ErrorCode.NOT_FOUND,"客户端不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return RestResponse.failure(ErrorCode.SYSTEM_ERROR);
        }
    }


    @RequestMapping("save-data")
    public RestResponse saveData(@RequestParam(value = "data") String data) {
        try {
            String path = environmentService.saveData(data);
            logger.info("data:" + data + ",path:" + path);
            return RestResponse.success(path);
        } catch (EnvironmentCtlException e) {
            e.printStackTrace();
            return RestResponse.failure(ErrorCode.SYSTEM_ERROR, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.failure(ErrorCode.SYSTEM_ERROR);
        }
    }


    @RequestMapping("demo")
    public void demo() {
        System.out.println("demo");
    }

}
