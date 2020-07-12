package com.lyoyang.lab.common.selector;

import com.lyoyang.lab.common.config.LabAuthExceptionConfigure;
import com.lyoyang.lab.common.config.LabOAuth2FeignConfigure;
import com.lyoyang.lab.common.config.LabServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


public class LabCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[] {
                LabAuthExceptionConfigure.class.getName(),
                LabOAuth2FeignConfigure.class.getName(),
                LabServerProtectConfigure.class.getName()
        };
    }
}
