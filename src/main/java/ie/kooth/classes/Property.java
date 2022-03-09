//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Property {
    private int propertyId;
    private String eirCode;
    private int maxCapacity;
    private int currentCapacity;
    private int costPerTenantPerMonth;
}
