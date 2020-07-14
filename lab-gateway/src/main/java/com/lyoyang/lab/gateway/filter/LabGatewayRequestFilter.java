package com.lyoyang.lab.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.lyoyang.lab.common.constant.LabServerConstant;
import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.gateway.properties.LabGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * @author: Brian
 * @Date: 2020/7/14 14:00
 * @Description:
 */
@Slf4j
@Component
public class LabGatewayRequestFilter implements GlobalFilter {

    @Autowired
    private LabGatewayProperties labGatewayProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        Mono<Void> checkForbidResult = checkForbidUri(request, response);
        if (checkForbidResult != null) {
            return checkForbidResult;
        }
        printLog(exchange);
        byte[] token = Base64Utils.encode(LabServerConstant.ZUUL_TOKEN_VALUE.getBytes());
        request.mutate().header(LabServerConstant.ZUUL_TOKEN_HEADER, new String(token));
        ServerWebExchange newExchange = exchange.mutate().request(request).build();
        return chain.filter(newExchange);
    }

    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = labGatewayProperties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String fUri : forbidRequestUris) {
                if (antPathMatcher.match(fUri, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            return makeResponse(response, ServiceResponse.getExceptionResponse("该URI不允许外部访问"));
        }
        return null;
    }


    private Mono<Void> makeResponse(ServerHttpResponse response, ServiceResponse serviceResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(serviceResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }


    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }

    }


}
