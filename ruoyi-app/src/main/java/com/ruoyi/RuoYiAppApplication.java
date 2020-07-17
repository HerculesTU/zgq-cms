package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.ruoyi.**.mapper")
public class RuoYiAppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiAppApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  网站APP后台启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}