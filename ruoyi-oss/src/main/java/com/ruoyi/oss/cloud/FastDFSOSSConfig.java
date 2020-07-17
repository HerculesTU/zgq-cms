package com.ruoyi.oss.cloud;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author 作者 gf
 * @version 创建时间：2019年8月30日 下午15:52:36
 * FastDFS配置
 */
@Configuration
@Import(FdfsClientConfig.class)
// Jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDFSOSSConfig {

}
