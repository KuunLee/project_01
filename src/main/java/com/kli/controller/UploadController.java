package com.kli.controller;

import com.kli.pojo.Result;
import com.kli.util.AliOSSUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Resource
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) {
        String netPath;
        log.info("文件上传:{}", image.getOriginalFilename());
        try {
            netPath = aliOSSUtils.upload(image);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("上传文件失败");
        }
        return Result.success(netPath);
    }
}
