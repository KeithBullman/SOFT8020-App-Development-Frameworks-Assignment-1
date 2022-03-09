//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

import ie.kooth.config.ConfigH2;
import ie.kooth.services.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigH2.class)
public class TestPropertyService {

    @Autowired
    PropertyService propertyService;

    @Test
    public void testPropertyExists(){
        boolean result = propertyService.propertyExists("T13C60");
        assertTrue(result);
    }

}
