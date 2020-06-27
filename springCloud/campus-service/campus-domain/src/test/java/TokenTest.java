import com.campus.DomainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.xml.ws.Holder;
import java.security.SecureRandom;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DomainApplication.class)
public class TokenTest {

    @Test
    public void getToken(){

        UUID uuid=UUID.randomUUID();
        System.out.println(uuid);


    }






}
