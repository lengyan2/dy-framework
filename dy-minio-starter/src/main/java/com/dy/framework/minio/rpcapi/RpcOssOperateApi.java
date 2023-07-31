package com.dy.framework.minio.rpcapi;

import com.dy.framework.minio.dto.OssProcessDTO;
import com.dy.framework.web.model.response.ApiResult;

/**
 * RPC调用接口
 *
 * @author daiyuanjing
 * @date 2023-07-31
 */
public interface RpcOssOperateApi {

    // 创建bucket

    /**
     * 创建bucket
     * @param ossProcessDTO oss处理dto
     * @return 返回ApiResult响应
     */
    ApiResult createBucket(OssProcessDTO ossProcessDTO);

    // 删除bucket

    /**
     * 删除bucket
     * @param ossProcessDTO oss处理dto
     * @return 返回ApiResult响应
     */
    ApiResult deleteBucket(OssProcessDTO ossProcessDTO);

    // 上传文件到bucket

    /**
     * 上传文件到bucket
     * @param ossProcessDTO oss处理dto
     * @return 返回ApiResult响应
     */
    ApiResult upload(OssProcessDTO ossProcessDTO);

    // 下载文件到本地

    /**
     * 下载文件到本地
     * @param ossProcessDTO oss处理dto
     * @return 返回ApiResult响应
     */
    ApiResult download(OssProcessDTO ossProcessDTO);
    // 删除文件

    /**
     * 删除文件
     * @param ossProcessDTO oss处理dto
     * @return 返回ApiResult响应
     */
    ApiResult delete(OssProcessDTO ossProcessDTO);

    // 获取文件外链
    /**
     * 获取文件外链
     * @param ossProcessDTO oss处理dto
     * @return 返回ApiResult响应
     */
    ApiResult FileLink(OssProcessDTO ossProcessDTO);

}
