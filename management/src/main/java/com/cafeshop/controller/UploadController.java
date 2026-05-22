package com.cafeshop.controller;


import com.cafeshop.Result.Result;
import com.cafeshop.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UploadController {
    private final UploadService uploadService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String url = uploadService.upload(file);
        return Result.success(url, "圖片上傳成功");
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String imageUrl) {
        uploadService.deleteByUrl(imageUrl);
        return Result.success(null, "刪除成功");
    }
}
