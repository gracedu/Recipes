package trainProject.consumer.Service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessService {

    @JmsListener(destination = "${aws.queue.name}")
    public void processMessage(String message) {
        System.out.println(message);
    }
}
