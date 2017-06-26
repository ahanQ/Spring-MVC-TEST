package cn.myapps.base.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Configure {

  @Bean
  public DataSource multiDataSource() {
    return DataSources.dataSource;
  }

  /**
   * 实际上为mysql的{@link DataSource}
   * 
   * @return
   */
  @Bean
  @Profile("mysql")
  public DataSource dataSource1() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(
        "jdbc:mysql://localhost:3307/spring?useUnicode=true&characterEncoding=utf8", "root", "");
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return dataSource;
  }

  /**
   * 实际上为mysql的{@link DataSource}
   * 
   * @return
   */
  @Bean
  @Profile("anothermysql")
  public DataSource dataSource2() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(
        "jdbc:mysql://localhost:3307/another_spring?useUnicode=true&characterEncoding=utf8", "root",
        "");
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return dataSource;
  }

  /**
   * {@link AbstractTransactionalJUnit4SpringContextTests} 需要在 Spring 上下文环境中加载
   * {@link JdbcTemplate}
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
  public PlatformTransactionManager transactionManager() {
    return new MyDataSourceTransactionManager();
  }

}
