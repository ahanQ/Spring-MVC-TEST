package base.configure;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

import base.AbstractRepositoryTests;

/**
 * {@link AbstractTransactionalJUnit4SpringContextTests} 需要在 Spring 上下文环境中加载
 * {@link JdbcTemplate} 和 {@link PlatformTransactionManager}
 * 
 * @author ahan
 *
 */
public final class MysqlDriverManagerTest extends AbstractRepositoryTests {

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
