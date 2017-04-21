import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

/**
 * 
 * @author LewJun
 * @version $Id: MediaTest.java, v 0.1 2017年4月21日 下午6:32:49 LewJun Exp $
 */
public class MediaTest {

    /**
     * 使用HttpURLConnection上传文件
     * 
     * @param requestUrl
     * @param file
     * @return
     */
    public String upload(String requestUrl, File file) {
        InputStream input = null;
        OutputStream output = null;
        String returnValue = null;
        // 定义数据分割符
        String boundary = "------Boundary" + System.currentTimeMillis();
        try {
            URL uploadUrl = new URL(requestUrl);
            HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod("POST");
            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type",
                "multipart/form-data;boundary=" + boundary);
            // 获取媒体文件上传的输出流
            output = uploadConn.getOutputStream();

            StringBuilder outputSb = new StringBuilder("");
            // 请求体开始
            outputSb.append("--" + boundary + "\r\n")
                // 设置文件名
                .append(String.format(
                    "Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n",
                    file.getName()))
                // 从请求头中获取内容类型
                .append("Content-Type: application/octet-stream\r\n\r\n");

            output.write(outputSb.toString().getBytes());
            // 获取媒体文件的输入流（读取文件）
            input = new FileInputStream(file);
            IOUtils.copy(input, output);
            // 请求体结束
            output.write(("\r\n--" + boundary + "--\r\n").getBytes());
            // 获取媒体文件上传的输入流（从微信服务器读数据）
            input = uploadConn.getInputStream();
            returnValue = new String(IOUtils.toByteArray(input));

        } catch (Exception e) {
            //LOGGER.error("【error:】",e);
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
        return returnValue;
    }

    @Test
    public void testUploadFile() {
        String requestUrl = "http://127.0.0.1:8080/hij2ee/media/upload";
        File file = new File("d:/net.png");
        String returnValue = upload(requestUrl, file);
        System.out.println(returnValue);
    }
}
