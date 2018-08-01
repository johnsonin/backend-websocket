package com.lin.app.service;

import com.lin.app.exception.EnvironmentCtlException;

/**
 * EnvironmentService
 *
 * @author linzhongsheng
 */
public interface EnvironmentService {

    boolean sendCommand(String clientName, String type, String command) throws EnvironmentCtlException;

    String saveData(String data) throws EnvironmentCtlException;

}
