package com.ruoyi.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 *
 * @author wujiyue
 * @date 2018/6/6 16:07
 * 在开发中可能会有这样的情景。需要在容器启动的时候执行一些内容。比如读取配置文件，
 * 数据库连接之类的。SpringBoot给我们提供了两个接口来帮助我们实现这种需求。
 * 这两个接口分别为CommandLineRunner和ApplicationRunner。他们的执行时机为容器启动完成的时候。
 * 这两个接口中有一个run方法，我们只需要实现这个方法即可。这两个接口的不同之处在于：
 * ApplicationRunner中run方法的参数为ApplicationArguments，而CommandLineRunner接口中run方法的参数为String数组。
 * 目前我在项目中用的是ApplicationRunner。
 */

@Component
public class MyApplicationRunner implements ApplicationRunner {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) {

            System.out.println("(♥◠‿◠)ﾉﾞ  网站启动成功   ლ(´ڡ`ლ)ﾞ");

    }
}
