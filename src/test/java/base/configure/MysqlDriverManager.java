package base.configure;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import base.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class MysqlDriverManager extends AbstractContextControllerTests {

  @Autowired
  private DataSource dataSource;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void dataSource() {
    Assert.assertNotNull(dataSource);
  }

  @Test
  public void jdbcTemplate() {
    Assert.assertNotNull(jdbcTemplate);
  }
}
