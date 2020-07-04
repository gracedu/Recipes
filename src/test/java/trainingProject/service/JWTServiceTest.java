package trainingProject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        u.setName("zhangsan");
        String token = jwtService.generateToken(u);
        Assert.assertNotNull(token);

        //check the three parts: header, payload, and signature
        String[] parts = token.split("\\.");
        Assert.assertEquals(3, parts.length);

        // check claims details
        Claims claims = jwtService.decryptJwtToken(token);
        Assert.assertEquals("zhangsan", claims.getSubject());
        Assert.assertEquals(String.valueOf(1), claims.getId());
        Assert.assertEquals("Jinxia", claims.getIssuer());

    }


}
