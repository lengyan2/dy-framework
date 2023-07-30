package com.dy.framework.minio.service;

import com.dy.framework.minio.config.MinioProperties;
import com.dy.framework.web.model.response.ApiResult;
import com.jvm123.minio.service.MinioFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * minio http 服务
 *
 * @author daiyuanjing
 * @date 2023-07-31
 */
@Slf4j
@Service
@AllArgsConstructor
public class MinioHttpService {

    private final MinioFileService minioFileService;

    private final MinioProperties minioProperties;

    /**
     * 创建ossBucket
     *
     * @param bucketName 桶名称
     * @return 返回ApiResult响应
     */
    public ApiResult createBucket(String bucketName) {
        return minioFileService.createBucket(bucketName) ? ApiResult.success("创建bucket: " + bucketName + " 成功") : ApiResult.fail(500, "创建oss存储桶失败");
    }

    /**
     * 删除对应的bucket
     *
     * @param bucketName 桶名称
     * @return 返回ApiResult响应
     */
    public ApiResult removeBucket(String bucketName) {
        return minioFileService.deleteBucket(bucketName) ? ApiResult.success("删除bucket: " + bucketName + " 成功") : ApiResult.fail(500, "删除oss存储桶失败");
    }

    /**
     * 上传文件到对应的桶
     *
     * @param bucketName 桶名称
     * @param file       文件
     * @return 返回ApiResult响应
     */
    public ApiResult uploadFile(String bucketName, MultipartFile file) {
        String fileName = null;
        try {
            fileName = minioFileService.save(bucketName, file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            log.error("upload the file error", e);
            return ApiResult.fail(500, "upload the file error");
        }
        return ApiResult.data("上传成功", fileName);
    }

    /**
     * 上传文件到对应的桶
     *
     * @param bucketName 桶名称
     * @param file       文件
     * @return 返回文件名
     */
    public String upload(String bucketName, MultipartFile file) {
        String fileName = null;
        try {
            fileName = minioFileService.save(bucketName, file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            log.error("upload the file error", e);
            return "upload the file error";
        }
        return fileName;
    }

    /**
     * 删除桶中的对应文件
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称
     * @return 返回ApiResult响应
     */
    public ApiResult deleteFile(String bucketName, String fileName) {
        return minioFileService.delete(bucketName, fileName) ? ApiResult.success(fileName + " 删除成功") : ApiResult.fail(500, fileName + " 删除文件失败");
    }


    /**
     * 下载对应桶中的文件
     *
     * @param response   响应
     * @param bucketName 桶名称
     * @param fileName   文件名称
     */
    public void download(HttpServletResponse response, String bucketName, String fileName) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            ServletOutputStream outputStream = response.getOutputStream();
            minioFileService.writeTo(bucketName, fileName, outputStream);
        } catch (IOException e) {
            log.error("download file is Failure", e);
        }
    }

    /**
     * 批量删除文件
     *
     * @param bucketName 桶名称
     * @param fileNames  文件名称
     * @return 返回ApiResult响应
     */
    public ApiResult deleteFiles(String bucketName, List<String> fileNames) {
        List<String> errorFileList = new ArrayList<>();
        // 批量删除 如果有一个报错则添加进errorFileList
        fileNames.stream().forEach(fileName -> {
            if (!minioFileService.delete(bucketName, fileName)) {
                errorFileList.add(fileName);
            }
        });
        return errorFileList.size() == 0 ? ApiResult.success("删除成功") : ApiResult.fail(500, "删除失败，无法删除文件为", errorFileList);
    }

    /**
     * 获取文件的外链
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称
     * @return 返回文件外链
     */
    public String fileLink(String bucketName, String fileName) {
        return minioProperties.getEndpoint() + "/" + bucketName + "/" + fileName;
    }

    /**
     * 获取文件的外链
     *
     * @param bucketName
     * @param fileName
     * @return
     */
    public ApiResult fileLinkApi(String bucketName, String fileName) {
        return ApiResult.data(fileLink(bucketName, fileName));
    }
}
