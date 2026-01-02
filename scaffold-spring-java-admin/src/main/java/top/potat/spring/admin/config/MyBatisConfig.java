package top.potat.spring.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.potat.spring.common.config.datasource.DataDruidConfig;

@Configuration
@EnableTransactionManagement
@MapperScan({
        "top.potat.spring.db.mapper",
        "top.potat.spring.db.model",
        "top.potat.spring.admin.dao",
        "top.potat.spring.service.dao"
})
public class MyBatisConfig extends DataDruidConfig {

}
