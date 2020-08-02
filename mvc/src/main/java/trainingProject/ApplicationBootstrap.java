package trainingProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"trainingProject"})
@ServletComponentScan(basePackages = {"trainingProject"})
@EnableCaching
public class ApplicationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }
}
