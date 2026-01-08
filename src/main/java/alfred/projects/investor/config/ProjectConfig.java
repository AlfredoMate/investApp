package alfred.projects.investor.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients (
        basePackages = "alfred.projects"
)
public class ProjectConfig {
}
