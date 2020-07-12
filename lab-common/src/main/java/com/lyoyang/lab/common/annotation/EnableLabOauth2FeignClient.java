package com.lyoyang.lab.common.annotation;

import com.lyoyang.lab.common.config.LabOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LabOAuth2FeignConfigure.class)
public @interface EnableLabOauth2FeignClient {

}
