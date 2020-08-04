package trainProject.consumer.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MyListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Received: " + ((TextMessage)message).getText());
        }
        catch(JMSException e) {
            logger.error("unable to receive message");
            e.printStackTrace();
        }
    }
}
