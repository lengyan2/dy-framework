package com.dy.framework.dubbo.filter;

import com.dy.framework.core.context.TenantContext;
import com.dy.framework.core.context.TenantContextHolder;
import com.dy.framework.core.context.UserContext;
import com.dy.framework.core.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * 自定义dubbo提供值上下文
 * @author daiyuanjing
 * @date 2023-07-21
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class ProviderContextFilter implements Filter {

    /**
     * 从dubbo附件中取出用户上下文 租户上下文字段
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContextAttachment serverAttachment = RpcContext.getServerAttachment();
        if (serverAttachment.isProviderSide()){
            // 清理数据 避免线程池复用残留
            UserContextHolder.clear();
            TenantContextHolder.clear();

            Object objectAttachment = serverAttachment.getObjectAttachment(UserContext.CAMEL_NAME);
            if (objectAttachment instanceof  UserContext){
                UserContext userContext = (UserContext) objectAttachment;
                UserContextHolder.setUserContext(userContext);
                log.debug("[dubbo RPC] 取出当前用户上下文 》》 {}", userContext);
            }
             objectAttachment = serverAttachment.getObjectAttachment(TenantContext.CAMEL_NAME);
            if (objectAttachment instanceof  TenantContext){
                TenantContext tenantContext = (TenantContext) objectAttachment;
                TenantContextHolder.setTenantContext(tenantContext);
                log.debug("[dubbo RPC] 取出当前租户上下文 >> {}",tenantContext);
            }

        }
        return invoker.invoke(invocation);
    }
}
