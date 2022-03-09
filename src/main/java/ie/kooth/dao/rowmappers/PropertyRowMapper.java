//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.dao.rowmappers;

import ie.kooth.classes.Property;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<Property> {

    public Property mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Property property = new Property();
        property.setPropertyId(rs.getInt("propertyId"));
        property.setEirCode(rs.getString("eirCode"));
        property.setMaxCapacity(rs.getInt("maxCapacity"));
        property.setCurrentCapacity(rs.getInt("currentCapacity"));
        property.setCostPerTenantPerMonth(rs.getInt("costPerTenantPerMonth"));
        return property;
    }
}
