package com.lyoyang.lab.common.annotation;

import com.lyoyang.lab.common.selector.LabCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LabCloudApplicationSelector.class)
public @interface LabCloudApplication {
}
