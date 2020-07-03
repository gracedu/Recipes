package trainingProject.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingProject.ApplicationBootstrap;
import trainingProject.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    JWTService jwtService;

    @Test
    public void generateTokenTest() {
        User u = new User();
        u.setId(1L);
        u.setName("zhang");
        String token = jwtService.generateToken(u);
        Assert.assertNotNull(token);
    }
}
