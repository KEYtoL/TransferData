package com.yx.config;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import com.yx.config.configEntity.SqlserverConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/7/29
 */
@Configuration
@MapperScan(basePackages = "com.yx.mapper.sqlservermapper", sqlSessionTemplateRef  = "sqlserverSqlSessionTemplate")
public class SQLDataSourceConfig {
    @Bean(name="sqlserverConfig")
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver")
    @Primary
    public SqlserverConfig sqlserverConfig(){
        return new SqlserverConfig();
    }
    @Bean(name = "sqlserverDataSource")
    @Primary
    public DataSource testDataSource(@Qualifier("sqlserverConfig") SqlserverConfig sqlserverConfig) throws SQLException {
        SQLServerXADataSource SqlserverXaDataSource = new SQLServerXADataSource();

        SqlserverXaDataSource.setURL(sqlserverConfig.getUrl());
        SqlserverXaDataSource.setPassword(sqlserverConfig.getPassword());
        SqlserverXaDataSource.setUser(sqlserverConfig.getUsername());
        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(SqlserverXaDataSource);
        xaDataSource.setUniqueResourceName("sqlserverDataSource");
        xaDataSource.setMinPoolSize(sqlserverConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(sqlserverConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(sqlserverConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(sqlserverConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(sqlserverConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(sqlserverConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(sqlserverConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(sqlserverConfig.getTestQuery());
        return xaDataSource;

    }
    @Bean(name = "sqlserverSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("sqlserverDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper1/*.xml"));
        return bean.getObject();
    }
//    @Bean(name = "sqlserverTransactionManager")
//    @Primary
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("sqlserverDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
    @Bean(name = "sqlserverSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlserverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }




}
