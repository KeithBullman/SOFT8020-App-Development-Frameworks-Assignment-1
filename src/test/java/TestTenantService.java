//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

import ie.kooth.config.ConfigH2;
import ie.kooth.services.TenantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigH2.class)

public class TestTenantService {

    @Autowired
    TenantService tenantService;

    @Test
    public void testTenantExists(){
        boolean result = tenantService.exists("Keith", "kooth@gmail.com", "19572");
        assertTrue(result);
    }

}
