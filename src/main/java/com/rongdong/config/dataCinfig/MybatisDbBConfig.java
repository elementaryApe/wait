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
@MapperScan(basePackages = MybatisDbBConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisDbBConfig {
    public static final String PACKAGE = "com.rongdong.dao.secondDataSource";
    public static final String MAPPER_LOCATION = "classpath:mapping/secondDataSource/*.xml";

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource ds2;

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisDbBConfig.MAPPER_LOCATION);
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
    @Bean(name="secondDataSourceTransactionManager")
    public DataSourceTransactionManager secondDataSourceTransactionManager(){
        return new DataSourceTransactionManager(ds2);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
}
