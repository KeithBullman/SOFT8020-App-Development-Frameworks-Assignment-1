//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.services;

import ie.kooth.classes.Tenant;
import java.util.List;

public interface TenantService {
    List<Tenant> findTenantByEirCode(String eirCode);
    boolean exists(String name, String email, String phoneNumber);
    int addTenant(String name, String email, String phoneNumber, String eirCode);
    int deleteTenant(String name, String email, String eirCode);
    int changeTenantProperty(String name, String email, String phoneNumber, String oldEircode, String newEircode);
}
