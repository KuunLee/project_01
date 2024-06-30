package com.kli.controller;

import com.kli.pojo.Result;
import com.kli.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

     @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image){
//
//        log.info("文件上传:{},{},{}",username,age,image);
//        //uuid作为文件名
//        UUID uuid = UUID.randomUUID();
//        //获取到文件全名之后，根据最后1个小数点进行截取，拿到文件后缀
//        String objectName = image.getOriginalFilename();
//        int i = objectName.lastIndexOf(".");
//        String fileType = objectName.substring(i);
//        String fileUrl = "E:\\images\\" + objectName;
//        try {
//            ALiYunUploadUtil.upload(objectName,fileUrl);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return Result.error("上传文件失败");
//        }
//        return Result.success(netUrl);
//    }

    public Result upload(MultipartFile image){
        log.info("文件上传:{}",image);
        try {
            String netPath = aliOSSUtils.upload(image);
            return Result.success(netPath);
        }
        catch (Exception e){
            e.printStackTrace();
            return Result.error("上传文件失败");
        }
     }
}
