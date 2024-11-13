package com.CCMe.Configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ApplicationProperties {
    private List<String> allowedOrigins;
    private String baseUrl;
    private String applicationName;
    private String loginPageUrl;
}
