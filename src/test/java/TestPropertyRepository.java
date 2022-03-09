//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

import ie.kooth.config.ConfigH2;
import ie.kooth.dao.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigH2.class)
public class TestPropertyRepository {

    @Autowired
    PropertyRepository propertyRepository;

    @Test
    public void testDeleteProperty(){
        int count = propertyRepository.deleteProperty("T15C80");
        assertEquals(1, count);
    }

}
