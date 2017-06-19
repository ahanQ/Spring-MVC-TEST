package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "cn.myapps.**.repository", excludeFilters = @Filter(classes = EnableWebMvc.class))
public class AppConfig {

}
