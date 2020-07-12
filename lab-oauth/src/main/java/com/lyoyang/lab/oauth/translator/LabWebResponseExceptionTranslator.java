package com.lyoyang.lab.oauth.translator;

import com.lyoyang.lab.common.entity.ServiceResponse;
import com.lyoyang.lab.common.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LabWebResponseExceptionTranslator implements WebResponseExceptionTranslator {


    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("认证失败", e);
        if (e instanceof UnsupportedGrantTypeException) {
            return responseEntity.body(ServiceResponse.getServiceResponse(ResponseEnum.UNSUPPORTED_GRANT_TYPE));
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                return responseEntity.body(ServiceResponse.getServiceResponse(ResponseEnum.INVALID_REFRESH_TOKEN));
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                return responseEntity.body(ServiceResponse.getServiceResponse(ResponseEnum.USER_LOKED));
            }
            return responseEntity.body(ServiceResponse.getServiceResponse(ResponseEnum.USERNAME_PASSWORD_ERROR));
        }
        return responseEntity.body(ServiceResponse.getServiceResponse(ResponseEnum.AUTHENTICATION_FAILURE));
    }
}
