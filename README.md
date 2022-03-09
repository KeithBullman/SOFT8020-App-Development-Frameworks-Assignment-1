A landlord has several properties.

The landlord records the following data about each property:

Eircode
Capacity of property i.e. number of tenants it can hold
Number of tenants currently renting this property
Cost of rental per tenant per calendar month
The landlord stores the following information about each tenant:

Name
Email
Phone number
The landlord wishes you to develop a command-line menu system (not web) with the following menu of options:

Search for a house by Eircode, listing the details of the household including all tenants
View a list of houses
View a list of houses with space in them 
Add a new house (ensuring unique Eircode)
Add a new tenant and assign that tenant to a household (subject to capacity)
Move a person from one household to another (subject to capacity)
Delete a household, along with its occupants
Delete a tenant, and remove them from the house in which they reside (reduce the number of occupants)
Display some statistics*
the average number of tenants per household
the total income from rental properties
the number of houses with no space i.e. have reached capacity
Quit
No security is needed.

You must write 4 unit tests, 2 for the repository layer and 2 for the service layer.

Refer to the rubric for expected standards.

* STATISTICS --> Use SQL queries here - don't just retrieve lists and do the work in Java as I have seen done in the past - SQL has queries for these. 

Penalties
Penalties for late submission are applied as per section 4.4.2 of the Exam Regulations (see http://www.mycit.ie/examregulations (Links to an external site.)).

If you have issues that you think I should be made aware of which may cause you to be late submitting work, please contact me directly using Inbox in Canvas. 

Do not plagiarize – don’t copy code, don’t provide code, don’t work too closely with someone else on the project. Trying to be clever by taking someone else’s code and renaming classes and variables is not a good strategy and will likely result in a zero grade or worse.

If you are working on this during the reading week and need help, please contact me.

Advice
Set up your Spring project first, adding all of your dependencies.
Create a Spring Beans Configuration File, adding beans for a data source and a JdbcTemplate, injecting the data source into the latter. Also add the boilerplate code for h2 (embedded database) and a web server so that you can view the embedded database through a browser.
Design the database.
Create the schema for the database.
Run the program and view the database.
Create data for the database in SQL, maybe using https://www.mockaroo.com/ (Links to an external site.). The Eircode doesn't have to follow a real pattern if it makes life easier.
Run the program and view the database.
Create one or more repositories/data access objects for queries through the database.
Autowire the NamedParameterJdbcTemplate bean into this class. Run your queries through this bean.
Create any classes you might need (they usually map to each table in the database).
Create a RowMapper() for each class and/or row from a resultset.
Add a service layer (interface and implementation) which may include business rules e.g. in a save() method you might make sure that the Eircode is unique. This service layer's implementation will have the Repository layer interface wired into it.
Add more methods to the service layer and implement these methods in the DAO layer.
Input Validation is not required for console input.
If the user types a letter instead of a number, please don't worry about it. I am looking for skills in Spring Framework not Java console, so do not waste time with it. The only validation required relates to the database's integrity e.g. if the user tries to create a property with an Eircode that already exists in the database, etc.
