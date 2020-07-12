package com.lyoyang.lab.zuul.filter;

import com.lyoyang.lab.common.constant.LabServerConstant;
import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.utils.LabUtil;
import com.lyoyang.lab.zuul.properties.LabGatewayProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LabGatewayRequestFilter extends ZuulFilter {

    @Autowired
    private LabGatewayProperties labGatewayProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();



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
        // 禁止外部访问资源实现
        boolean shouldForward = true;
        String forbidRequestUri = labGatewayProperties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }

        if (!shouldForward) {
            HttpServletResponse response = currentContext.getResponse();
            try {
                LabUtil.createResponse(
                        response, MediaType.APPLICATION_JSON_VALUE,
                        HttpServletResponse.SC_FORBIDDEN, ServiceResponse.getExceptionResponse("该URI不允许外部访问")
                );
                currentContext.setSendZuulResponse(false);
                currentContext.setResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        byte[] token = Base64Utils.encode(LabServerConstant.ZUUL_TOKEN_VALUE.getBytes());
        currentContext.addZuulRequestHeader(LabServerConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
