//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.services;

import ie.kooth.classes.Tenant;
import ie.kooth.dao.PropertyRepository;
import ie.kooth.dao.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class TenantServiceImplementation implements TenantService{

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public List<Tenant> findTenantByEirCode(String eirCode) {
        return tenantRepository.findTenantByEirCode(eirCode);
    }

    @Override
    public boolean exists(String name, String email, String phoneNumber) {
        return tenantRepository.exists(name, email, phoneNumber);
    }

    @Override
    public int addTenant(String name, String email, String phoneNumber, String eirCode) {
        if(!propertyRepository.propertyExists(eirCode)){
            System.out.println("Property does not exist; aborting function.");
            return 0;
        }

        if(!tenantRepository.exists(name, email, phoneNumber)){
            if(propertyRepository.findProperty(eirCode).getMaxCapacity() >= (propertyRepository.findProperty(eirCode).getCurrentCapacity() + 1)) {
                // Increment property's current capacity by 1 when logging new tenant
                propertyRepository.updateCurrentCapacity(eirCode);
                System.out.println("Successfully added tenant into property and database!\n");
                return tenantRepository.addTenant(name, email, phoneNumber, eirCode);
            }
            else{
                System.out.println("Property with Eircode '" + eirCode + "' is already at maximum capacity; failed to add tenant into database.\n");
            }
        }
        return 0;
    }

    @Override
    public int deleteTenant(String name, String email, String eirCode) {
        int numberDeleted = tenantRepository.deleteTenant(name, email, eirCode);
        if(numberDeleted == 0){
            log.error("Deletion failed; no Tenant with Name '" + name + "' found in database.\n");
        }
        else{
            // Reduce property's current capacity by 1 when removing tenant
            propertyRepository.lowerCurrentCapacity(eirCode);
            System.out.println("Deletion of Tenant Successful\n");
        }
        return numberDeleted;
    }

    @Override
    public int changeTenantProperty(String name, String email, String phoneNumber, String oldEircode, String newEircode) {
        if(! propertyRepository.propertyExists(oldEircode)){
            log.error(oldEircode + " is not in the database.");
            return 0;
        }

        if(! propertyRepository.propertyExists(newEircode)){
            log.error(newEircode + " is not in the database.");
            return 0;
        }

        if(!tenantRepository.exists(name, email, phoneNumber)){
            log.error("Tenant with name '" + name + "' is not in the database.");
            return 0;
        }

        if(propertyRepository.findProperty(newEircode).getMaxCapacity() >= (propertyRepository.findProperty(newEircode).getCurrentCapacity() + 1)){
            // Increment new property current capacity by one when adding tenant
            propertyRepository.updateCurrentCapacity(newEircode);
            // Reduce old property current capacity by one when removing tenant
            propertyRepository.lowerCurrentCapacity(oldEircode);
            System.out.println("Tenant Move Successful!");
            return tenantRepository.changeTenantProperty(name, email, phoneNumber, oldEircode, newEircode);
        }

        else{
            log.error("Desired property is already at maximum capacity; aborting change");
            return 0;
        }
    }
}
