package com.lyoyang.lab.gateway.handler;

import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Brian
 * @Date: 2020/7/15 18:37
 * @Description:
 */
@Slf4j
public class LabGatewayExceptionHandler extends DefaultErrorWebExceptionHandler {


    public LabGatewayExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = super.getError(request);
        log.error("request exception, request URI:{}, method:{}, exception trace:{}",
                request.path(), request.method(), error.getMessage());

        String erroeMsg;
        if (error instanceof NotFoundException) {
            String serverId = StringUtils.substringAfter(error.getMessage(), "Unable to find instance for ");
            serverId = StringUtils.replace(serverId, "\"", StringUtils.EMPTY);
            erroeMsg = String.format("unable to find %s service", serverId);
        } else if (StringUtils.containsIgnoreCase(error.getMessage(), "connection refused")) {
            erroeMsg = "connection refused";
        } else if (error instanceof TimeoutException) {
            erroeMsg = "request time out";
        } else if (error instanceof ResponseStatusException && StringUtils.containsIgnoreCase(error.getMessage(), HttpStatus.NOT_FOUND.toString())) {
            erroeMsg = "not found resource";
        } else if (error instanceof ParamFlowException) {
            erroeMsg = "访问频率超限";
        } else {
            erroeMsg = "gateway forwarding exception";
        }
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("message", erroeMsg);
        return errorAttributes;
    }

    @SuppressWarnings("all")
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }


    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
