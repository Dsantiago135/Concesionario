package edu.unicauca.dsantiago135.concesionaria.Repository;

import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import edu.unicauca.dsantiago135.concesionaria.Model.clsDealership;
import edu.unicauca.dsantiago135.concesionaria.Model.clsUnit;
import edu.unicauca.dsantiago135.concesionaria.Model.clsVehicle;

@Repository
public class UnitRepository {

//region ATTRIBUTES
private final JdbcTemplate attJdbcTemplate;

private final SimpleJdbcCall attSpRegisterX;
private final SimpleJdbcCall attSpUpdateX;
private final SimpleJdbcCall attSpGetX;
//endregion

//region CONSTRUCTOR
public UnitRepository(JdbcTemplate prmJdbcTemplate) {
//Conexion 
        this.attJdbcTemplate = prmJdbcTemplate;
//Calls to procedures
        this.attSpRegisterX = new SimpleJdbcCall(attJdbcTemplate).withProcedureName("sp_register_x");
        this.attSpUpdateX = new SimpleJdbcCall(attJdbcTemplate).withProcedureName("sp_update_x");
        this.attSpGetX = new SimpleJdbcCall(attJdbcTemplate).withProcedureName("sp_get_x");
}
//endregion

//region MAPPING
/**
 * Convierte un objeto {@link clsUnit} en un {@link MapSqlParameterSource}
 * listo para ser enviado como parámetros a un procedimiento almacenado de Oracle.
 *
 * @param prmUnit objeto de tipo {@link clsUnit} con los datos de la unidad
 * @return {@link MapSqlParameterSource} con los parámetros mapeados para Oracle
 */
private MapSqlParameterSource opToParams(clsUnit prmUnit) {
        return new MapSqlParameterSource()
                .addValue("P_UNI_ID",            prmUnit.getAttUnitId())
                .addValue("P_DEA_ID",            prmUnit.getAttDealership().getAttDealershipId())
                .addValue("P_VEH_ID",            prmUnit.getAttVehicle().getAttVehicleId())
                .addValue("P_UNI_LICENSE_PLATE", prmUnit.getAttLicensePlate())
                .addValue("P_UNI_COLOR",         prmUnit.getAttColor())
                .addValue("P_UNI_MILEAGE",       prmUnit.getAttMileage())
                .addValue("P_UNI_DATE_ENTRY",    prmUnit.getAttDateEntry())
                .addValue("P_UNI_CONDITION",     prmUnit.getAttCondition())
                .addValue("P_UNI_STATUS",        prmUnit.getAttStatus());
    }
/**
     * Convierte el resultado de un procedimiento almacenado de Oracle en un objeto {@link clsUnit}.
     * <p>
     * El {@link Map} de entrada corresponde a los parámetros de salida retornados
     * por {@link org.springframework.jdbc.core.simple.SimpleJdbcCall}.
     * </p>
     *
     * @param prmRow {@link Map} con las columnas y valores retornados por Oracle,
     *               donde la clave es el nombre del parámetro y el valor es de tipo {@link Object}
     * @return objeto de tipo {@link clsUnit} con los datos mapeados
     */
private clsUnit opToObject(Map<String, Object> prmRow) {
        clsUnit varUnit = new clsUnit();

        varUnit.setAttUnitId(((Number) prmRow.get("UNI_ID")).intValue());

        clsDealership varDealership = new clsDealership();
        varDealership.setAttDealershipId(((Number) prmRow.get("DEA_ID")).intValue());
        varUnit.setAttDealership(varDealership);

        clsVehicle varVehicle = new clsVehicle();
        varVehicle.setAttVehicleId(((Number) prmRow.get("VEH_ID")).intValue());
        varUnit.setAttVehicle(varVehicle);

        varUnit.setAttLicensePlate((String) prmRow.get("UNI_LICENSE_PLATE"));
        varUnit.setAttColor((String) prmRow.get("UNI_COLOR"));
        varUnit.setAttMileage(((Number) prmRow.get("UNI_MILEAGE")).intValue());
        varUnit.setAttDateEntry((Date) prmRow.get("UNI_DATE_ENTRY"));
        varUnit.setAttCondition((String) prmRow.get("UNI_CONDITION"));
        varUnit.setAttStatus((String) prmRow.get("UNI_STATUS"));

        return varUnit;
}
//endregion

//region PROCEDURES

//endregion

}
