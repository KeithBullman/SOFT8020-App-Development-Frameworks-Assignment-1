//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.services;

import ie.kooth.classes.Property;
import ie.kooth.dao.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class PropertyServiceImplementation implements PropertyService{

    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public Property findProperty(String eirCode) {
        Property returnedProperty = propertyRepository.findProperty(eirCode);
        if(returnedProperty == null){
            log.error("There is no property with Eircode '" + eirCode + "' in the database.");
        }
        return returnedProperty;
    }

    @Override
    public List<Property> findAllProperties() {
        return propertyRepository.findAllProperties();
    }

    @Override
    public List<Property> findPropertiesWithSpace() {
        return propertyRepository.findPropertiesWithSpace();
    }

    @Override
    public boolean propertyExists(String eirCode){
        return propertyRepository.propertyExists(eirCode);
    }

    @Override
    public int addProperty(String eirCode, int maxCapacity, int costPerTenantPerMonth) {
        if(!propertyRepository.propertyExists(eirCode)){
            System.out.println("New Property Added!\n");
            return propertyRepository.addProperty(eirCode, maxCapacity, costPerTenantPerMonth);

        }
        log.error("Property with Eircode '" + eirCode + "' is already in the database.\n");
        return 0;
    }

    @Override
    public int deleteProperty(String eirCode) {
        int numberDeleted = propertyRepository.deleteProperty(eirCode);
        if(numberDeleted == 0){
            log.error("Deletion failed; no property with Eircode '" + eirCode + "' found in database.\n");
        }
        else{
            System.out.println("Deletion of Property Successful\n");
        }
        return numberDeleted;
    }

    @Override
    public Float averageAmountOfTenants() {
        return propertyRepository.averageAmountOfTenants();
    }

    @Override
    public Float totalIncome() {
        return propertyRepository.totalIncome();
    }

    @Override
    public int totalReachedCapacity() {
        return propertyRepository.totalReachedCapacity();
    }
}
