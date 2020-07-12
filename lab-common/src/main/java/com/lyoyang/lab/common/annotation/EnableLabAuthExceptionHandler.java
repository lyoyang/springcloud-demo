package com.lyoyang.lab.common.annotation;


import com.lyoyang.lab.common.config.LabAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LabAuthExceptionConfigure.class)
public @interface EnableLabAuthExceptionHandler {
}
