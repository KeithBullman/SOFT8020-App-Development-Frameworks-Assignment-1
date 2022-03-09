//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

import ie.kooth.config.ConfigH2;
import ie.kooth.dao.TenantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigH2.class)
public class TestTenantRepository {

    @Autowired
    TenantRepository tenantRepository;

    @Test
    public void testInsertTenant(){
        int result = tenantRepository.addTenant("Jennifer", "jennifer@gmail.com", "58271", "T13C60");
        assertEquals(result, 1);
    }

}
