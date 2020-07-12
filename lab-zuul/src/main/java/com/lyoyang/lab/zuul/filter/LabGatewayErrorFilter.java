package com.lyoyang.lab.zuul.filter;

import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.utils.LabUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LabGatewayErrorFilter extends SendErrorFilter {


    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
            ExceptionHolder zuulException = findZuulException(ctx.getThrowable());
            String errorCause = zuulException.getErrorCause();
            Throwable throwable = zuulException.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isEmpty(message) ? errorCause : message;
            ServiceResponse serviceResponse = resolveExceptionMessage(message, serviceId);
            HttpServletResponse response = ctx.getResponse();
            LabUtil.createResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, serviceResponse);
        } catch (Exception e) {
            log.error("zuul send error:{}", e);
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }



    private ServiceResponse resolveExceptionMessage(String message, String serviceId) {
        ServiceResponse failInstance = ServiceResponse.getFailInstance();
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            failInstance.setMessage("请求" + serviceId + "服务超时");
        } else if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            failInstance.setMessage(serviceId + "服务不可用");
        } else {
            failInstance.setMessage("Zuul请求" + serviceId + "服务异常");
        }
        return failInstance;
    }
}
