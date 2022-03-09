-- Name: Keith Bullman
-- ID: R00178736
-- Class: SDH4-A

CREATE TABLE property (
    propertyId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    eirCode varchar(10) NOT NULL,
    maxCapacity int(11) NOT NULL,
    currentCapacity int(11),
    costPerTenantPerMonth int(11) NOT NULL
);

CREATE TABLE tenant (
    tenantId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(25) NOT NULL,
    email varchar(50) NOT NULL,
    phoneNumber varchar(15) NOT NULL,
    tenantEirCode varchar(10) NOT NULL,
    FOREIGN KEY (tenantEirCode) REFERENCES property(eirCode) ON DELETE CASCADE
);