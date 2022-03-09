//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.dao;

import ie.kooth.classes.Tenant;
import ie.kooth.dao.rowmappers.TenantRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Slf4j

public class TenantRepositorySQL implements TenantRepository{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Tenant> findTenantByEirCode(String eirCode) {
        try{
            String SQL = "SELECT * FROM tenant WHERE tenant.tenantEirCode = :tenantEirCode";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("tenantEirCode", eirCode);
            return namedParameterJdbcTemplate.query(SQL, namedParameters, new TenantRowMapper());

        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean exists(String name, String email, String phoneNumber) {
        final String SQL = "SELECT COUNT(*) FROM tenant WHERE tenant.name = :name AND tenant.email = :email AND tenant.phoneNumber = :phoneNumber";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", name).addValue("email", email).addValue("phoneNumber", phoneNumber);
        return 1 == namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, Integer.class);
    }

    @Override
    public int addTenant(String name, String email, String phoneNumber, String eirCode) {
        final String SQL = "INSERT INTO tenant(name, email, phoneNumber, tenantEirCode) VALUES (:name, :email, :phoneNumber, :tenantEirCode)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", name).addValue("email", email).addValue("phoneNumber", phoneNumber).addValue("tenantEirCode", eirCode);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public int deleteTenant(String name, String email, String eirCode) {
        final String SQL = "DELETE FROM tenant WHERE tenant.name = :name AND tenant.email = :email AND tenant.tenantEirCode = :eirCode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", name).addValue("email", email).addValue("eirCode", eirCode);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public int changeTenantProperty(String name, String email, String phoneNumber, String oldEircode, String newEircode) {
        final String SQL = "UPDATE tenant SET tenant.tenantEirCode = :newEircode WHERE tenant.name = :name AND tenant.email = :email AND tenant.phoneNumber = :phoneNumber AND tenant.tenantEirCode = :oldEircode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("newEircode", newEircode).addValue("name", name).addValue("email", email).addValue("phoneNumber", phoneNumber).addValue("oldEircode", oldEircode);
        int numberChanged = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if(numberChanged == 0){
            log.error("FAILED MISERABLY");
        }
        return numberChanged;
    }
}