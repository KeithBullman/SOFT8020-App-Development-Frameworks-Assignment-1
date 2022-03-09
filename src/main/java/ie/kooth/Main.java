//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A
//please read README; includes how I interpreted some requirements in the project brief

package ie.kooth;

import ie.kooth.classes.Property;
import ie.kooth.classes.Tenant;
import ie.kooth.config.ConfigH2;
import ie.kooth.services.PropertyService;
import ie.kooth.services.TenantService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigH2.class);
        PropertyService propertyService = applicationContext.getBean(PropertyService.class);
        TenantService tenantService = applicationContext.getBean(TenantService.class);

        boolean exit = false;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("=== Welcome! ===\nPlease choose one of the following options:\n1: Search for house by Eircode\n2: View a list of houses\n3: View a list of houses with space in them\n4: Add a new house (ensuring unique Eircode)\n5: Add a new tenant and assign that tenant to a household (subject to capacity)\n6: Move a person from one household to another (subject to capacity)\n7: Delete a household, along with its occupants\n8: Delete a tenant, and remove them from the house in which they reside (reduce the number of occupants)\n9: Display some statistics\n10: Quit\n");

        // Loop until 10 is selected; breaking loop and exiting program
        while(!exit){
            try{
                System.out.println("");
                System.out.print("Please choose an option: ");
                int myInt = keyboard.nextInt();

                switch (myInt) {

                    // Search for a house by Eircode, listing the details of the household including all tenants
                    case 1 -> {
                        System.out.print("Enter Eircode to search: ");
                        String eirCode = keyboard.next();
                        if(propertyService.propertyExists(eirCode)) {

                            Property property = propertyService.findProperty(eirCode);
                            System.out.println("\nDetails of House with Eircode '" + eirCode + "':\nID: " + property.getPropertyId() + "\nEircode: " + property.getEirCode() + "\nMax Capacity: " + property.getMaxCapacity() + "\nCurrent Capacity: " + property.getCurrentCapacity() + "\nCost of Rent Per Tenant Per Calender Month: €" + property.getCostPerTenantPerMonth());

                            List<Tenant> tenants = tenantService.findTenantByEirCode(eirCode);
                            if(tenants.isEmpty()){
                                System.out.println("Currently no one living in this property.");
                            }
                            else {
                                System.out.println("\nDetails of Tenants Living in Property with Eircode '" + eirCode + "':");
                                for (Tenant tenant : tenants) {
                                    System.out.println("| Tenant ID: " + tenant.getTenantId() + " | Name: " + tenant.getName() + " | Email: " + tenant.getEmail() + " | Phone Number: " + tenant.getPhoneNumber() + " |\n");
                                }
                            }
                        }
                        else{
                            System.out.println("\nNo Details of House with Eircode '" + eirCode + "' found in database.");
                        }
                    }

                    // View a list of houses
                    case 2 -> {
                        List<Property> properties = propertyService.findAllProperties();
                        System.out.println("");
                        if(properties.isEmpty()){
                            System.out.println("Currently no houses in database.");
                        }
                        else {
                            for (Property property : properties) {
                                System.out.println("| Property ID: " + property.getPropertyId() + " | Eircode: " + property.getEirCode() + " | Max Capacity: " + property.getMaxCapacity() + " | Current Capacity: " + property.getCurrentCapacity() + " | Cost Per Tenant Per Month: €" + property.getCostPerTenantPerMonth() + " |\n");
                            }
                        }
                    }

                    // View a list of houses with space in them
                    case 3 -> {
                        List<Property> properties = propertyService.findPropertiesWithSpace();
                        System.out.println("");
                        if(properties.isEmpty()){
                            System.out.println("Currently no houses with space in them.");
                        }
                        else {
                            for (Property property : properties) {
                                System.out.println("| Property ID: " + property.getPropertyId() + " | Eircode: " + property.getEirCode() + " | Max Capacity: " + property.getMaxCapacity() + " | Current Capacity: " + property.getCurrentCapacity() + " | Cost Per Tenant Per Month: €" + property.getCostPerTenantPerMonth() + " |\n");
                            }
                        }
                    }

                    // Add a new house (ensuring unique Eircode)
                    case 4 -> { while(true){
                        try {
                            System.out.println("");

                            System.out.print("Please enter the new property's Eircode: ");
                            String eirCode = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter maximum capacity of new property: ");
                            int maxCap = keyboard.nextInt();

                            System.out.println("");

                            System.out.print(("Please enter cost of rental per tenant per calender month (Integer Prices Only): €"));
                            int rent = keyboard.nextInt();

                            System.out.println("");

                            System.out.println("Please review these details for the new property:\nEircode: " + eirCode + "\nMax Capacity: " + maxCap + "\nRent Per Tenant Per Month: €" + rent);
                            System.out.print("Satisfied? (Y to continue / Any other key to re-enter details): ");
                            String prompt = keyboard.next();

                            if (prompt.toUpperCase().equals("Y")) {
                                propertyService.addProperty(eirCode, maxCap, rent);
                                break;
                            }
                        }catch (Exception ex){
                            System.out.println("Please only enter integers for the maximum capacity and rent price sections!\n");
                        }
                        }
                    }

                    // Add a new tenant and assign that tenant to a household (subject to capacity)
                    case 5 -> { while(true){

                            System.out.println("");

                            System.out.print("Please enter the Tenant's name: ");
                            String name = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's email address: ");
                            String email = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's phone number: ");
                            String phoneNumber = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's Property Eircode: ");
                            String eirCode = keyboard.next();

                            System.out.println("Please review these details for the new Tenant:\nName: " + name + "\nEmail: " + email + "\nPhone Number: " + phoneNumber + "\nProperty Eircode: " + eirCode);
                            System.out.print("Satisfied? (Y to continue / Any other key to re-enter details): ");
                            String prompt = keyboard.next();

                            if (prompt.toUpperCase().equals("Y")) {
                                tenantService.addTenant(name, email, phoneNumber, eirCode);
                                break;
                            }
                        }
                    }

                    // Move a person from one household to another (subject to capacity)
                    case 6 -> { while(true){

                            System.out.println("");

                            System.out.print("Please enter the Tenant's name: ");
                            String name = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's email address: ");
                            String email = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's phone number: ");
                            String phoneNumber = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's Current Eircode: ");
                            String oldEirCode = keyboard.next();

                            System.out.println("");

                            System.out.print("Please enter the Tenant's New Eircode: ");
                            String newEirCode = keyboard.next();

                            System.out.println("Please review these details for the changing Tenant's Location:\nName: " + name + "\nEmail: " + email + "\nPhone Number: " + phoneNumber + "\nCurrent Eircode: " + oldEirCode + "\nNew Eircode: " + newEirCode);
                            System.out.print("Satisfied? (Y to continue / Any other key to re-enter details): ");
                            String prompt = keyboard.next();

                            if (prompt.toUpperCase().equals("Y")) {
                                tenantService.changeTenantProperty(name, email, phoneNumber, oldEirCode, newEirCode);
                                break;
                            }
                        }
                    }

                    // Delete a household, along with its occupants
                    case 7 -> {
                        System.out.print("Enter Eircode of Property to Delete: ");
                        String eirCode = keyboard.next();

                        System.out.println("");

                        System.out.print("Delete Property with Eircode: " + eirCode + "? (Y to proceed / Any other key to abort): ");
                        String prompt = keyboard.next();

                        if(prompt.toUpperCase().equals("Y")){
                            propertyService.deleteProperty(eirCode);
                        }
                        else{
                            System.out.println("Aborting deletion; returning to main menu\n");
                        }
                    }

                    // Delete a tenant, and remove them from the house in which they reside (reduce the number of occupants)
                    case 8 -> {
                        System.out.print("Enter Name of Tenant: ");
                        String name = keyboard.next();

                        System.out.println("");

                        System.out.print("Enter Tenant's Email: ");
                        String email = keyboard.next();

                        System.out.println("");

                        System.out.print("Enter Tenant's Eircode: ");
                        String eirCode = keyboard.next();

                        System.out.println("");

                        System.out.print("Delete Tenant '" + name + "' with Email '" + email + "' and Eircode '" + eirCode + "'? (Y to proceed / Any other key to abort): ");
                        String prompt = keyboard.next();

                        if (prompt.toUpperCase().equals("Y")) {
                            tenantService.deleteTenant(name, email, eirCode);
                        } else {
                            System.out.println("Aborting deletion; returning to main menu\n");
                        }
                    }

                    // Display some statistics
                    case 9 -> {
                        // the average number of tenants per household
                        System.out.println("Average amount of tenants per household: " + propertyService.averageAmountOfTenants());

                        // the total income from rental properties
                        System.out.println("Total income from rental properties: €" + propertyService.totalIncome());

                        // the number of houses with no space i.e. have reached capacity
                        System.out.println("Number of houses with no space i.e. have reached capacity: " + propertyService.totalReachedCapacity());
                    }

                    // Quit
                    case 10 -> {
                        exit = true;
                        System.out.println("\nThanks for using our service!");
                    }
                    default -> System.out.println("Please enter a number between 1-10\n");
                }

            }catch(Exception e){
                System.out.println("Please enter a number between 1-10\n");
                keyboard.next();
            }
        }

        System.out.println("Exiting program now...");
        //Exit program and close H2 embedded database.
        System.exit(0);

    }
}