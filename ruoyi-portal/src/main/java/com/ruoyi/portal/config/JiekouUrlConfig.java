package com.ruoyi.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class JiekouUrlConfig {
    @Value("${qyzc.basicjiekouurl}")
    private String basicjiekouurl;
    @Value("${qyzc.qtjiekouurl}")
    private String qtjiekouurl;

    public String getBasicjiekouurl() {
        return basicjiekouurl;
    }

    public void setBasicjiekouurl(String basicjiekouurl) {
        this.basicjiekouurl = basicjiekouurl;
    }

    public String getQtjiekouurl() {
        return qtjiekouurl;
    }

    public void setQtjiekouurl(String qtjiekouurl) {
        this.qtjiekouurl = qtjiekouurl;
    }
}
