package base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.config.AppConfig;
import spring.config.WebConfig;

/**
 * 启动了事务的 Controller 类的测试抽象类，需要加载 {@link AppConfig} 和 {@link WebConfig}
 * 类的配置。<br/>
 * 测试 Controller 需要加载 Servlet 上下文，因此添加了{@link WebAppConfiguration}注解<br/>
 * 需要测试的 Controller 类可以直接从此类继承
 * 
 * @author ahan
 *
 */
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, WebConfig.class })
public abstract class AbstractControllerTests
    extends AbstractTransactionalJUnit4SpringContextTests {
}
