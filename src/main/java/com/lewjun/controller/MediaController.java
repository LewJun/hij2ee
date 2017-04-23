/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * 素材管理
 * @author LewJun
 * @version $Id: MediaController.java, v 0.1 2017年4月21日 上午11:51:52 LewJun Exp $
 */
@Controller
@RequestMapping("/media")
public class MediaController extends ApplicationBaseController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartHttpServletRequest request) throws Exception {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        List<MediaModel> mediaModels = new ArrayList<MediaModel>();
        if (fileMap != null && fileMap.size() != 0) {
            for (Map.Entry<String, MultipartFile> me : fileMap.entrySet()) {
                MultipartFile mf = me.getValue();
                String filename = mf.getOriginalFilename();
                long filesize = mf.getSize();

                String contentType = mf.getContentType();
                byte[] fileBytes = mf.getBytes();
                InputStream is = mf.getInputStream();

                // 保存到磁盘
                mf.transferTo(new File("E:/springmvcUpload/" + filename));

                MediaModel mm = new MediaModel();
                mm.setCode(UUID.randomUUID().toString());
                mm.setId(72);
                mm.setFilename(filename);
                mm.setFilepath("/file/path");
                mm.setFilesize(filesize);
                mm.setContentType(contentType);

                mediaModels.add(mm);
            }
        }

        final String result = JSON.toJSONString(mediaModels);
        LOGGER.info("【result={}】", result);
        return result;
    }

    /**
     * <p>采用file.Transto 来保存上传的文件
     * <p>通过获取的字节数组和字节流来保存上传的文件
     */
    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws Exception {

        MediaModel mm = new MediaModel();
        if (file != null && !file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] fileBytes = file.getBytes();
            InputStream is = file.getInputStream();
            long filesize = file.getSize();

            file.transferTo(new File("E:/springmvcUpload/" + filename));

            mm.setCode(UUID.randomUUID().toString());
            mm.setId(72);
            mm.setFilename(filename);
            mm.setFilepath("/file/path");
            mm.setFilesize(filesize);
            mm.setContentType(contentType);
        }
        final String result = JSON.toJSONString(mm);
        LOGGER.info("【result={}】", result);
        return result;
    }

	/**
	 * 推荐使用这种方式
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("download1")
	public static void download1(HttpServletResponse response) throws Exception {
		String fileName = "惠.jpg";
		// 设置响应头和客户端保存文件名
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));// 解决中文乱码

		// 打开本地文件流
		InputStream is = new FileInputStream("E:/Camera/IMG_20170122_054915.jpg");
		// 激活下载操作
		OutputStream os = response.getOutputStream();
//
//		// 循环写入输出流
//		byte[] bytes = new byte[1024];
//		int length = 0;
//		while ((length = is.read(bytes)) > 0) {
//			os.write(bytes, 0, length);
//		}
//
//		// 这里主要关闭。
//		os.close();
//		is.close();
		
		IOUtils.copy(is, os);
		
		IOUtils.closeQuietly(is);
		IOUtils.closeQuietly(os);
	}

	@RequestMapping("/download2")
	public ResponseEntity<byte[]> download2() throws IOException {
		String fileName = "惠.jpg";
		File file = new File("E:/Camera/IMG_20170122_054915.jpg");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		return entity;
	}
}

class MediaModel {
    private Serializable id;

    private String       code;

    private String       filename;

    private String       filepath;

    private long         filesize;

    private String       contentType;

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "MediaModel [id=" + id + ", code=" + code + ", filename=" + filename + ", filepath="
               + filepath + ", filesize=" + filesize + ", contentType=" + contentType + "]";
    }

}