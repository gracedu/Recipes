package trainingProject.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingProject.ApplicationBootstrap;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS sqsClient;


//    @After
//    public void tearDown() {
//        reset(sqsClient);
//    }

    @Test
    public void getQueueUrlTest() {

        messageService.getQueueUrl("123");
        verify(sqsClient, times(1)).getQueueUrl("123");
    }

    @Test
    public void sendMessageTest() {
        messageService.sendMessage("Hello World!", 1);
        //         Assert.assertTrue(false);
    }
}
