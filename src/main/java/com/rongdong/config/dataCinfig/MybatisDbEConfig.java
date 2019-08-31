package com.rongdong.config.dataCinfig;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Mybatis SqlSessionFactory多数据源配置
 *
 * @author hsh
 * @create 2018-03-21 19:48
 **/
@Configuration
@MapperScan(basePackages = MybatisDbEConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory5")
public class MybatisDbEConfig {
    public static final String PACKAGE = "com.rongdong.dao.fiveDataSource";
    public static final String MAPPER_LOCATION = "classpath:mapping/fiveDataSource/*.xml";

    @Autowired
    @Qualifier("fiveDataSource")
    private DataSource ds5;

    @Bean
    public SqlSessionFactory sqlSessionFactory5() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds5);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisDbEConfig.MAPPER_LOCATION);
        factoryBean.setMapperLocations(resource);
        //分页
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        factoryBean.setPlugins(new Interceptor[]{pageHelper});
        return factoryBean.getObject();

    }

    //数据源事务管理器
    @Bean(name="fiveDataSourceTransactionManager")
    public DataSourceTransactionManager fiveDataSourceTransactionManager(){
        return new DataSourceTransactionManager(ds5);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate5() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory5());
    }
}
