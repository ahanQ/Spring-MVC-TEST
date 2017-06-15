package base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.config.AppConfig;
import spring.config.WebConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, WebConfig.class })
public abstract class AbstractContextControllerTests
    extends AbstractTransactionalJUnit4SpringContextTests {
}
