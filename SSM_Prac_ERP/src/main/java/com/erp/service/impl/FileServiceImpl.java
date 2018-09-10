package com.erp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bockey
 */
@Service
public class FileServiceImpl {

    static String filePath = "D:\\untitled\\upload\\temp\\file\\";
    static String picPath = "D:\\untitled\\upload\\temp\\pic\\";

    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (file != null && file.getOriginalFilename() != null && file.getOriginalFilename().length() > 0) {
                //生成一个新的文件名
                //取原始文件名
                String fileName = file.getOriginalFilename();
                //String date = new DateTime().toString("yyyy-MM-dd");
                //生成新文件名,防止重名
                //UUID.randomUUID();
                //String newName = oldName.substring(0,oldName.lastIndexOf("."))+"("+date+")"+oldName.substring(oldName.lastIndexOf("."));

                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //新文件
                File f1 = new File(filePath + fileName);
                file.transferTo(f1);
                //将内存中的文件写入磁盘
                //图片上传成功后，将图片的地址写回
                resultMap.put("error", 0);
                resultMap.put("url", "/file/" + f1.getName());
                return resultMap;
            } else {
                //返回结果
                resultMap.put("error", 1);
                resultMap.put("message", "文件异常");
                return resultMap;
            }
        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }


    }

    public void deleteFile(String fileName) {
        File f2 = new File(filePath + fileName);
        f2.exists();
        f2.delete(); //del file
        f2 = null;
    }
}
