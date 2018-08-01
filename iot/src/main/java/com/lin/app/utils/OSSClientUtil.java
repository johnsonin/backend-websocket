package com.lin.app.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * OSS工具类
 */
public class OSSClientUtil {

    private final static Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);

    // endpoint
    private static String endpoint;
    // AK
    private static String accessKeyId;
    // SK
    private static String accessKeySecret;
    // 空间
    private static String bucketName;
    // 文件存储目录
    private static String dirPath;

    private static OSSClient ossClient;

    /**
     * 字符串上传
     * @return
     * @throws IOException
     */
    public static String uploadContent(String content) throws IOException {
        //filename
        Random random = new Random();
        String name = System.currentTimeMillis() + "" + random.nextInt(1000000);

        //日期路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date()) + "/";

        //filename
        String fileName = dirPath + date + name;
        PutObjectResult putResult = ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content.getBytes()));
        return getUrl(fileName);
    }

    /**
     * 获得文件路径
     */
    private static String getUrl(String file) {
        return "https://" + bucketName + "." + endpoint + "/" + file;
    }

    /**
     * 读取配置文件
     */
    static {
        InputStream in = null;
        try {
            in = OSSClientUtil.class.getClassLoader().getResourceAsStream("config/oss-config.properties");

            Properties p = new Properties();
            p.load(new InputStreamReader(in, "utf-8"));

            endpoint = p.getProperty("endpoint");
            accessKeyId = p.getProperty("accessKeyId");
            accessKeySecret = p.getProperty("accessKeySecret");
            bucketName = p.getProperty("bucketName");
            dirPath = p.getProperty("dirPath");

            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("oss-config.properties文件流关闭出现异常:" + e.toString());
            }
        }
    }


    public static void main(String[] args) {
        try {
            new OSSClientUtil().uploadContent("林忠生 hello world 林忠生");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
