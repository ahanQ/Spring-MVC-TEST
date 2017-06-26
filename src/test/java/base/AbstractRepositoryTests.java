package base;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import spring.config.AppConfig;

/**
 * 启动了事务的 Repository 类的测试抽象类，因为不需要测试 Controller，只需要加载 {@link AppConfig}
 * 类的配置。<br/>
 * 需要测试的 Repository 类可以直接从此类继承
 * 
 * @author ahan
 *
 */
@ContextConfiguration(classes = { AppConfig.class })
public abstract class AbstractRepositoryTests
    extends AbstractTransactionalJUnit4SpringContextTests {

}
