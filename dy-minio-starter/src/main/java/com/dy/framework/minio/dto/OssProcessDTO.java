package com.dy.framework.minio.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Oss处理dto
 *
 * @author  daiyuanjing
 * @date 2023-07-31
 */
@Data
@ApiModel("Oss处理dto")
public class OssProcessDTO {

    @ApiModelProperty("上传的文件路径")
    private String filePath;

    @ApiModelProperty("上传到哪个桶")
    private String bucketName;

    @ApiModelProperty("上传的文件名")
    private String fileName;
}
