//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.dao;

import ie.kooth.classes.Property;
import java.util.List;

public interface PropertyRepository {
    Property findProperty(String eirCode);
    List<Property> findAllProperties();
    List<Property> findPropertiesWithSpace();
    boolean propertyExists(String eirCode);
    int addProperty(String eirCode, int maxCapacity, int costPerTenantPerMonth);
    int deleteProperty(String eirCode);
    int updateCurrentCapacity(String eirCode);
    int lowerCurrentCapacity(String eirCode);
    Float averageAmountOfTenants();
    Float totalIncome();
    int totalReachedCapacity();
}
