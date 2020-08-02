package trainProject.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import trainProject.consumer.Service.SQSMessageService;

@SpringBootApplication(scanBasePackages = {"trainProject.consumer"})
public class ConsumerApplication {
    public static void main(String[] args) {
        //SpringApplication.run(ConsumerApplication.class, args);

        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApplication.class, args);
        SQSMessageService sqsMessageService = app.getBean(SQSMessageService.class);
        sqsMessageService.receiveMessage();
    }
}
