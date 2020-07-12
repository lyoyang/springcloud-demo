package com.lyoyang.lab.zuul.filter;

import com.lyoyang.lab.common.constant.LabServerConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class LabGatewayRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        String serviceId = (String) currentContext.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = currentContext.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("request uri:{}, request method:{}, request host:{}, serverId:{}", uri, method, host, serviceId);
        byte[] token = Base64Utils.encode(LabServerConstant.ZUUL_TOKEN_VALUE.getBytes());
        currentContext.addZuulRequestHeader(LabServerConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
