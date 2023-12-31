package com.dy.framework.core.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 分页查询参数
 *
 * @author daiyuanjing
 * @Date 2023-07-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageParam implements Serializable {

    /**
     * 全局分页页码限制函数
     */
    public static Function<Integer,Integer> globalPageNumLimiter = null;

    /**
     * 全局分页大小限制函数
     */
    public static Function<Integer,Integer> globalPageSizeLimiter = null;

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;

    public Integer getPageNum(){
        if (pageNum == null){
            pageNum = 1;
        }
        if (globalPageNumLimiter != null){
            pageNum = globalPageNumLimiter.apply(pageNum);
        }
        return pageNum;
    }

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize;

    public Integer getPageSize(){
        if (pageSize == null){
            pageSize = 10;
        }
        if (globalPageSizeLimiter != null){
            pageSize = globalPageSizeLimiter.apply(pageSize);
        }
        return pageSize;
    }
}
