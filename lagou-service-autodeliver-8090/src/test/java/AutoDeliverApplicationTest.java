import com.lagou.edu.AutoDeliverApplication8090;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author Jaa
 * @Date 2022/5/9 23:21
 * @Description
 */
@SpringBootTest(classes = {AutoDeliverApplication8090.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoDeliverApplicationTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void testInstanceMetadata() {
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");
        for (int i = 0; i < instances.size(); i++) {
            ServiceInstance serviceInstance = instances.get(i);
            System.out.println(serviceInstance);
        }
    }
}
