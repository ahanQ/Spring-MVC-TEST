package com.teemlink.base.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class Configure {

  @Bean
  public DataSource dataSource() {
    return new DriverManagerDataSource(
        "jdbc:mysql://localhost:3307/spring?useUnicode=true&characterEncoding=utf8", "root", "");
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
