package com.example.druidflywaydemo.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.druidflywaydemo.autoconfigure.properties.DruidStatProperties;
import com.example.druidflywaydemo.autoconfigure.stat.DruidFilterConfiguration;
import com.example.druidflywaydemo.autoconfigure.stat.DruidSpringAopConfiguration;
import com.example.druidflywaydemo.autoconfigure.stat.DruidStatViewServletConfiguration;
import com.example.druidflywaydemo.autoconfigure.stat.DruidWebStatFilterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/4/4 9:07
 * @description：
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class,
        DruidStatViewServletConfiguration.class,
        DruidWebStatFilterConfiguration.class,
        DruidFilterConfiguration.class})
public class DruidDataSourceAutoConfigure {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSourceAutoConfigure.class);

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        LOGGER.info("Init DruidDataSource");
        return new DruidDataSourceWrapper();
    }


}
