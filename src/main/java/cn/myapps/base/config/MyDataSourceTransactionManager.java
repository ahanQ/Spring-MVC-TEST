package cn.myapps.base.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class MyDataSourceTransactionManager extends DataSourceTransactionManager {

  private static final long serialVersionUID = 3200837253573142045L;

  public MyDataSourceTransactionManager() {
    setDataSource(DataSources.dataSource);
  }

}
