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
 * dubbo 自定义消费者上下文
 * @author  daiyuanjing
 * @date  2023-07-21
 */
@Slf4j
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerContextFilter implements Filter {

    /**
     * 设置附件 用户上下文和租户上下文
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContextAttachment clientAttachment = RpcContext.getClientAttachment();
        if (clientAttachment.isConsumerSide()){
            UserContext userContext = UserContextHolder.getUserContext();
            if (userContext!= null){
                log.debug("[dubbo Rpc] 设置用户上下文 》》 {}",userContext);
                clientAttachment.setAttachment(UserContext.CAMEL_NAME,userContext);
            }
            TenantContext tenantContext = TenantContextHolder.getTenantContext();
            if (tenantContext != null && tenantContext.getTenantId() != null){
                log.debug("[dubbo RPC] 设置租户上下文 >> {}",tenantContext);
                clientAttachment.setAttachment(TenantContext.CAMEL_NAME,tenantContext);
            }
        }
        return invoker.invoke(invocation);
    }
}
