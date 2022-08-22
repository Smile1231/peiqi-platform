package com.sap.peiqiplatform.entity.bo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName ShellProperties.java
 * @Description TODO
 * @createTime 2022-08-22  23:53:00
 */
@Component
@Data
@ConfigurationProperties(prefix = "shell")
public class ShellProperties {
    private String deployFile;
}
