package com.pt.config.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "myconfig.ignore")
public class IgnoreUrlConfig {
    private List<String> urls;
}
