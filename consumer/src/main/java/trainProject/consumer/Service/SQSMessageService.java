package trainProject.consumer.Service;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

@Deprecated
@Service
@Component
public class SQSMessageService {
    @Value("${aws.queue.name}")
    private String queueName;

    @Autowired
    private AmazonSQS sqsClient;


    public void receiveMessage()  {
        System.out.println("Receiving messages from MyQueue.\n");
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(getQueueUrl(queueName));
        final List<Message> msg = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
        for (final Message message : msg) {
            System.out.println("Message");
            System.out.println("  MessageId:     " + message.getMessageId());
            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("  Body:          " + message.getBody());
            Map<String,String> messageAttributes = message.getAttributes();
            for (final Map.Entry<String, String> entry : messageAttributes.entrySet()) {
                System.out.println("Attribute");
                System.out.println("  Name:  " + entry.getKey());
                System.out.println("  Value: " + entry.getValue());
            }
            sqsClient.deleteMessage(getQueueUrl(queueName), message.getReceiptHandle());
        }
        System.out.println();
    }


    public String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = sqsClient.getQueueUrl(queueName);
        // logger.info("QueueUrl: " + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

    public static void main(String[] args) throws JMSException, InterruptedException {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                sqsClient
        );

        SQSConnection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);//or AUTO_ACKNOWLEDGE

        MessageConsumer consumer = session.createConsumer(session.createQueue(System.getProperty("aws.queue.name")));
        MyListener processService = new MyListener();
        consumer.setMessageListener(processService);
        connection.start();
        System.out.println("connection start");
        Thread.sleep(1000);


        //connection.stop();

    }
}
