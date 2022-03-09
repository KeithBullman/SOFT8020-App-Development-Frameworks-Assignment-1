//Name: Keith Bullman
//ID: R00178736
//Class: SDH4-A

package ie.kooth.dao;

import ie.kooth.classes.Property;
import ie.kooth.dao.rowmappers.PropertyRowMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Slf4j

public class PropertyRepositorySQL implements PropertyRepository{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Property findProperty(String eirCode) {
        try{
            String SQL = "SELECT * FROM property WHERE property.eirCode = :eirCode";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eirCode", eirCode);
            return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new PropertyRowMapper());
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public List<Property> findAllProperties(){
        try{
            return namedParameterJdbcTemplate.query("SELECT * from property", new PropertyRowMapper());
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public List<Property> findPropertiesWithSpace() {
        try{
            return namedParameterJdbcTemplate.query("SELECT * from property WHERE currentCapacity < maxCapacity", new PropertyRowMapper());
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean propertyExists(String eirCode){
        String SQL = "SELECT COUNT(*) FROM property WHERE property.eirCode = :eirCode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eirCode", eirCode);
        return 1 == namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, Integer.class);
    }

    @Override
    public int addProperty(String eirCode, int maxCapacity, int costPerTenantPerMonth) {
        final String SQL = "INSERT INTO property(eirCode, maxCapacity, currentCapacity, costPerTenantPerMonth) VALUES (:eirCode, :maxCapacity, :currentCapacity, :costPerTenantPerMonth)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eirCode", eirCode).addValue("maxCapacity", maxCapacity).addValue("currentCapacity", 0).addValue("costPerTenantPerMonth", costPerTenantPerMonth);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public int deleteProperty(String eirCode) {
        final String SQL = "DELETE FROM property WHERE property.eirCode = :eirCode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eirCode", eirCode);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public int updateCurrentCapacity(String eirCode) {
        final String SQL = "UPDATE property SET property.currentCapacity = currentCapacity + 1 WHERE property.eirCode = :eirCode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eirCode", eirCode);
        int numberChanged = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (numberChanged == 0){
            log.error("Failed to increment current capacity by one for new tenant.");
        }
        return numberChanged;
    }

    @Override
    public int lowerCurrentCapacity(String eirCode) {
        final String SQL = "UPDATE property SET property.currentCapacity = currentCapacity - 1 WHERE property.eirCode = :eirCode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eirCode", eirCode);
        int numberChanged = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (numberChanged == 0){
            log.error("Failed to decrease property capacity by one when removing tenant.");
        }
        return numberChanged;
    }

    @Override
    public Float averageAmountOfTenants() {
        return namedParameterJdbcTemplate.getJdbcTemplate().queryForObject("SELECT AVG(CAST(CURRENTCAPACITY as FLOAT)) from property;", Float.class);
    }

    @Override
    public Float totalIncome() {
        return namedParameterJdbcTemplate.getJdbcTemplate().queryForObject("SELECT SUM(CURRENTCAPACITY*COSTPERTENANTPERMONTH) from property;", Float.class);
    }

    @Override
    public int totalReachedCapacity() {
        return namedParameterJdbcTemplate.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM PROPERTY WHERE currentCapacity = MaxCapacity;", Integer.class);
    }
}
