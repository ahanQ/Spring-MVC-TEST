package cn.myapps.base.config;

import javax.sql.DataSource;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSources {
  public static DriverManagerDataSource dataSource1;

  public static DriverManagerDataSource dataSource2;

  public static DataSource dataSource;
  static {
    dataSource1 = new DriverManagerDataSource(
        "jdbc:mysql://localhost:3307/spring?useUnicode=true&characterEncoding=utf8", "root", "");
    dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource2 = new DriverManagerDataSource(
        "jdbc:mysql://localhost:3307/another_spring?useUnicode=true&characterEncoding=utf8", "root",
        "");
    dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
    if (RandomUtils.nextBoolean()) {
      System.out.println("dataSource1: " + DataSources.dataSource1);
      dataSource = DataSources.dataSource1;
    } else {
      System.out.println("dataSource2: " + DataSources.dataSource2);
      dataSource = DataSources.dataSource2;
    }

  }

}
