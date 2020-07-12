package com.lyoyang.lab.common.annotation;

import com.lyoyang.lab.common.config.LabLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LabLettuceRedisConfigure.class)
public @interface EnableLabLettuceRedis {
}
