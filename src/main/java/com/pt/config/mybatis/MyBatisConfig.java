package com.pt.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 */
@Configuration
@EnableTransactionManagement
//自动生成的文件地址和自定义的地址
@MapperScan({"com.pt.db.mapper"})
public class MyBatisConfig {
}
