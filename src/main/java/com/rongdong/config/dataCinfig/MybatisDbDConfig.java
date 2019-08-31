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
@MapperScan(basePackages = MybatisDbDConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory4")
public class MybatisDbDConfig {
    public static final String PACKAGE = "com.rongdong.dao.fourDataSource";
    public static final String MAPPER_LOCATION = "classpath:mapping/fourDataSource/*.xml";

    @Autowired
    @Qualifier("fourDataSource")
    private DataSource ds4;

    @Bean
    public SqlSessionFactory sqlSessionFactory4() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds4);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisDbDConfig.MAPPER_LOCATION);
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
    @Bean(name="fourDataSourceTransactionManager")
    public DataSourceTransactionManager fourDataSourceTransactionManager(){
        return new DataSourceTransactionManager(ds4);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate4() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory4());
    }
}
