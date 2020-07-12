package com.lyoyang.lab.common.annotation;

import com.lyoyang.lab.common.config.LabServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LabServerProtectConfigure.class)
public @interface EnableLabServerProtect {
}
