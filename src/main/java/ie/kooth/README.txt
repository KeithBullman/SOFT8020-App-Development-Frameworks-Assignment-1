Hiya Cliona,

For this project, the inputs are fairly case-sensitive when it comes to adding/deleting properties and tenants.

As regards the property's rent, I'm unsure if you wanted it to vary based on the amount of people living in the property at a time.
EG: If the house rent is €1500, and if 3 people live there, the cost per person is €500.

However, I did not implement it like this, I implemented it as though regardless of how many people live there, each person pays the same fixed price (in relation to the above example, each person would pay €1500).

If the way I implemented it is not correct, I know to fix this it would just require an update query to change the rent cost every time someone was either added to the house or removed, and would require an additional column in the property table to account for totalCostOfRent so you could divide and calculate how much each tenant owes monthly.

Cheers! :))
-K