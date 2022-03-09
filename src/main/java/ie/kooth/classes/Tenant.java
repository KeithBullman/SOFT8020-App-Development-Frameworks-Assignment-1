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

public class Tenant {
    private int tenantId;
    private String name;
    private String email;
    private String phoneNumber;
    private String tenantEirCode;
}
