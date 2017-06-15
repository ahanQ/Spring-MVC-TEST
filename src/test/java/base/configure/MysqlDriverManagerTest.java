package base.configure;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

import spring.config.AppConfig;

@ContextConfiguration(classes = { AppConfig.class })
public class MysqlDriverManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  @Test
  public void dataSource() {
    Assert.assertNotNull(dataSource);
  }

  @Test
  public void jdbcTemplate() {
    Assert.assertNotNull(jdbcTemplate);
  }

  @Test
  public void platformTransactionManager() {
    Assert.assertNotNull(platformTransactionManager);
  }

}
