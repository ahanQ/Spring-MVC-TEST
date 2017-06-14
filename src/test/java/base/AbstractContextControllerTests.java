package base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import spring.config.AppConfig;
import spring.config.WebConfig;

@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration(classes = { AppConfig.class, WebConfig.class })
public abstract class AbstractContextControllerTests {
  @Autowired
  protected WebApplicationContext webApplicationContext;
}
