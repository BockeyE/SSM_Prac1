package com.erp.controller;

import com.erp.service.impl.PicServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author bockey
 */
@Controller
public class PicController {

    @Autowired
    PicServive serv;

    @RequestMapping("/pic")
    @ResponseBody
    public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
        Map<String, Object> map = serv.uploadPicture(uploadFile);
        return map;
    }

}
