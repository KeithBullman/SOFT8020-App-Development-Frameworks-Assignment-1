//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.dao.rowmappers;

import ie.kooth.classes.Tenant;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantRowMapper implements RowMapper<Tenant> {

    public Tenant mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Tenant tenant = new Tenant(rs.getInt("tenantId"), rs.getString("name"), rs.getString("email"), rs.getString("phoneNumber"), rs.getString("tenantEirCode"));
        return tenant;
    }
}
