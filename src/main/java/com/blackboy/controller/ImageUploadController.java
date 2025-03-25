package com.blackboy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.blackboy.controller.util.Result;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ImageUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    // 从 application.yml 中读取上传路径
    @Value("${file.upload.path}")
    private String uploadPath;

    @PostMapping("/upload/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(false, "未选择文件");
        }

        try {
            // 生成唯一的文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileExtension;

            // 创建上传目录
            System.out.println("uploadPath:" + uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    logger.info("上传目录创建成功: {}", uploadDir.getAbsolutePath());
                } else {
                    logger.error("上传目录创建失败: {}", uploadDir.getAbsolutePath());
                    return new Result(false, "上传目录创建失败");
                }
            }

            // 保存文件
            File dest = new File(uploadDir, newFileName);
            file.transferTo(dest);

            return new Result(true, "上传成功", newFileName);
        } catch (IOException e) {
            logger.error("文件上传失败", e);
            return new Result(false, "上传失败");
        }
    }

    // 获取用户头像图片，转换为Base64再传送
    @GetMapping("/getUserAvatar/{avatarId}")
    public Result getUserAvatar(@PathVariable String avatarId) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(avatarId);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                byte[] imageBytes = Files.readAllBytes(filePath);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                return new Result(true, "获取成功", base64Image);
            } else {
                return new Result(false, "图片不存在", null);
            }
        } catch (IOException e) {
            logger.error("获取图片失败", e);
            return new Result(false, "获取图片失败", null);
        }
    }
}