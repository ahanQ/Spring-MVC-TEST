package com.teemlink.base.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Configure {

  /**
   * 实际上为mysql的{@link DataSource} TODO 需要修改
   * 
   * @return
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(
        "jdbc:mysql://localhost:3307/spring?useUnicode=true&characterEncoding=utf8", "root", "");
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return dataSource;
  }

  /**
   * {@link AbstractTransactionalJUnit4SpringContextTests}需要在 Spring 上下文环境中加载
   * JdbcTemplate
   * 
   * @param dataSource
   * @return
   */
  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  /**
   * {@link AbstractTransactionalJUnit4SpringContextTests}需要在 Spring 上下文环境中加载
   * {@link PlatformTransactionManager}
   * 
   * @param dataSource
   * @return
   */
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
