package com.orange.verify.adminweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class DatabaseConfig implements TransactionManagementConfigurer {

    @Resource(name = "defaultManager")
    private PlatformTransactionManager defaultManager;

    @Bean(name = "defaultManager")
    public PlatformTransactionManager defaultManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return defaultManager;
    }
}

