-- Name: Keith Bullman
-- ID: R00178736
-- Class: SDH4-A

INSERT INTO property (propertyId, eirCode, maxCapacity, currentCapacity, costPerTenantPerMonth) VALUES
(1, 'T12C50', 3, 3, 500),
(2, 'T13C60', 5, 2, 400),
(3, 'T14C70', 2, 2, 750),
(4, 'T15C80', 4, 1, 600);

INSERT INTO tenant (tenantId, name, email, phoneNumber, tenantEirCode) VALUES
(1, 'Bethany', 'bethany@gmail.com', '12345', 'T12C50'),
(2, 'Gregg', 'gregg@live.ie', '54321', 'T12C50'),
(3, 'Mae', 'mae@outlook.com', '92843', 'T13C60'),
(4, 'James', 'james@yahoo.co.uk', '01957', 'T12C50'),
(5, 'Sofi', 'sofi@gmail.com', '29185', 'T13C60'),
(6, 'Keith', 'kooth@gmail.com', '19572', 'T15C80'),
(7, 'Daniel', 'daniel@live.ie', '48192', 'T14C70'),
(8, 'Sam', 'sam@outlook.com', '59284', 'T14C70');
