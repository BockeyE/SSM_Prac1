package com.erp.controller;

import com.erp.service.impl.FileServiceImpl;
import com.erp.util.DownLoadFileUtil;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author bockey
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileServiceImpl serv;

    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile file) {
        Map<String, Object> map = null;
        // Iterator<String> fileNames = req.getFileNames();
        System.out.println("==============enter file upload==================");
        //   String next = fileNames.next();
        //   for (MultipartFile file : files) {
        map = serv.uploadFile(file);
        //    }
        // MultipartFile file = req.getFile(next);
        System.out.println(1);
        return map;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map handleFileDelete( String fileName) {
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        serv.deleteFile(fileName);
        Map<String, Object> result = new HashMap<>();
        result.put("data", "success");
        return result;
    }

    @RequestMapping(value = "/download")
    public void downLoad(String fileName, HttpServletResponse response) {
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        String filePath = "D:\\untitled\\upload\\temp\\file\\" + fileName;
        try {
            DownLoadFileUtil.download(filePath, fileName, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
