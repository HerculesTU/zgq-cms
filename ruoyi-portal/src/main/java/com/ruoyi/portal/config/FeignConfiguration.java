package com.ruoyi.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FeignConfiguration {
    @Bean(name="remoteRestTemplate")
    public RestTemplate RestTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(3500);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
