/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.controller;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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